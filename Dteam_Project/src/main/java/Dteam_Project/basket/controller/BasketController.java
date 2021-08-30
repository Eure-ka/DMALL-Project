package Dteam_Project.basket.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Dteam_Project.basket.model.BasketVO;
import Dteam_Project.basket.service.BasketService;
import Dteam_Project.userlogin.model.UserLoginVO;
import Dteam_Project.utils.ScriptUtils;



@Controller
@RequestMapping("basket")
public class BasketController {
	
	@Autowired
	private BasketService basketService;
	
	
	
	
	@GetMapping("basketlist")  //장바구니 페이지
	public String basketlist(BasketVO basketVO, Model model, @RequestParam(name = "sumtest", defaultValue = "0") Integer sumtest[],HttpSession session,HttpServletResponse response) throws Exception{
		
		List<BasketVO> basketList = basketService.getBasket(basketVO);
		
		model.addAttribute("basketList" , basketList);
		
		UserLoginVO userLoginVO = (UserLoginVO)session.getAttribute("userLoginVO");
		
		if (userLoginVO == null) {
		
			ScriptUtils.alertAndMovePage(response, "장바구니 담기는 로그인 후에 확인 가능합니다", "/login");
		
			return "";
		
		}else {
			
			return "basket/basketlist";
		}
		 
	}
	
	
	
	//@PostMapping("basketlist")
	//public String basketsum(BasketVO basketVO, Model model, @RequestParam() Integer sumtest[]) throws Exception{
	//	System.out.println("합 :" + sumtest);
		
	
	//	return "basket/basketlist";
//	}
	
	@GetMapping("insert")  
	public String insert() throws Exception{
		
		System.out.println("get:");
		
		return "basket/insert";
	}
	
	@PostMapping("insert") 
	public String insertSubmit(@RequestParam("infoBk") Integer infoBk, @RequestParam("resultNumber") Integer resultNumber, BasketVO basketVO, Model model,HttpSession session,HttpServletResponse response) throws Exception{
		String userid =(String)session.getAttribute("userid");	
		
		System.out.println("product_code:"+infoBk); 
		System.out.println("resultNumber:"+resultNumber);
		
		basketVO.setBk_resultnumber(resultNumber);
		basketVO.setProduct_code(infoBk);
		basketVO.setUser_id(userid);   // VO에 set으로 넣는다
		
		System.out.println("게터확인"+basketVO.getUser_id()); // 아이디가 들어갔는지 확인
		
		basketService.insertProduct_info(basketVO);
		
		return "redirect:/basket/basketlist";
	
	}

	@PostMapping("remove")
	public String removeSubmit(@RequestParam("delBkNum") Integer delBkNum[]) throws Exception{
		int cnt1 = basketService.BasketRemove(delBkNum);
		
		return "redirect:/basket/basketlist";
		
		
	}

	
	
}
		
	
	
	

