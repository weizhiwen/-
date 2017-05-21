package control;

import java.awt.HeadlessException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.AduserFun;

public class RegisterCheck {
	public void isAdd(String username,String password) {
		AduserFun loginFun = new AduserFun();
		try {
			if(loginFun.addUser(username, password)){
				JOptionPane.showMessageDialog(null, "注册新用户成功！");
			}else{
				JOptionPane.showConfirmDialog(null, "注册新用户失败，请稍后再试。");
			}
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
