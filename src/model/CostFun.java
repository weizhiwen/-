package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.prism.Presentable;

import db.DBConnection;

public class CostFun {
	//查询费用类型信息的条数
	public int getCostTypeCount() throws SQLException {
		Connection connection = DBConnection.getConnection("yzmedical");
		String sql = "SELECT count(id) FROM yz_costtype";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		int number = resultSet.getInt(1);
		return number;
	}
	
	//查询费用类型信息表
	public String[][] query() throws SQLException {
		CostFun costFun = new CostFun();
		int count = costFun.getCostTypeCount();
		String result[][] = new String[count][2];
		Connection connection = DBConnection.getConnection("yzmedical");
		String sql = "SELECT * FROM yz_costtype ORDER BY id";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement .executeQuery();
		int i = 0;
		while(resultSet.next()){
			//把这行数据放到一个对象中
			result[i][0] = resultSet.getString("id");
			result[i][1] = resultSet.getString("costtype");
			i++;
		}
		return result;
	}
	
	//添加病人费用信息
	public boolean addCost(String patientname, String patientnumber, int rows, Object cost[][]) throws SQLException {
		Connection connection = DBConnection.getConnection("yzmedical");
		String sql = "INSERT yz_patientcost VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		for(int i = 0; i < rows; i++)
		{
			preparedStatement.setString(1, patientname);
			preparedStatement.setString(2, patientnumber);
			preparedStatement.setString(3, (String) cost[i][0]);
			preparedStatement.setString(4, (String) cost[i][1]);
			preparedStatement.setString(5, (String) cost[i][2]);
			preparedStatement.setString(6, (String) cost[i][3]);
			preparedStatement.setString(7, (String) cost[i][4]);
			preparedStatement.setString(8, (String) cost[i][5]);
			preparedStatement.setString(9, (String) cost[i][6]);
			preparedStatement.executeUpdate();
		}
		return true;
	}
	
	//查询某一位病人的费用信息的条数
	public int getPatientCostCount(String patientNumber) throws SQLException {
		Connection connection = DBConnection.getConnection("yzmedical");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(itemname) FROM yz_patientcost ");
		sql.append("where patientnumber=?");
		PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		preparedStatement.setString(1, patientNumber);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		int number = resultSet.getInt(1);
		return number;
	}
	
	//查询某一位病人的费用信息
	public List<Medicine> getMedicineCost(String patientNumber) throws SQLException
	{
		List<Medicine> result = new ArrayList<Medicine>();
		CostFun costFun = new CostFun();
		int count = costFun.getPatientCostCount(patientNumber);
		Medicine medicines[] = new Medicine[count];
		Connection connection = DBConnection.getConnection("yzmedical");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM yz_patientcost ");
		sql.append("where patientnumber= ?");
		PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		preparedStatement.setString(1, patientNumber);
		ResultSet resultSet = preparedStatement.executeQuery();
		Medicine medicine = null;
		while(resultSet.next()){
			//把这行数据放到一个对象中
			medicine = new Medicine();
			medicine.setItemName(resultSet.getString("itemname"));
			medicine.setCostType(resultSet.getString("costtype"));
			medicine.setKind(resultSet.getString("kind"));
			medicine.setAmount(resultSet.getInt("amount"));
			medicine.setUnit(resultSet.getString("unit"));
			medicine.setPrePrice(resultSet.getFloat("preprice"));
			medicine.setCost(resultSet.getFloat("thiscost"));
			//把这个对象放到List数组里面
			result.add(medicine);
		}
		return result;
	}
	
	//删除病人的费用信息
	public boolean delCost(String patientNumber, String itemName) throws SQLException {
		Connection connection = DBConnection.getConnection("yzmedical");			
		String sql = "DELETE FROM yz_patientcost WHERE patientnumber=? and itemname=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, patientNumber);
		preparedStatement.setString(2, itemName);
		int result = preparedStatement.executeUpdate();
		if(result == 1)
			return true;
		else
			return false;
	}
	
	//查询费用单价，下面使用，查询的数据表这里选择的是病人费用表，没有直接从药品表中查，
	//为了避免病人费用表中的价格和药品表中的不一样这种情况，或者各个病人的同样名称药品价格也有不同
	public float getItemPreprice(String patientNumber, String itemName) throws SQLException{
		float preprice = 0;
		Connection connection = DBConnection.getConnection("yzmedical");
		String sql = "SELECT preprice FROM yz_patientcost WHERE patientnumber=? and itemname=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, patientNumber);
		preparedStatement.setString(2, itemName);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()){
			preprice = resultSet.getFloat("preprice");
		}
		return preprice;
	}
	
	//修改病人的费用数量，注意把总钱数也修改了，容易忽略此处，要增加一个查询单价的函数
	public boolean changeamount(String patientNumber, String itemName, int amount) throws SQLException {
		CostFun costFun = new CostFun();
		float preprice = costFun.getItemPreprice(patientNumber, itemName);
		Connection connection = DBConnection.getConnection("yzmedical");
		String sql = "UPDATE yz_patientcost SET amount=?, thiscost=? WHERE patientnumber=? and itemname=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, amount);
		preparedStatement.setFloat(2, preprice*amount);
		preparedStatement.setString(3, patientNumber);
		preparedStatement.setString(4, itemName);
		int result = preparedStatement.executeUpdate();
		if(result == 1)
			return true;
		else
			return false;
	}
	
	//分组查询各项的费用，已实现，在控制台上尝试，得到想要的命令格式
	public String[][] getPreCost(String patientNumber) throws SQLException {
		CostFun costFun = new CostFun();
		int count = costFun.getPatientCostCount(patientNumber);
		String [][]costs= new String[count][2];
		Connection connection = DBConnection.getConnection("yzmedical");
		String sql = "SELECT costtype,sum(thiscost) totalcost FROM yz_patientcost WHERE patientnumber=? GROUP BY costtype ORDER BY costtype";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, patientNumber);
		ResultSet resultSet = preparedStatement.executeQuery();
		int i = 0;
		while(resultSet.next())
		{
			costs[i][0] = resultSet.getString("costtype");
			costs[i][1] = resultSet.getString("totalcost");
			i++;
		}
		return costs;
	}
	
	//查询各项费用的总和
	public String getTotalCost(String patientNumber) throws SQLException {
		String totalCost = null;
		Connection connection = DBConnection.getConnection("yzmedical");
		String sql = "select sum(thiscost) totalcost  from yz_patientcost where patientnumber=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, patientNumber);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			totalCost = resultSet.getString("totalcost");
		}
		return totalCost;
	}
}
