package Dteam_Project.user_join.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dteam_Project.user_join.model.UserDAO;
import Dteam_Project.user_join.model.UserVO;
import Dteam_Project.userlogin.model.UserLoginVO;
//
@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserVO showUser(String user_id) throws SQLException {
		UserVO userVO = userDAO.showUser(user_id);
		return userVO;
	}

	@Override
	public int user_register(UserVO userVO) throws SQLException {
		int cnt = userDAO.user_register(userVO);
		return cnt;
	}

	@Override
	public int user_modify(UserVO userVO) throws SQLException {
		int cnt = userDAO.user_modify(userVO);
		return cnt;
	}

	@Override
	public int user_remove(UserLoginVO userLoginVO) throws SQLException {
		int cnt = userDAO.user_remove(userLoginVO);
		return cnt;
	}

}
