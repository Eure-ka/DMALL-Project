package Dteam_Project.user_join.service;

import java.sql.SQLException;
import java.util.List;

import Dteam_Project.user_join.model.UserVO;
import Dteam_Project.userlogin.model.UserLoginVO;
//
public interface UserService {
	
	public UserVO showUser(String user_id) throws SQLException;
	
	public int user_register(UserVO userVO) throws SQLException;
	
	public int user_modify(UserVO userVO) throws SQLException;
	
	public int user_remove(UserLoginVO userLoginVO) throws SQLException;
}
