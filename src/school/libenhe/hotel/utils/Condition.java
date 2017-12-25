package school.libenhe.hotel.utils;

/**
 * @author Li Benhe Email: libenhe919@gmail.com
 * @version 2016-3-1 下午2:40:09
 */
public class Condition {

	private String foodName;
	private int foodType_id;

	private String name;

	private String issh;
	private String username;
	private String dept;
	private String zhou;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getFoodType_id() {
		return foodType_id;
	}

	public void setFoodType_id(int foodType_id) {
		this.foodType_id = foodType_id;
	}

	@Override
	public String toString() {
		return "Condition{" +
				"foodName='" + foodName + '\'' +
				", foodType_id=" + foodType_id +
				", name='" + name + '\'' +
				'}';
	}

	public String getIssh() {
		return issh;
	}

	public void setIssh(String issh) {
		this.issh = issh;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getZhou() {
		return zhou;
	}

	public void setZhou(String zhou) {
		this.zhou = zhou;
	}
}
