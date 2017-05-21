package control;

import java.sql.SQLException;

import model.AduserFun;

public class LoginCheck {
	public boolean isLoginSuccess(String username, String password) throws SQLException {
		AduserFun loginFun = new AduserFun();
		boolean isOk = loginFun.select(username, password);
		return isOk;
	}
}
