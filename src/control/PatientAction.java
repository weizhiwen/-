package control;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import model.Patient;
import model.PatientFun;

public class PatientAction {
	//录入病人信息
	public void addPatient(String patientName,String patientNumber,String bedNumber,String medicalType,String inHospitalTime,String outHospitalTime) throws SQLException, HeadlessException, ParseException {
		PatientFun patientFun = new PatientFun();
	    SimpleDateFormat simpleDateFormat =  new SimpleDateFormat ("yyyy'/'M'/'dd");
	    Date inDate = simpleDateFormat.parse(inHospitalTime);
	    Date outDate = simpleDateFormat.parse(outHospitalTime);
		if(patientFun.addPatientInfo(patientName,patientNumber,bedNumber,medicalType,inDate,outDate)){
			JOptionPane.showMessageDialog(null, "病人信息录入成功！");
		}
		else {
			JOptionPane.showMessageDialog(null, "病人信息录入失败！");
		}
	}
	
	//维护和查询病人信息，通过添加一个条件使代码复用性更好,但操作起来要改的地方很多，暂时放弃这种方法
	public Object[][] getPatient() throws SQLException{
		PatientFun patientFun = new PatientFun();
		int count = patientFun.getPatientCount();
		Object result[][] = new Object[count][7];
 		List<Patient> patients = patientFun.query();
		//将一个patient的List转化为一个object数组
		for(int i = 0; i < count; i++){
			result[i][0] = patients.get(i).getId();
			result[i][1] = patients.get(i).getPatientName();
			result[i][2] = patients.get(i).getPatientNumber();
			result[i][3] = patients.get(i).getBedNumber();
			result[i][4] = patients.get(i).getMedicalType();
			result[i][5] = patients.get(i).getInHospitalTime();
			result[i][6] = patients.get(i).getOutHospitalTime();
		}
		return result; 
	}
	//有条件的查询病人信息
	public Object[][] getPatient(String patientName) throws SQLException{
		PatientFun patientFun = new PatientFun();
		int count = patientFun.getPatientCount(patientName);
		Object result[][] = new Object[count][7];
 		List<Patient> patients = patientFun.query(patientName);
		//将一个patient的List转化为一个object数组
		for(int i = 0; i < count; i++){
			result[i][0] = patients.get(i).getId();
			result[i][1] = patients.get(i).getPatientName();
			result[i][2] = patients.get(i).getPatientNumber();
			result[i][3] = patients.get(i).getBedNumber();
			result[i][4] = patients.get(i).getMedicalType();
			result[i][5] = patients.get(i).getInHospitalTime();
			result[i][6] = patients.get(i).getOutHospitalTime();
		}
		return result; 
	}
	
	//更新病人信息
	public void updatePatient(String patientName,String patientNumber,String bedNumber,String medicalType,String inHospitalTime,String outHospitalTime) throws SQLException, HeadlessException, ParseException {
		PatientFun patientFun = new PatientFun();
	    SimpleDateFormat simpleDateFormat =  new SimpleDateFormat ("yyyy'/'M'/'dd");
	    Date inDate = simpleDateFormat.parse(inHospitalTime);
	    Date outDate = simpleDateFormat.parse(outHospitalTime);
		if(patientFun.updatePatientInfo(patientName,patientNumber,bedNumber,medicalType,inDate,outDate)){
			JOptionPane.showMessageDialog(null, "病人信息修改成功！");
		}
		else {
			JOptionPane.showMessageDialog(null, "病人信息修改失败！");
		}
	}
	
	//删除病人信息
	public void delPatient(String patientNumber) throws SQLException, ParseException {
		PatientFun patientFun = new PatientFun();
		if(patientFun.delPatientInfo(patientNumber)){
			JOptionPane.showMessageDialog(null, "病人信息删除成功！");
		}
		else {
			JOptionPane.showMessageDialog(null, "病人信息删除失败！");
		}
	}
}
