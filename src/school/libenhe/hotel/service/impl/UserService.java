package school.libenhe.hotel.service.impl;

import school.libenhe.hotel.dao.IUserDao;
import school.libenhe.hotel.entity.Task;
import school.libenhe.hotel.service.IUserService;
import school.libenhe.hotel.utils.PageBean;

import java.util.List;

import static school.libenhe.hotel.factory.BeanFactory.getInstance;

public class UserService implements IUserService {

    IUserDao userDao = getInstance("userDao", IUserDao.class);
    @Override
    public void getAll(PageBean<Task> pageBean) {
        userDao.getAll(pageBean);
    }

    @Override
    public List<Task> query() {
        return null;
    }

    @Override
    public List<Task> query(String name) {
        return userDao.query(name);
    }

    @Override
    public void add(Task user) {

        userDao.add(user);
    }

    @Override
    public void delete(int i) {
        userDao.delete(i);
    }

    @Override
    public Task findById(int i) {
        return userDao.findById(i);
    }

    @Override
    public void updata(Task user) {
        userDao.updata(user);
    }

    @Override
    public List<Task> query(String username, String password) {
        return userDao.query(username,password);
    }

    
}
