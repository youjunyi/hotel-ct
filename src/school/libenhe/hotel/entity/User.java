package school.libenhe.hotel.entity;

public class User {
    private Long id;
    private String name;
    private String number;
    private String password;
    private String sex;
    private String minzhu;
    private String cno;
    private String adds;
    private String telephone;
    private String emil;
    private String birth;
    private String cjgzdate;
    private String zhuanye;
    private String yuanxiao;
    private String zhicheng;
    private String xueli;
    private String xuewei;
    private String waiyu;
    private String dept;
    private String jianyanshi;
    private String yjfx;
    private String beizhu;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMinzhu() {
        return minzhu;
    }

    public void setMinzhu(String minzhu) {
        this.minzhu = minzhu;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getAdds() {
        return adds;
    }

    public void setAdds(String adds) {
        this.adds = adds;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmil() {
        return emil;
    }

    public void setEmil(String emil) {
        this.emil = emil;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getCjgzdate() {
        return cjgzdate;
    }

    public void setCjgzdate(String cjgzdate) {
        this.cjgzdate = cjgzdate;
    }

    public String getZhuanye() {
        return zhuanye;
    }

    public void setZhuanye(String zhuanye) {
        this.zhuanye = zhuanye;
    }

    public String getYuanxiao() {
        return yuanxiao;
    }

    public void setYuanxiao(String yuanxiao) {
        this.yuanxiao = yuanxiao;
    }

    public String getZhicheng() {
        return zhicheng;
    }

    public void setZhicheng(String zhicheng) {
        this.zhicheng = zhicheng;
    }

    public String getXueli() {
        return xueli;
    }

    public void setXueli(String xueli) {
        this.xueli = xueli;
    }

    public String getXuewei() {
        return xuewei;
    }

    public void setXuewei(String xuewei) {
        this.xuewei = xuewei;
    }

    public String getWaiyu() {
        return waiyu;
    }

    public void setWaiyu(String waiyu) {
        this.waiyu = waiyu;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getJianyanshi() {
        return jianyanshi;
    }

    public void setJianyanshi(String jianyanshi) {
        this.jianyanshi = jianyanshi;
    }

    public String getYjfx() {
        return yjfx;
    }

    public void setYjfx(String yjfx) {
        this.yjfx = yjfx;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", minzhu='" + minzhu + '\'' +
                ", cno='" + cno + '\'' +
                ", adds='" + adds + '\'' +
                ", telephone='" + telephone + '\'' +
                ", emil='" + emil + '\'' +
                ", birth='" + birth + '\'' +
                ", cjgzdate='" + cjgzdate + '\'' +
                ", zhuanye='" + zhuanye + '\'' +
                ", yuanxiao='" + yuanxiao + '\'' +
                ", zhicheng='" + zhicheng + '\'' +
                ", xueli='" + xueli + '\'' +
                ", xuewei='" + xuewei + '\'' +
                ", waiyu='" + waiyu + '\'' +
                ", dept='" + dept + '\'' +
                ", jianyanshi='" + jianyanshi + '\'' +
                ", yjfx='" + yjfx + '\'' +
                ", beizhu='" + beizhu + '\'' +
                '}';
    }
}
