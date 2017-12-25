package school.libenhe.hotel.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import school.libenhe.hotel.dao.ISysuserDao;
import school.libenhe.hotel.entity.Sysuser;
import school.libenhe.hotel.utils.Condition;
import school.libenhe.hotel.utils.JdbcUtils;
import school.libenhe.hotel.utils.PageBean;

import java.util.ArrayList;
import java.util.List;

public class SysuserDao implements ISysuserDao {
    private QueryRunner qr = JdbcUtils.getQueryRunner();

    @Override
    public void getAll(PageBean<Sysuser> pageBean) {
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
        sb.append("     	sysuser f");
        sb.append(" WHERE 	1=1 ");

        // 判断
        if (condition != null) {
            String name = condition.getFoodName();
            if (name != null && !name.isEmpty()) {
                sb.append("  AND f.name LIKE ? ");
                list.add("%" + name + "%");
            }

        }
        sb.append(" limit ?,? ");
        list.add(index);
        list.add(count);
        try {
            // 根据当前页，查询当前页数据(一页数据)
            if (index >= 0) {
                List<Sysuser> pageData = qr.query(sb.toString(),
                        new BeanListHandler<Sysuser>(Sysuser.class), list.toArray());
                // 设置到pb对象中
                pageBean.setPageData(pageData);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Sysuser> query() {
        String sql = "SELECT * FROM sysuser";
        try {
            return qr.query(sql, new BeanListHandler<Sysuser>(Sysuser.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Sysuser> query(String name) {
        String sql = "SELECT * FROM sysuser WHERE name LIKE ?";
        try {
            return qr.query(sql, new BeanListHandler<Sysuser>(Sysuser.class), "%"
                    + name + "%");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void delete(int i) {
        String sql = "DELETE FROM sysuser WHERE id=?";
        try {
            qr.update(sql, i);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Sysuser findById(int i) {
        String sql = "SELECT * FROM sysuser where id =?";
        try {
            return qr.query(sql, new BeanHandler<Sysuser>(Sysuser.class), i);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

   /* @Override
    public void add(Sysuser sysuser) {
        String sql = " INSERT sysuser(name,number,zjjb,zjlb,bz,password) VALUES(?,?,?,?,?,?)";
        try {
            qr.update(sql,sysuser.getName(),sysuser.getNumber(),sysuser.getZjjb(),sysuser.getZjlb(),sysuser.getBz(),"000000");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }*/

   /* @Override
    public void updata(Sysuser sysuser) {
        String sql = "UPDATE sysuser SET name=?,number=?,zjjb=?,zjlb=?,bz=? WHERE id =?";
        try {
            qr.update(sql, sysuser.getName(),sysuser.getNumber(),sysuser.getZjjb(),sysuser.getZjlb(),sysuser.getBz(),sysuser.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

    @Override
    public List<Sysuser> query(String username, String password) {
        String sql = "SELECT * FROM sysuser WHERE number=? and password=?";
        try {
            return qr.query(sql, new BeanListHandler<Sysuser>(Sysuser.class),username,password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatapassword(Sysuser sysuser) {
        String sql = "UPDATE sysuser SET password=? WHERE id =?";
        try {
            qr.update(sql, sysuser.getPassword(),sysuser.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getTotalCount(PageBean<Sysuser> pb) {
        StringBuilder sb = new StringBuilder();
        List<Object> list = new ArrayList<Object>();
        sb.append(" SELECT");
        sb.append("   count(*) ");
        sb.append(" FROM ");
        sb.append("     	sysuser f ");
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
