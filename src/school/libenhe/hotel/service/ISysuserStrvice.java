package school.libenhe.hotel.service;

import school.libenhe.hotel.entity.Sysuser;
import school.libenhe.hotel.utils.PageBean;

import java.util.List;

public interface ISysuserStrvice {
    void getAll(PageBean<Sysuser> pageBean);

    List<Sysuser> query();
    List<Sysuser> query(String name);
    /*void add(Sysuser sysuser);*/

    void delete(int i);

    Sysuser findById(int i);

   /* void updata(Sysuser sysuser);*/

    List query(String username, String password);

    void updatapassword(Sysuser sysuser);
}
