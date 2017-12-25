package school.libenhe.hotel.service.impl;

import school.libenhe.hotel.dao.IUserDao;
import school.libenhe.hotel.entity.User;
import school.libenhe.hotel.service.IUserService;
import school.libenhe.hotel.utils.PageBean;

import java.util.List;

import static school.libenhe.hotel.factory.BeanFactory.getInstance;

public class UserService implements IUserService {

    IUserDao userDao = getInstance("userDao", IUserDao.class);
    @Override
    public void getAll(PageBean<User> pageBean) {
        userDao.getAll(pageBean);
    }

    @Override
    public List<User> query() {
        return null;
    }

    @Override
    public List<User> query(String name) {
        return userDao.query(name);
    }

    @Override
    public void add(User user) {

        userDao.add(user);
    }

    @Override
    public void delete(int i) {
        userDao.delete(i);
    }

    @Override
    public User findById(int i) {
        return userDao.findById(i);
    }

    @Override
    public void updata(User user) {
        userDao.updata(user);
    }

    @Override
    public List<User> query(String username, String password) {
        return userDao.query(username,password);
    }

    @Override
    public void updatapassword(User user) {
        userDao.updatapassword(user);
    }
}
