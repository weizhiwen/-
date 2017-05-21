package control;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.List;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;
import javax.swing.JOptionPane;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.sun.org.apache.xml.internal.serializer.ElemDesc;

import model.CostFun;
import model.Medicine;

public class CostAction {
	//录入病人的费用
	public void addPatientCost(String patientname, String patientnumber, int rows, Object cost[][]) throws SQLException{
		CostFun costFun = new CostFun();
		if(costFun.addCost(patientname, patientnumber, rows, cost))
			JOptionPane.showMessageDialog(null, "病人出院费用信息录入成功");
		else {
			JOptionPane.showMessageDialog(null, "病人出院费用信息录入失败，请稍后再试");
		}
	}
	
	//根据病人的住院号查询病人的费用信息
	public Object[][] getPatientCost(String patientNumber) throws SQLException
	{
		CostFun costFun = new CostFun();
		int count = costFun.getPatientCostCount(patientNumber);
		List<Medicine> medicines = costFun.getMedicineCost(patientNumber);
		Object object[][] = new Object[count][8];
		for(int i = 0; i < count; i++)
		{
			object[i][0] = i+1;
			object[i][1] = medicines.get(i).getItemName();
			object[i][2] = medicines.get(i).getCostType();
			object[i][3] = medicines.get(i).getKind();
			object[i][4] = medicines.get(i).getAmount();
			object[i][5] = medicines.get(i).getUnit();
			object[i][6] = medicines.get(i).getPrePrice();
			object[i][7] = medicines.get(i).getCost();
		}
		return object;
	}
	
	//删除病人费用信息
	public boolean delPatientCost(String patientNumber, String itemName) throws SQLException{
		CostFun costFun = new CostFun();
		if(costFun.delCost(patientNumber, itemName))
			JOptionPane.showMessageDialog(null, "病人出院费用信息删除成功");
		else {
			JOptionPane.showMessageDialog(null, "病人出院费用信息删除失败，请稍后再试");
		}
		return true;
	}
	
	//修改病人的费用信息
	public void changeCostAmount(String patientNumber, String itemName, String amount) throws HeadlessException, SQLException {
		CostFun costFun = new CostFun();
		int costAmount = Integer.valueOf(amount);
		if(costFun.changeamount(patientNumber, itemName, costAmount))
			JOptionPane.showMessageDialog(null, "病人"+itemName+"费用数量修改成功");
		else {
			JOptionPane.showMessageDialog(null, "病人"+itemName+"费用数量修改失败，请稍后再试");
		}
	}
	
	//得到病人的各项费用花费以及总费用
	public Object[][] getCost(String patientNumber) throws SQLException {
		CostFun costFun = new CostFun();
		int count = costFun.getCostTypeCount();
		String costs[][] = costFun.getPreCost(patientNumber);
		String totalCost = costFun.getTotalCost(patientNumber);
		Object result[][] = new Object[count+1][3];
		for(int i = 0; i < count; i++)
		{
			result[i][0] = i+1;
			result[i][1] = costs[i][0];
			result[i][2] = costs[i][1];
		}
		result[count][1] = "合计";
		result[count][2] = totalCost;
		return result;
	}
}
