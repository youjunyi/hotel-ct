package school.libenhe.hotel.factory;

import java.util.ResourceBundle;

/**
 * 工厂创建Dao或Service实例
 * 
 * @author Li Benhe Email: libenhe919@gmail.com
 * @version 2016-3-7 下午6:09:04
 */
public class BeanFactory {

	//加载配置文件
	private static ResourceBundle bundle;
	static {
		bundle = ResourceBundle.getBundle("instance");
	}

	/**
	 * 根据指定的key值读取文件获取文件的全路径，创建对象
	 * @author: Li Benhe Email: libenhe919@gmail.com
	 * @version: 2016-3-7 下午9:32:23 
	 * @param key
	 * @param clazz
	 * @return
	 */
	public static <T> T getInstance(String key, Class<T> clazz) {

		String className = bundle.getString(key);
		try {
			return (T) Class.forName(className).newInstance();
		} catch (Exception e) {
         	throw new RuntimeException(e);
		}
	}
}
