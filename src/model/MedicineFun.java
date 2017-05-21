package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;

public class MedicineFun {
	//查询费用类型的条数
	public int getTypeCount(String costType) throws SQLException {
		Connection connection = DBConnection.getConnection("yzmedical");
		String sql = "SELECT count(*) FROM yz_medicinedetail where costtype='"+costType+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		int number = resultSet.getInt(1);
		return number;
	}
			
	//查询费用信息表
	//查询病人信息表
	public List<Medicine> query(String costType) throws SQLException {
		List<Medicine> result = new ArrayList<Medicine>();
		Connection connection = DBConnection.getConnection("yzmedical");
		//根据condition是否有值来判断是否是按条件查询,此处也使用模糊查询，方便用户的简单输入，注意查询条数也要是模糊查询
		StringBuffer sql = new StringBuffer();
		sql.append("select * from yz_medicinedetail ");
		sql.append("where costtype= ?");
		PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		preparedStatement.setString(1, costType);
		ResultSet resultSet = preparedStatement.executeQuery();
		Medicine medicine = null;
		while(resultSet.next()){
			//把这行数据放到一个对象中
			medicine = new Medicine();
			medicine.setItemName(resultSet.getString("itemname"));
			medicine.setCostType(resultSet.getString("costtype"));
			medicine.setKind(resultSet.getString("kind"));
			medicine.setUnit(resultSet.getString("unit"));
			medicine.setPrePrice(resultSet.getFloat("preprice"));
			//把这个对象放到List数组里面
			result.add(medicine);
			}
			return result;
		}
	
	//模糊查询的条数
	public int getSearchCount(String costType, String medicineName) throws SQLException {
		Connection connection = DBConnection.getConnection("yzmedical");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(itemname) FROM yz_medicinedetail ");
		sql.append("WHERE itemname like ? and costtype=?");
		PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		preparedStatement.setString(1, "%"+medicineName+"%");
		preparedStatement.setString(2, costType);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		int number = resultSet.getInt(1);
		return number;
	}
	
	//模糊查询的结果
	public List<Medicine> getSearchContent(String costType, String medicineName) throws SQLException {
		List<Medicine> result = new ArrayList<Medicine>();
		Connection connection = DBConnection.getConnection("yzmedical");
		StringBuffer sql = new StringBuffer();
		//注意后面的空格，因为这里已被整哭。。。。
		sql.append("SELECT * FROM yz_medicinedetail ");
		sql.append("WHERE itemname like ? and costtype=?");
		PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		preparedStatement.setString(1, "%"+medicineName+"%");
		preparedStatement.setString(2, costType);
		ResultSet resultSet = preparedStatement.executeQuery();
		Medicine medicine = null;
		while(resultSet.next()){
			//把这行数据放到一个对象中
			medicine = new Medicine();
			medicine.setItemName(resultSet.getString("itemname"));
			medicine.setCostType(resultSet.getString("costtype"));
			medicine.setKind(resultSet.getString("kind"));
			medicine.setUnit(resultSet.getString("unit"));
			medicine.setPrePrice(resultSet.getFloat("preprice"));
			//把这个对象放到List数组里面
			result.add(medicine);
			}
			return result;
		}
}
