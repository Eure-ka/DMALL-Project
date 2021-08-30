package Dteam_Project.userlogin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import Dteam_Project.userlogin.model.UserLoginDAO;
import Dteam_Project.userlogin.model.UserLoginVO;
import Dteam_Project.userlogin.service.UserLoginService;
import Dteam_Project.utils.ScriptUtils;


@Controller
public class UserLoginController {
	// logger loggerfactory import 한거 잘고르기 여러개임	
		private static Logger logger = LoggerFactory.getLogger(UserLoginController.class);
			
		@Autowired
		private UserLoginDAO userLoginDAO;		
		@Autowired
		private UserLoginService userService;
		
		
		@GetMapping("findID")
		public String idfindGet() throws Exception {
			return "user_Login/findIDPW";
		}
		
		
		
		
		
		// 아이디 찾기 실행
		@PostMapping("findID")
		public String findIDPost(HttpServletRequest request, Model model, HttpSession session,HttpServletResponse response) throws Exception {
			
			String user_name = request.getParameter("user_name");			
			String user_phone = request.getParameter("user_phone");
			
			logger.debug("name     :"+ user_name);
			logger.debug("phone    :"+ user_phone);	
			
			UserLoginVO findIDVO = userService.findID(user_name);
			logger.debug("userLoginVO" + findIDVO);			
			
			
			if(findIDVO == null) { 		
				model.addAttribute("errMsg", "해당하는 이름이 없습니다");
				return "user_Login/findIDPW";	
				//model.addAttribute("errMsg", "해당하는 아이디가 없습니다");
				//return "user_Login/finderror";	
				
			}	
			
			logger.debug("db phone    :" + findIDVO.getUser_phone());
			
			//check
			if(findIDVO.getUser_phone().equals(user_phone)) { //자바에서 문자열이 같은지 비교할떄는 equals 메소드 사용
				
				
				ScriptUtils.alertAndMovePage(response, "아이디는 "+findIDVO.getUser_id()+" 입니다.", "/login");
				System.out.println("js process end");
				return "index";
				
			}else {
				model.addAttribute("errMsg", "전화번호가 틀립니다.");
				return "user_Login/findIDPW";
			}	
		}	
		
		
		@GetMapping("findPW")
		public String findPWGet() throws Exception {
			return "user_Login/findIDPW";
		}

		
		// 비번 찾기 실행
		@PostMapping("findPW")
		public String findPW(UserLoginVO vo,HttpServletRequest request, Model model, HttpSession session,HttpServletResponse response) throws Exception {
				
				
				String user_id = request.getParameter("user_id");			
				String user_email = request.getParameter("user_email");				
				
				logger.debug("id     :"+ user_id);
				logger.debug("email    :"+ user_email);	
				
				UserLoginVO findIDVO = userService.findPW(user_id);
				logger.debug("userLoginVO" + findIDVO);			
				
				
				if(findIDVO == null) { 
					model.addAttribute("errMsg", "등록되지 않은 아이디입니다.");
					return "user_Login/findIDPW";				
				}	
				
				logger.debug("db email    :" + findIDVO.getUser_email());
				
				
				
				//check
				if(findIDVO.getUser_email().equals(user_email)) { //자바에서 문자열이 같은지 비교할떄는 equals 메소드 사용			
				
					userService.sendMail(findIDVO.getUser_pw(), user_email);
					
					//ScriptUtils.alertAndMovePage(response, "비밀번호는 "+findIDVO.getUser_pw()+" 입니다.", "/login");
					//System.out.println("js process end");
					//return "index";
					
					return "redirect:/login";
					
				} else {
					model.addAttribute("errMsg", "이메일이 틀립니다");
					return "user_Login/findIDPW";
//					model.addAttribute("errMsg", "이메일이 틀립니다");	
//					return "user_Login/finderror";				
				
				}
				
			

					
			
		}
		
		
				
		@GetMapping("login")
		public String login() throws Exception {
			//sendMail("rlgus951@naver.com");
			
			return "user_Login/login";
		}
		
		@PostMapping("login")
		public String loginPost(HttpServletRequest request, Model model, HttpSession session,HttpServletResponse response) throws Exception {
			
			////getParameter는 위에서 VO에서 가져오는것을 선언 안했을떄 ()부분은 html부분에서 선언한것을 가져온다
			String user_id = request.getParameter("user_id");
			String user_pw = request.getParameter("user_pw");
			
			logger.debug("login     :"+ user_id);
			logger.debug("loginPass :"+ user_pw);					
			
			//return "loginSuccess";
			UserLoginVO userLoginVO = userService.getLoginInfo(user_id);
			logger.debug("userLoginVO" + userLoginVO);
			
			if(userLoginVO  ==null) {		
				model.addAttribute("errMsg", "아이디가 존재하지 않습니다.");
				return "user_Login/login";	
				//model.addAttribute("errMsg", "등록된 아이디가 없습니다");
				//return "user_Login/error";		
			}
			
			logger.debug("db pass" + userLoginVO.getUser_pw());
			
			//check
			if(userLoginVO.getUser_pw().equals(user_pw)) { //자바에서 문자열이 같은지 비교할떄는 equals 메소드 사용
				

				
				ScriptUtils.alertAndMovePage(response, userLoginVO.getUser_id()+"님 환영합니다.", "/main/home");
				session.setAttribute("userLoginVO", userLoginVO); 
				
				
				session.setAttribute("userid", userLoginVO.getUser_id()); 
				
				return "index";
				
			}else {
				model.addAttribute("errMsg", "암호가 틀립니다");
				return "user_Login/login";	
			}
		}
		
		
		
		@GetMapping("logout")
		public String logout(HttpSession session,HttpServletResponse response) throws Exception {
			// session.removeAttribute("userVO"); 하나의 값만 삭제
			session.invalidate(); //세션에 있는 모든 값을 삭제
			
			ScriptUtils.alertAndMovePage(response,"로그아웃 되었습니다.", "/main/home");
			
			return "index"; //redirect를 리턴하면 해당 mapping으로 url이 이동된다
		}
		
		
		
	
		
		
//		// https://gofnrk.tistory.com/82 참고
//		@Autowired
//		public JavaMailSender javaMailSender;
// 		//동기화는 메일이 보내지는 것이 끝날떄까지 / 비동기화는 메일이 덜보내져도 일단 다음작업 
//		@Async
//		public void sendMail(UserLoginVO vo, String div ,String email) {		
//			
//			SimpleMailMessage simpleMessage = new SimpleMailMessage();
//			simpleMessage.setFrom("rlgus951@naver.com");  // NAVER, DAUM, NATE일 경우 넣어줘야 함  //"보낸사람@naver.com"
//			simpleMessage.setTo(email);
//			simpleMessage.setSubject("[Dteam] 이메일 인증 ");
//			simpleMessage.setText("인증번호: 123456\n");
//			javaMailSender.send(simpleMessage);
//		}
//		
		
		
		
		
		

}
