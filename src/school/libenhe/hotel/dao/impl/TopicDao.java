package school.libenhe.hotel.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import school.libenhe.hotel.dao.ITopicDao;
import school.libenhe.hotel.entity.Chapter;
import school.libenhe.hotel.utils.Condition;
import school.libenhe.hotel.utils.JdbcUtils;
import school.libenhe.hotel.utils.PageBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicDao implements ITopicDao{
    private QueryRunner qr = JdbcUtils.getQueryRunner();
    @Override
    public void getAll(PageBean<Chapter> pageBean) {
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
        sb.append("     	chapter f" );
        sb.append("  ");
        sb.append(" WHERE 	1=1 ");

        // 判断
        if (condition != null) {
            String zhou = condition.getZhou();
            if (zhou != null && !zhou.isEmpty()) {
                sb.append("  AND f.zhou = ? ");
                list.add( zhou );
            }

        }
        sb.append(" limit ?,? ");
        list.add(index);
        list.add(count);
        try {
            // 根据当前页，查询当前页数据(一页数据)
            if (index >= 0) {
                List<Chapter> pageData = qr.query(sb.toString(),
                        new BeanListHandler<Chapter>(Chapter.class), list.toArray());
                // 设置到pb对象中
                pageBean.setPageData(pageData);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void delete(int i) {
        String sql = "DELETE FROM chapter WHERE id=?";
        try {
            qr.update(sql, i);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Chapter findById(int i) {
        String sql = "SELECT * FROM chapter where id =?";
        try {
            return qr.query(sql, new BeanHandler<Chapter>(Chapter.class), i);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Chapter chapter) {
        String sql = " INSERT chapter(name,zhou) VALUES(?,?)";
        try {
            qr.update(sql,chapter.getName(),chapter.getZhou());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void updata(Chapter chapter) {
        String sql = "UPDATE chapter SET name=?  WHERE id =?";
        try {
            qr.update(sql, chapter.getName(),chapter.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

   

    @Override
    public List<Chapter> query(String keyword) {
        String sql = "SELECT * FROM chapter WHERE name LIKE ?";
        try {
            return qr.query(sql, new BeanListHandler<Chapter>(Chapter.class), "%"
                    + keyword + "%");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getAlltj(PageBean<Chapter> pageBean) {
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
        sb.append(" f.*,u.name as username,s.name as sysName ");

        sb.append(" FROM ");
        sb.append("     	chapter f" );
        sb.append(" left join user u   on u.id=f.userid left join sysuser s on s.id=f.sysid ");
        sb.append(" WHERE 	1=1 ");

    // 判断
        if (condition != null) {
        String name = condition.getName();
        if (name != null && !name.isEmpty()) {
            sb.append("  AND f.name LIKE ? ");
            list.add("%" + name + "%");
        }

    }
        if (condition != null) {
        String issh = condition.getIssh();
        if (issh != null && !issh.isEmpty()) {
            sb.append("  AND f.issh = ? ");
            list.add(issh);
        }

    }
        sb.append(" limit ?,? ");
        list.add(index);
        list.add(count);
        try {
        // 根据当前页，查询当前页数据(一页数据)
        if (index >= 0) {
            List<Chapter> pageData = qr.query(sb.toString(),
                    new BeanListHandler<Chapter>(Chapter.class), list.toArray());
            // 设置到pb对象中
            pageBean.setPageData(pageData);
        }

    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}

    @Override
    public List<Chapter> getAlltj(Condition condition) {
        List<Object> list = new ArrayList<Object>();
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append(" f.*,u.name as username,s.name as sysName ");

        sb.append(" FROM ");
        sb.append("     	chapter f" );
        sb.append(" left join user u   on u.id=f.userid left join sysuser s on s.id=f.sysid ");
        sb.append(" WHERE 	1=1 ");
        sb.append(" and f.userid in ( select id from user where 1=1 ") ;
        if (condition != null) {
            String username = condition.getUsername();
            if (username != null && !username.isEmpty()) {
                sb.append("  AND name  = ? ");
                list.add(username);
            }

        }

        if (condition != null) {
            String dept = condition.getDept();
            if (dept != null && !dept.isEmpty()) {
                sb.append("  AND dept  = ? ");
                list.add(dept);
            }

        }
        
        sb.append(" )");

        // 判断
        if (condition != null) {
            String issh = condition.getIssh();
            if (issh != null && !issh.isEmpty()) {
                sb.append("  AND f.issh = ? ");
                list.add(issh);
            }

        }
        List<Chapter> pageData = null;
        try {
            pageData = qr.query(sb.toString(),
                    new BeanListHandler<Chapter>(Chapter.class), list.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return   pageData;
    }


    public int getTotalCount(PageBean<Chapter> pb) {
        StringBuilder sb = new StringBuilder();
        List<Object> list = new ArrayList<Object>();
        sb.append(" SELECT");
        sb.append("   count(*) ");
        sb.append(" FROM ");
        sb.append("     	chapter f ");
        sb.append(" WHERE 	1=1 ");

        Condition condition = pb.getCondition();
        // 判断
        if (condition != null) {
            String name = condition.getFoodName();
            if (name != null && !name.isEmpty()) {
                sb.append("  AND f.name LIKE ? ");
                list.add("%" + name + "%");
            }


        }
        if (condition != null) {
            String issh = condition.getIssh();
            if (issh != null && !issh.isEmpty()) {
                sb.append("  AND f.issh = ? ");
                list.add(issh);
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
