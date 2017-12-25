package school.libenhe.hotel.entity;

public class Chapter {
    private Long id;
    private String name;
    private  String zhou;

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZhou() {
        return zhou;
    }

    public void setZhou(String zhou) {
        this.zhou = zhou;
    }
}
