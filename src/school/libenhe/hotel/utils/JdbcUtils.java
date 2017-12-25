package school.libenhe.hotel.utils;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据库操作封装
 * 
 * @author Li Benhe Email: libenhe919@gmail.com
 * @version 2016-3-1 下午2:15:12
 */
public class JdbcUtils {

	// 链接池
	private static DataSource dataSource;
	static {
		dataSource = new ComboPooledDataSource();
	}

	public static DataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * DBUtils工具类对象
	 */
	
	public static QueryRunner getQueryRunner() {
		return new QueryRunner(dataSource);
	}
	
}
