package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;

public class RoomFun {
	//查询房间类型的条数
	public int getRoomTypeCount() throws SQLException {
		Connection connection = DBConnection.getConnection("yzmedical");
		String sql = "SELECT count(id) FROM yz_roombed";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		int number = resultSet.getInt(1);
		return number;
	}
	
	//查询房间的全部信息
	public String[][] query(int count) throws SQLException {
		RoomFun roomFun = new RoomFun();
		String result[][] = new String[count][7];
		Connection connection = DBConnection.getConnection("yzmedical");
		String sql = "SELECT * FROM yz_roombed";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement .executeQuery();
		int i = 0;
		while(resultSet.next()){
		//把这行数据放到一个对象中
			//result[i][0] = resultSet.getString("id");
			//数据库中的序号不取，到界面上在动态为其编号，因为考虑到数据库中的序号因为删减不是连续
			result[i][0] = resultSet.getString("roomno");
			result[i][1] = resultSet.getString("bedno");
			result[i][2] = resultSet.getString("bedtype");
			result[i][3] = resultSet.getString("bedprice");
			result[i][4] = resultSet.getString("floornumber");
			result[i][5] = resultSet.getString("iscanuse");
			result[i][6] = resultSet.getString("remark");
			i++;
		}
		return result;
	}
	
	//删除选定的房间信息
	public boolean delRoom(String roomNo, String bedNo) throws SQLException
	{
		Connection connection = DBConnection.getConnection("yzmedical");			
		String sql = "DELETE FROM yz_roombed WHERE roomno=? and bedno=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, roomNo);
		preparedStatement.setString(2, bedNo);
		int result = preparedStatement.executeUpdate();
		if(result == 1)
			return true;
		else
			return false;
	}
	
	//增加房间信息
	public boolean addRoom(String roomNo, String bedNo, String bedType, String bedPrice, String floors, String remark) throws SQLException
	{
		Connection connection = DBConnection.getConnection("yzmedical");
		String sql = "INSERT yz_roombed(roomno,bedno,bedtype,bedprice,floornumber,remark) VALUES(?,?,?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, roomNo);
		preparedStatement.setString(2, bedNo);
		preparedStatement.setString(3, bedType);
		preparedStatement.setString(4, bedPrice);
		preparedStatement.setString(5, floors);
		preparedStatement.setString(6, remark);
		int result = preparedStatement.executeUpdate();
		if(result == 1)
			return true;
		else
			return false;
	}
	
	//修改房间信息
	public boolean changeRoom(String roomNo, String bedNo, String bedType, String bedPrice, String floors, String remark) throws SQLException
	{
		Connection connection = DBConnection.getConnection("yzmedical");
		String sql = "UPDATE yz_roombed SET bedtype=?,bedprice=?,floornumber=?,remark=? WHERE roomno=? and bedno=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, bedType);
		preparedStatement.setString(2, bedPrice);
		preparedStatement.setString(3, floors);
		preparedStatement.setString(4, remark);
		preparedStatement.setString(5, roomNo);
		preparedStatement.setString(6, bedNo);
		int result = preparedStatement.executeUpdate();
		if(result == 1)
			return true;
		else
			return false;
	}
}
