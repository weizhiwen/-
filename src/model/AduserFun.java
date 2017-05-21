package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import db.DBConnection;

public class AduserFun {
	//登录查询函数
	public boolean select(String username, String password) throws SQLException{
		Connection connection = DBConnection.getConnection("yzmedical");
		//预防sql注入
		String sql = "SELECT * FROM yz_aduser WHERE username=? and password=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()){
			return true;
		}else{
			return false;
		}
	}
	
	//修改密码函数
	public boolean changePassword(String username, String newpassword) throws SQLException {
		Connection connection = DBConnection.getConnection("yzmedical");
		String sql = "UPDATE yz_aduser SET password=? WHERE username=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, newpassword);
		preparedStatement.setString(2, username);
		int result = preparedStatement.executeUpdate();
		if(result == 1)
			return true;
		else
			return false;
	}
	
	//查询用户存在函数
	public boolean isExist(String username) throws SQLException{
		Connection connection = DBConnection.getConnection("yzmedical");
		String sql = "SELECT count(username) FROM yz_aduser WHERE username=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, username);
		ResultSet resultSet = preparedStatement.executeQuery();
		//resultSet指针移动到下一行
		resultSet.next();
		//返回sql指令的结果值，也即是查询字段值的重复次数
		int count = resultSet.getInt(1);
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}
	
	//添加用户函数
	public boolean addUser(String username, String password) throws SQLException{
		Connection connection = DBConnection.getConnection("yzmedical");
		//在插入数据库之前，先要做用户名是否重复检测
		if(!isExist(username)){
			String sql = "INSERT yz_aduser(username,password) VALUES(?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			int result = preparedStatement.executeUpdate();
			if(result == 1)
				return true;
			else
				return false;
		}else {
			JOptionPane.showMessageDialog(null, "该用户名已存在，换个试试。");
		}
		return false;
	}
}
