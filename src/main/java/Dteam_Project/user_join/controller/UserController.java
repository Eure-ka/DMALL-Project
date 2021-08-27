package Dteam_Project.user_join.controller;

import java.net.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Dteam_Project.user_join.service.UserService;
import Dteam_Project.user_join.model.UserVO;
import Dteam_Project.userlogin.model.UserLoginVO;
import Dteam_Project.userlogin.service.UserLoginService;
//import Dteam_Project.userlogin.model.UserLoginVO;
import Dteam_Project.utils.ScriptUtils;


@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserLoginService userLoginService;

	@GetMapping("register")
	public String user_registerForm(HttpSession session) throws Exception{
		
		return "user/user_reg";
	}
	
	@PostMapping("register") 
	public String user_registerPost(UserVO userVO, Model model) throws Exception {
		
		model.addAttribute("new_user",userVO);
		
		userService.user_register(userVO);
//		System.out.println(""+userVO.getUser_id());
		return "redirect:/login";
		
	}
	
		@GetMapping("check")
		public String userInfoCheck(HttpSession session, Model model,HttpServletResponse response) throws Exception {
			
			UserLoginVO userLoginVO = (UserLoginVO)session.getAttribute("userLoginVO");
			String username = (String)session.getAttribute("username"); 
//			여러 필드를 vo형태로 한번에 가져오려면 session.setAttribute("userLoginVO",userLoginVO)해줘야 함. 
			//		UserLoginVO userLoginVO = (UserLoginVO)session.getAttribute("username");
			//		UserLoginController에서 session에 저장된 필드1개값만 String으로 불러옴. 
			
			
			if (username != null) {
				
				ScriptUtils.alertAndMovePage(response, "카카오나 네이버의 회원정보는 확인이 불가능합니다 추후에 업데이트 하겠습니다.", "/main/home");
				
				return "";
			}
			
			
			if (userLoginVO == null) {
								
				ScriptUtils.alertAndMovePage(response, "회원정보는 로그인 후에 확인 가능합니다", "/login");
				return "";
				
			}else {
				
				UserLoginVO dbuserLoginVO = userLoginService.getLoginInfo(userLoginVO.getUser_id());
				
				System.out.println("dbuserLoginVO:"+dbuserLoginVO);
				
				model.addAttribute("dbuserLoginVO",dbuserLoginVO);
			}
			
			
			return "user/user_infoCheck";

		}
		
		@GetMapping("modify") 
		public String userModifyForm(HttpSession session, Model model) throws Exception {
			
			UserLoginVO userLoginVO = (UserLoginVO)session.getAttribute("userLoginVO");
			UserLoginVO dbuserLoginVO = userLoginService.getLoginInfo(userLoginVO.getUser_id());
			model.addAttribute("dbuserLoginVO",dbuserLoginVO);
			
			return "user/user_modification";
		}
		
		@PostMapping("modify") 
		public String userModifySubmit(UserLoginVO userLoginVO) throws Exception {
			
			int cnt1 = userLoginService.updateUser(userLoginVO);
			System.out.println(cnt1);
			
			return "redirect:/main/home";	//리다이렉트를 통해 다른 컨트롤러를 호출할 수 있음
		}
		
		@GetMapping("remove")
		public String userRemoveChk() throws Exception {
			return "user/user_remove";
		}
		
		@PostMapping("remove")
		public String userRemoveSubmit(HttpSession session) throws Exception {
			UserLoginVO userLoginVO = new UserLoginVO();
			
			userLoginVO = (UserLoginVO) session.getAttribute("userLoginVO");
			session.invalidate();
			
			int cnt2 = userLoginService.deleteUser(userLoginVO);
			System.out.println(cnt2);
			
			return "redirect:/main/home";
		}
		
	
}