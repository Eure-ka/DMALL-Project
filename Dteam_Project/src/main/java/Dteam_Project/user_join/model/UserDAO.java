package Dteam_Project.user_join.model;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import Dteam_Project.userlogin.model.UserLoginVO;

@Repository
@Mapper		
public interface UserDAO {
	
	public UserVO showUser(String user_id) throws SQLException;
	
	public int user_register(UserVO userVO) throws SQLException;
	
	public int user_modify(UserVO userVO) throws SQLException;
	
	public int user_remove(UserLoginVO userLoginVO) throws SQLException;
}


