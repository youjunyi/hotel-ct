package school.libenhe.hotel.service.impl;

import school.libenhe.hotel.dao.ITopicDao;
import school.libenhe.hotel.entity.Chapter;
import school.libenhe.hotel.service.ITopicService;
import school.libenhe.hotel.utils.Condition;
import school.libenhe.hotel.utils.PageBean;

import java.util.List;

import static school.libenhe.hotel.factory.BeanFactory.getInstance;

public class TopicService implements ITopicService{
    ITopicDao topicDao = getInstance("topicDao", ITopicDao.class);

    @Override
    public void getAll(PageBean<Chapter> pageBean) {
        topicDao.getAll(pageBean);
    }

    @Override
    public List<Chapter> query() {
        return null;
    }

    @Override
    public void add(Chapter topic) {
        topicDao.add(topic);
    }

    @Override
    public void delete(int i) {
        topicDao.delete(i);
    }

    @Override
    public Chapter findById(int i) {
        return topicDao.findById(i);
    }

    @Override
    public void updata(Chapter topic) {
        topicDao.updata(topic);
    }
    

    @Override
    public List<Chapter> query(String keyword) {
        return topicDao.query(keyword);
    }

    @Override
    public void getAlltj(PageBean<Chapter> pageBean) {
        topicDao.getAlltj(pageBean);
    }

    @Override
    public List<Chapter> getAlltj(Condition condition) {
        return topicDao.getAlltj(condition);
    }
}
