package school.libenhe.hotel.service.impl;

import school.libenhe.hotel.dao.ISysuserDao;
import school.libenhe.hotel.entity.Sysuser;
import school.libenhe.hotel.service.ISysuserStrvice;
import school.libenhe.hotel.utils.PageBean;

import java.util.List;

import static school.libenhe.hotel.factory.BeanFactory.getInstance;

public class SysuserService implements ISysuserStrvice{
    ISysuserDao sysuserDao = getInstance("sysuserDao", ISysuserDao.class);
    @Override
    public void getAll(PageBean<Sysuser> pageBean) {
        sysuserDao.getAll(pageBean);
    }

    @Override
    public List<Sysuser> query() {
        return sysuserDao.query();
    }

    @Override
    public List<Sysuser> query(String name) {
        return sysuserDao.query(name);
    }

   /* @Override
    public void add(Sysuser sysuser) {
        sysuserDao.add(sysuser);
    }*/

    @Override
    public void delete(int i) {
        sysuserDao.delete(i);
    }

    @Override
    public Sysuser findById(int i) {
        return sysuserDao.findById(i);
    }

    /*@Override
    public void updata(Sysuser sysuser) {
        sysuserDao.updata(sysuser);
    }*/

    @Override
    public List<Sysuser> query(String username, String password) {
        return sysuserDao.query(username,password);
    }

    @Override
    public void updatapassword(Sysuser sysuser) {
        sysuserDao.updatapassword(sysuser);
    }
}
