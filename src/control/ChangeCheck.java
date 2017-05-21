package control;

import java.sql.SQLException;

import javax.swing.JOptionPane;


import model.AduserFun;

public class ChangeCheck {
	public void ischange(String username, String oldpassword, String newpassword) {
		LoginCheck loginCheck = new LoginCheck();
		try {//验证是否可以修改密码,即验证老密码
			if(loginCheck.isLoginSuccess(username,oldpassword)){
				AduserFun loginFun = new AduserFun();
				if(loginFun.changePassword(username, newpassword)){
					JOptionPane.showMessageDialog(null, "修改密码成功！");
				}else{
					JOptionPane.showMessageDialog(null, "修改密码失败！");
				}
				
			}
			else {
				JOptionPane.showMessageDialog(null, "用户名和密码不一致");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
