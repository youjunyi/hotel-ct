package school.libenhe.hotel.dao;

import school.libenhe.hotel.entity.Task;
import school.libenhe.hotel.utils.PageBean;

import java.util.List;

public interface IUserDao {
    void getAll(PageBean<Task> pageBean);

    void add(Task user);

    void delete(int i);

    List<Task> query(String name);

    Task findById(int i);

    void updata(Task user);

    List<Task> query(String username, String password);

}
