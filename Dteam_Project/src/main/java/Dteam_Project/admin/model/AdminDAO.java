package Dteam_Project.admin.model;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminDAO {
	
	public AdminVO getLoginInfo(String admin_id) throws SQLException;
	
	public List<UserlistVO> getuserlist() throws SQLException;
	
	public UserlistVO getuserinfo(String user_id) throws SQLException;
	
	public int uptuserinfo(UserlistVO userlistVO) throws SQLException;
	
	public int removeuser(String user_id) throws SQLException;
}
