package Dteam_Project.userlogin.service;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dteam_Project.userlogin.model.UserLoginDAO;
import Dteam_Project.userlogin.model.UserLoginVO;
import Dteam_Project.userlogin.service.UserLoginService;


@Service
public class UserLoginServiceImpl  implements UserLoginService{
	
	@Autowired
	private UserLoginDAO userLoginDAO;

	@Override
	public UserLoginVO getLoginInfo(String user_Id) throws SQLException {
		UserLoginVO userLoginVO = userLoginDAO.getLoginInfo(user_Id);		
		return userLoginVO;
	}
	
	public int insertUser(UserLoginVO userLoginVO) throws SQLException {
		UserLoginVO dbUserLoginVO = userLoginDAO.getLoginInfo(userLoginVO.getUser_id());
		int cnt=0;
		if(dbUserLoginVO == null) {		
			 cnt = userLoginDAO.insertUser(userLoginVO);			
		}else {
			// 이미 가입된 아이디입니다. 
			cnt = -1;
		}
		
		return cnt; // 0  -1 : 이미 가입된 아이디입니다.
	}

	@Override
	public int updateUser(UserLoginVO userLoginVO) throws SQLException {
		int cnt = userLoginDAO.updateUser(userLoginVO);
		return cnt;
	}

	@Override
	public int deleteUser(UserLoginVO userLoginVO) throws SQLException {
		int cnt = userLoginDAO.deleteUser(userLoginVO);
		return cnt;
	}	
	
	@Override
	public UserLoginVO findID(String user_name) throws SQLException {	
		UserLoginVO userfindVO = userLoginDAO.findID(user_name);		
		return userfindVO;
		
	}	
	

	@Override
	public UserLoginVO findPW(String user_id) throws SQLException {	
		UserLoginVO userfindpwVO = userLoginDAO.findPW(user_id);		
		return userfindpwVO;
		
	}	
	
	
	@Autowired
	   private JavaMailSender mailSender;
	   private static final String FROM_ADDRESS = "js6988@naver.com";
	 
	   
	@Override
	//동기화는 메일이 보내지는 것이 끝날떄까지 / 비동기화는 메일이 덜보내져도 일단 다음작업 
	@Async
		public void sendMail(String pwd, String address) {
		
		 // TODO Auto-generated method stub
		 SimpleMailMessage message = new SimpleMailMessage();
	     message.setTo(address);
	     message.setFrom(FROM_ADDRESS);
	     message.setSubject("DteamProject 비밀번호찾기 안내 메일입니다.");
	     message.setText("비밀번호는 "+ pwd +" 입니다.");
	     //System.out.println(pwd+","+address);
	     mailSender.send(message);
		}	


}
