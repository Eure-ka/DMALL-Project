package Dteam_Project.admin.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dteam_Project.admin.model.AdminDAO;
import Dteam_Project.admin.model.AdminVO;
import Dteam_Project.admin.model.UserlistVO;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminDAO admindao;

	@Override
	public AdminVO getLoginInfo(String admin_id) throws SQLException {
		
		AdminVO adminVO =admindao.getLoginInfo(admin_id);
		
		
		return adminVO;
	}

	@Override
	public List<UserlistVO> getuserlist() throws SQLException {
		
		List<UserlistVO> userlist = admindao.getuserlist();
		
		return userlist;
	}

	@Override
	public UserlistVO getuserinfo(String user_id) throws SQLException {
		
		UserlistVO userinfo=admindao.getuserinfo(user_id);

		return userinfo;
	}

	@Override
	public int uptuserinfo(UserlistVO userlistVO) throws SQLException {
		
		int cnt = admindao.uptuserinfo(userlistVO);
		
		return cnt;
	}

	@Override
	public int removeuser(String user_id) throws SQLException {
		
		int cnt =admindao.removeuser(user_id);
		
		return cnt;
	}
	
	
}
