package school.libenhe.hotel.dao;

import school.libenhe.hotel.entity.Sysuser;
import school.libenhe.hotel.utils.PageBean;

import java.util.List;

public interface ISysuserDao {
    void getAll(PageBean<Sysuser> pageBean);

    List<Sysuser> query();
    List<Sysuser> query(String name);
   /* void add(Sysuser sysuser);*/

    void delete(int i);

    Sysuser findById(int i);

   /* void updata(Sysuser sysuser);*/

    List<Sysuser> query(String username, String password);

    void updatapassword(Sysuser sysuser);
}
