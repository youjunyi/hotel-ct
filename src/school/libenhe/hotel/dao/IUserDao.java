package school.libenhe.hotel.dao;

import school.libenhe.hotel.entity.User;
import school.libenhe.hotel.utils.PageBean;

import java.util.List;

public interface IUserDao {
    void getAll(PageBean<User> pageBean);

    void add(User user);

    void delete(int i);

    List<User> query(String name);

    User findById(int i);

    void updata(User user);

    List<User> query(String username, String password);

    void updatapassword(User user);
}
