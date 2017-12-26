package school.libenhe.hotel.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import school.libenhe.hotel.dao.IUserDao;
import school.libenhe.hotel.entity.Task;
import school.libenhe.hotel.utils.Condition;
import school.libenhe.hotel.utils.JdbcUtils;
import school.libenhe.hotel.utils.PageBean;

import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {
    private QueryRunner qr = JdbcUtils.getQueryRunner();
    @Override
    public void getAll(PageBean<Task> pageBean) {
        int totalCount = this.getTotalCount(pageBean);
        pageBean.setTotalCount(totalCount);

        List<Object> list = new ArrayList<Object>();

        if (pageBean.getCurrentPage() <= 0) {
            pageBean.setCurrentPage(1); // 把当前页设置为1
        } else if (pageBean.getCurrentPage() > pageBean.getTotalPage()) {
            pageBean.setCurrentPage(pageBean.getTotalPage()); // 把当前页设置为最大页数
        }

        // 获取当前页： 计算查询的起始行、返回的行数
        int currentPage = pageBean.getCurrentPage();
        int index = (currentPage - 1) * pageBean.getPageCount(); // 查询的起始行
        int count = pageBean.getPageCount(); // 查询返回的行数

        Condition condition = pageBean.getCondition();

        // 分页查询数据; 把查询到的数据设置到pb对象中
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append(" f.* ");

        sb.append(" FROM ");
        sb.append("     	task f");
        sb.append(" WHERE 	1=1 ");

        // 判断
        if (condition != null) {
            String chapterid = condition.getChapterid();
            if (chapterid != null && !chapterid.isEmpty()) {
                sb.append("  AND f.chapterid = ? ");
                list.add(chapterid);
            }
            
        }
        sb.append(" limit ?,? ");
        list.add(index);
        list.add(count);
        try {
            // 根据当前页，查询当前页数据(一页数据)
            if (index >= 0) {
                List<Task> pageData = qr.query(sb.toString(),
                        new BeanListHandler<Task>(Task.class), list.toArray());
                // 设置到pb对象中
                pageBean.setPageData(pageData);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

  

    @Override
    public void delete(int i) {
        String sql = "DELETE FROM task WHERE id=?";
        try {
            qr.update(sql, i);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Task> query(String name) {
        String sql = "SELECT * FROM task WHERE name LIKE ?";
        try {
            return qr.query(sql, new BeanListHandler<Task>(Task.class), "%"
                    + name + "%");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Task findById(int i) {
         	String sql = "SELECT * FROM task where id =?";
        try {
            return qr.query(sql, new BeanHandler<Task>(Task.class), i);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Task task) {
        String sql = " INSERT task(name,classname,path,chapterid) VALUES(?,?,?,?)";
        try {
            qr.update(sql,task.getName(),task.getClassname(),task.getPath(),task.getChapterid());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }



    @Override
    public void updata(Task task) {
        String sql = "UPDATE task SET name=? WHERE id =?";
        try {
            qr.update(sql, task.getName(),task.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Task> query(String username, String password) {
        String sql = "SELECT * FROM user WHERE number=? and password=?";
        try {
            return qr.query(sql, new BeanListHandler<Task>(Task.class),username,password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

  

    public int getTotalCount(PageBean<Task> pb) {
        StringBuilder sb = new StringBuilder();
        List<Object> list = new ArrayList<Object>();
        sb.append(" SELECT");
        sb.append("   count(*) ");
        sb.append(" FROM ");
        sb.append("     	task f ");
        sb.append(" WHERE 	1=1 ");

        Condition condition = pb.getCondition();
        // 判断
        if (condition != null) {
            String chapterid = condition.getChapterid();
            if (chapterid != null && !chapterid.isEmpty()) {
                sb.append("  AND f.chapterid = ? ");
                list.add(chapterid);
            }

           
        }
        try {
            // 执行查询， 返回结果的第一行的第一列
            Long count = qr.query(sb.toString(), new ScalarHandler<Long>(),
                    list.toArray());
            return count.intValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
