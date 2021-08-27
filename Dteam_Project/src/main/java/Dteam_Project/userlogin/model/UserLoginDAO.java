package Dteam_Project.userlogin.model;

import java.sql.SQLException;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserLoginDAO {	
	
	public UserLoginVO getLoginInfo(String user_id) throws SQLException;
	
	public int insertUser(UserLoginVO userLoginVO) throws SQLException;
	
	public int updateUser(UserLoginVO userLoginVO) throws SQLException;
	
	public int deleteUser(UserLoginVO userLoginVO) throws SQLException;		
	
	public UserLoginVO findID(String user_name) throws SQLException;
	
	public UserLoginVO findPW(String user_id) throws SQLException;	
		
	public void sendMail(UserLoginVO userLoginVO) throws Exception;


}
