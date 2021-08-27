package Dteam_Project.admin.service;

import java.sql.SQLException;
import java.util.List;

import Dteam_Project.admin.model.AdminVO;
import Dteam_Project.admin.model.UserlistVO;

public interface AdminService {
	
	public AdminVO getLoginInfo(String admin_id) throws SQLException;
	
	public List<UserlistVO> getuserlist() throws SQLException;
	
	public UserlistVO getuserinfo(String user_id) throws SQLException;
	
	public int uptuserinfo(UserlistVO userlistVO) throws SQLException;
	
	public int removeuser(String user_id) throws SQLException;
}
