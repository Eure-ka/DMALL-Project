package Dteam_Project.admin.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Dteam_Project.admin.model.AdminVO;
import Dteam_Project.admin.model.UserlistVO;
import Dteam_Project.admin.service.AdminService;
import Dteam_Project.user_join.service.UserService;

@Controller
@RequestMapping("ad")
public class AdminController {
	
	@Autowired
	private AdminService adminsevice;
	
	@GetMapping("login")
	public String login() throws Exception{
		return "admin/adminlogin";
	}
	
	@PostMapping("login")
	public String loginPost(HttpServletRequest request,Model model,HttpSession session) throws Exception{
		
		String admin_id = request.getParameter("admin_id");
		String admin_pw = request.getParameter("admin_pw");
		AdminVO adminVO = adminsevice.getLoginInfo(admin_id);
	
		if(adminVO  == null) {
			model.addAttribute("errMsg", "아이디가 없습니다.");
			return "error";
		}
		
		if(adminVO.getAdmin_pw().equals(admin_pw)) {
			session.setAttribute("session_adminvo", adminVO);
			session.setAttribute("admin_id", adminVO.getAdmin_id());
			model.addAttribute("adminvo", adminVO);
			return "admin/adminmain";
		}else {
			model.addAttribute("errMsg", "암호가 틀립니다.");
			return "error";
		}
	}
	
	@GetMapping("userlist")
	public String userlist_get(Model model) throws SQLException{
		
		List<UserlistVO> dbuserlist = adminsevice.getuserlist();
		
		
		
		
		model.addAttribute("userList", dbuserlist);
		
		return "admin/userlist";
	}
	
	@GetMapping("uptUser/{user_id}")
	public String uptUser_get(@PathVariable("user_id") String user_id,Model model) throws SQLException{
		

		
		UserlistVO userinfo = adminsevice.getuserinfo(user_id);
		
		
		model.addAttribute("userinfo",userinfo);
		
		return "/admin/userinfo";
	}
	
	@PostMapping("uptUser")
	public String uptUser_post(UserlistVO userlistVO,Model model) throws SQLException{
		
		adminsevice.uptuserinfo(userlistVO);
		
		
		return "redirect:/ad/userlist";
	}
	
	@PostMapping("deleteUser")
	public String delUser_get(HttpServletRequest request) throws SQLException{
		
		String user_id=request.getParameter("user_id");
		System.out.println(user_id);
		
		adminsevice.removeuser(user_id);
		
		return "redirect:/ad/userlist";
	}
}
