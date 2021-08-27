package Dteam_Project.userlogin.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import Dteam_Project.userlogin.model.UserLoginVO;

public interface UserLoginService {
	
	public UserLoginVO getLoginInfo(String user_id) throws SQLException;
	
	public int insertUser(UserLoginVO userLoginVO) throws SQLException;
	
	public int updateUser(UserLoginVO userLoginVO) throws SQLException;
	
	public int deleteUser(UserLoginVO userLoginVO) throws SQLException;
	
	public UserLoginVO findID(String user_name) throws SQLException;

	public UserLoginVO findPW(String user_id) throws SQLException;
	
	public void sendMail(String pwd, String address);
	

}
