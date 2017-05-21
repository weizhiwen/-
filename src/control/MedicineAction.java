package control;

import java.sql.SQLException;
import java.util.List;

import model.Medicine;
import model.MedicineFun;

public class MedicineAction {
	//有条件的查询药品信息
	public Object[][] getMedicine(String costType) throws SQLException{
		MedicineFun medicineFun = new MedicineFun();
		int count = medicineFun.getTypeCount(costType);
		Object result[][] = new Object[count][5];
		List<Medicine> medicines = medicineFun.query(costType);
		//将一个patient的List转化为一个object数组
		for(int i = 0; i < count; i++){
			result[i][0] = medicines.get(i).getItemName();
			result[i][1] = medicines.get(i).getCostType();
			result[i][2] = medicines.get(i).getKind();
			result[i][3] = medicines.get(i).getUnit();
			result[i][4] = String.valueOf(medicines.get(i).getPrePrice());
		}
		return result; 
	}
	
	//模糊查询药品信息
	public Object[][] searchMedicine(String costType, String medicineName) throws SQLException{
		MedicineFun medicineFun = new MedicineFun();
		int count = medicineFun.getSearchCount(costType,medicineName);
		Object result[][] = new Object[count][5];
		List<Medicine> medicines = medicineFun.getSearchContent(costType,medicineName);
		//将一个patient的List转化为一个object数组
		for(int i = 0; i < count; i++){
			result[i][0] = medicines.get(i).getItemName();
			result[i][1] = medicines.get(i).getCostType();
			result[i][2] = medicines.get(i).getKind();
			result[i][3] = medicines.get(i).getUnit();
			result[i][4] = String.valueOf(medicines.get(i).getPrePrice());
		}
		return result; 
	}
}
