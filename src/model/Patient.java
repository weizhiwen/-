package model;

public class Patient {
	private int id;
	private String patientName;//病人姓名
	private String patientNumber;//住院号
	private String bedNumber;//病区床号，数据表暂时不建立，手动输入
	private String medicalType;//医保类型
	private String inHospitalTime;//入院时间
	private String outHospitalTime;//出院时间
	private float cost;//各项费用之和
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientNumber() {
		return patientNumber;
	}
	public void setPatientNumber(String patientNumber) {
		this.patientNumber = patientNumber;
	}
	public String getBedNumber() {
		return bedNumber;
	}
	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}
	public String getMedicalType() {
		return medicalType;
	}
	public void setMedicalType(String medicalType) {
		this.medicalType = medicalType;
	}
	public String getInHospitalTime() {
		return inHospitalTime;
	}
	public void setInHospitalTime(String inHospitalTime) {
		this.inHospitalTime = inHospitalTime;
	}
	public String getOutHospitalTime() {
		return outHospitalTime;
	}
	public void setOutHospitalTime(String outHospitalTime) {
		this.outHospitalTime = outHospitalTime;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
}
