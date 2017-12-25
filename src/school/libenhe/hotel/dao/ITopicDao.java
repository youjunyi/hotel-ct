package school.libenhe.hotel.dao;

import school.libenhe.hotel.entity.Chapter;
import school.libenhe.hotel.utils.Condition;
import school.libenhe.hotel.utils.PageBean;

import java.util.List;

public interface ITopicDao
{
    void getAll(PageBean<Chapter> pageBean);

    void add(Chapter topic);

    void delete(int i);

    Chapter findById(int i);

    void updata(Chapter topic);


    List<Chapter> query(String keyword);

    void getAlltj(PageBean<Chapter> pageBean);

    List<Chapter> getAlltj(Condition condition);
}
