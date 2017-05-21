package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static String URL = "jdbc:mysql://127.0.0.1:3306/";
	private static String USER = "root";
	private static String PASSWORD = "123456";
	private static Connection connection = null;
	
	public static Connection getConnection(String tablename) {
		//拼接数据库的地址
		String url = URL;
		url +=  tablename;
		//1.加载驱动程序
		try {
			Class.forName("com.mysql.jdbc.Driver");
		//2.获得数据库的连接
		connection = DriverManager.getConnection(url, USER, PASSWORD);
		//还原url地址，便于下次使用
		url = URL;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	public static void setClose() {
		if(connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
