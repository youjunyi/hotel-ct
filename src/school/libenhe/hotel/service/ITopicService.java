package school.libenhe.hotel.service;

import school.libenhe.hotel.entity.Chapter;
import school.libenhe.hotel.utils.Condition;
import school.libenhe.hotel.utils.PageBean;

import java.util.List;

public interface ITopicService  {
    void getAll(PageBean<Chapter> pageBean);

    List<Chapter> query();

    void add(Chapter chapter);

    void delete(int i);

    Chapter findById(int i);

    void updata(Chapter chapter);


    List<Chapter> query(String keyword);

    void getAlltj(PageBean<Chapter> pageBean);

    List<Chapter> getAlltj(Condition condition);
}
