package school.libenhe.hotel.service;

import school.libenhe.hotel.entity.Task;
import school.libenhe.hotel.utils.PageBean;

import java.util.List;

public interface IUserService
{
    void getAll(PageBean<Task> pageBean);

    List<Task> query();
    List<Task> query(String name);
    void add(Task user);

    void delete(int i);

    Task findById(int i);

    void updata(Task user);

    List<Task> query(String username, String password);

}
