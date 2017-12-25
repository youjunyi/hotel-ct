package school.libenhe.hotel.service;

import school.libenhe.hotel.entity.User;
import school.libenhe.hotel.utils.PageBean;

import java.util.List;

public interface IUserService
{
    void getAll(PageBean<User> pageBean);

    List<User> query();
    List<User> query(String name);
    void add(User user);

    void delete(int i);

    User findById(int i);

    void updata(User user);

    List<User> query(String username, String password);

    void updatapassword(User user);
}
