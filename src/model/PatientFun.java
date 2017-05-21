package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.DBConnection;

public class PatientFun {
	//添加病人信息函数
	public boolean addPatientInfo(String patientName, String patientNumebr, String bedNumber, String medicalType, Date inHospitalTime, Date outHospitalTime) throws SQLException, ParseException {
		//现将string类型的数据转换成合适的数据再插入数据库
		Connection connection = DBConnection.getConnection("yzmedical");
		String sql = "INSERT yz_patientinfo(patientname,patientnumber,bednumber,medicaltype,inhospitaltime,outhospitaltime) VALUES(?,?,?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		//类型转换，string类型的转化为java.untl.Date类型，在转换为java.sql.Date
		preparedStatement.setString(1, patientName);
		preparedStatement.setString(2, patientNumebr);
		preparedStatement.setString(3, bedNumber);
		preparedStatement.setString(4, medicalType);
		preparedStatement.setDate(5, new java.sql.Date(inHospitalTime.getTime()));
		preparedStatement.setDate(6, new java.sql.Date(inHospitalTime.getTime()));
		int result = preparedStatement.executeUpdate();
		if(result == 1)
			return true;
		else
			return false;
	}
	
	//更新病人信息函数
	public boolean updatePatientInfo(String patientName, String patientNumebr, String bedNumber, String medicalType, Date inHospitalTime, Date outHospitalTime) throws SQLException, ParseException {
		Connection connection = DBConnection.getConnection("yzmedical");
		String sql = "UPDATE yz_patientinfo SET patientname=?,patientnumber=?,bednumber=?,medicaltype=?,inhospitaltime=?,outhospitaltime=?  WHERE patientnumber=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, patientName);
		preparedStatement.setString(2, patientNumebr);
		preparedStatement.setString(3, bedNumber);
		preparedStatement.setString(4, medicalType);
		preparedStatement.setDate(5, new java.sql.Date(inHospitalTime.getTime()));
		preparedStatement.setDate(6, new java.sql.Date(inHospitalTime.getTime()));
		preparedStatement.setString(7, patientNumebr);
		int result = preparedStatement.executeUpdate();
		if(result == 1)
			return true;
		else
			return false;
	}
	
	//删除病人信息
	public boolean delPatientInfo(String patientNumebr) throws SQLException, ParseException {
		//现将string类型的数据转换成合适的数据再插入数据库
		Connection connection = DBConnection.getConnection("yzmedical");
		String sql = "DELETE FROM yz_patientinfo WHERE patientnumber=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, patientNumebr);
		int result = preparedStatement.executeUpdate();
		if(result == 1)
			return true;
		else
			return false;
	}
	
	//查询医保类型表中的数量
	public int countMedicalType() throws SQLException {
		Connection connection = DBConnection.getConnection("yzmedical");
		String sql = "SELECT count(medicaltype) FROM yz_medicaltype";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		int count = resultSet.getInt(1);
		return count;
	}
	
	//查询医保类型函数
	public String[] selectMedicalType() throws SQLException {
		Connection connection = DBConnection.getConnection("yzmedical");
		String sql = "SELECT medicaltype FROM yz_medicaltype";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		//从数据库中获取医保类型的数目，而不是直接把数量值写死，方便以后添加删除医保类型
		int count = countMedicalType();
		String result[] = new String[count];
		int i = 0;
		while(resultSet.next()){
			String string = resultSet.getString("medicaltype");
			//把结果填充到数组中
			//Arrays.fill(result, string);此种方法是填充整个数组，而不是我想要的逐个填充数组数据
			result[i++] = string;
		}
		return result;
	}
	
	//获取当前日期函数
	public String getDate() {
		Date now = new Date();
	    SimpleDateFormat simpleDateFormat =  new SimpleDateFormat ("yyyy'/'M'/'dd");
	    String inDate = simpleDateFormat.format(now);
		return inDate;
	}
	
	//利用日期时间来生成病人的住院号
	public String getPatientNumber() {
		Date date = new Date();
	    SimpleDateFormat simpleDateFormat =  new SimpleDateFormat ("yyMMddHHmmss");
	    String patientNumber = simpleDateFormat.format(date);
		return patientNumber;
	}
	
	//查询病人信息的条数，无条件
	public  int getPatientCount() throws SQLException {
		Connection connection = DBConnection.getConnection("yzmedical");
		String sql = "SELECT count(id) FROM yz_patientinfo";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		int number = resultSet.getInt(1);
		return number;
	}
	
	//查询病人信息条数，有条件的模糊查询
	public  int getPatientCount(String patientName) throws SQLException {
		Connection connection = DBConnection.getConnection("yzmedical");
		StringBuffer sql = new StringBuffer();
		sql.append("select count(patientname) from yz_patientinfo ");
		sql.append("where patientname like ?");
		PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		preparedStatement.setString(1, "%"+patientName+"%");
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		int number = resultSet.getInt(1);
		return number;
	}
	
	//查询病人信息表,无条件
	public List<Patient> query() throws SQLException {
		List<Patient> result = new ArrayList<Patient>();
		Connection connection = DBConnection.getConnection("yzmedical");
		//根据condition是否有值来判断是否是按条件查询
		String sql = "SELECT * FROM yz_patientinfo";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		Patient patient = null;
		while(resultSet.next()){
			//把这行数据放到一个对象中
			patient = new Patient();
			patient.setId(resultSet.getInt("id"));
			patient.setPatientName(resultSet.getString("patientname"));
			patient.setPatientNumber(resultSet.getString("patientnumber"));
			patient.setBedNumber(resultSet.getString("bednumber"));
			patient.setMedicalType(resultSet.getString("medicaltype"));
			patient.setInHospitalTime(resultSet.getString("inhospitaltime"));
			patient.setOutHospitalTime(resultSet.getString("outhospitaltime"));
			//把这个对象放到List数组里面
			result.add(patient);
		}
		return result;
	}
	
	//查询病人信息表,无条件
		public List<Patient> query(String patientName) throws SQLException {
			List<Patient> result = new ArrayList<Patient>();
			Connection connection = DBConnection.getConnection("yzmedical");
			//根据condition是否有值来判断是否是按条件查询,此处也使用模糊查询，方便用户的简单输入，注意查询条数也要是模糊查询
			StringBuffer sql = new StringBuffer();
			sql.append("select * from yz_patientinfo ");
			sql.append("where patientname like ?");
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setString(1, "%"+patientName+"%");
			ResultSet resultSet = preparedStatement .executeQuery();
			Patient patient = null;
			while(resultSet.next()){
				//把这行数据放到一个对象中
				patient = new Patient();
				patient.setId(resultSet.getInt("id"));
				patient.setPatientName(resultSet.getString("patientname"));
				patient.setPatientNumber(resultSet.getString("patientnumber"));
				patient.setBedNumber(resultSet.getString("bednumber"));
				patient.setMedicalType(resultSet.getString("medicaltype"));
				patient.setInHospitalTime(resultSet.getString("inhospitaltime"));
				patient.setOutHospitalTime(resultSet.getString("outhospitaltime"));
				//把这个对象放到List数组里面
				result.add(patient);
			}
			return result;
		}
}
