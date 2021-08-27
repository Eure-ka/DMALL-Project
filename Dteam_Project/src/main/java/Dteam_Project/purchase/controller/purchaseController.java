package Dteam_Project.purchase.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.protocol.a.NativeConstants.IntegerDataType;

import Dteam_Project.index.service.IndexService;
import Dteam_Project.purchase.model.CouponVO;
import Dteam_Project.purchase.model.ProductVO;
import Dteam_Project.purchase.service.purchaseService;
import Dteam_Project.user_join.model.UserVO;
import Dteam_Project.userlogin.model.UserLoginVO;
import Dteam_Project.utils.ScriptUtils;

@Controller
@RequestMapping("purchase")
public class purchaseController {
	
	@Autowired
	purchaseService purchaseService;
	

	@GetMapping("pcpage")
	public String resultNumberPost(HttpServletResponse response,@RequestParam(value="resultNumber",required=false) String resultNumber,@RequestParam(value="resultNumber2",required=false) String resultNumber2,@RequestParam(value="product_code",required=false) String before_product_code,Model model,HttpSession session) throws Exception{
		
		
		UserLoginVO userLoginVO = (UserLoginVO)session.getAttribute("userLoginVO");
		
		if (userLoginVO ==null) {
			
			ScriptUtils.alertAndMovePage(response, "구매는 회원만 가능합니다.", "/login");
			
			return "";
			
		}else {
			Integer product_code=Integer.parseInt(before_product_code);
			
			ProductVO dbproductvo=purchaseService. getProductInfo(product_code);
			
			int coupon_cnt =purchaseService.getcouponCount();
			
			//확인용
			//System.out.println("resultNumber:"+resultNumber);
			
			//java로 보낼때 addAtrribute
			
			model.addAttribute("resultNumber", resultNumber);
			model.addAttribute("resultNumber2", resultNumber2);
			model.addAttribute("productInfo",dbproductvo);
			model.addAttribute("coupocount", coupon_cnt);
			
			return "purchase/purchase";
		}
		
}
	
	@GetMapping("coupon")
	public String go_couponlist (Model model) throws Exception{
		
			List<CouponVO> couponlist=purchaseService.getCouponList();
			
			model.addAttribute("couponlist", couponlist);
			
			
		return "purchase/couponlist";
	}
	
	@PostMapping("coupon")
	public String calcdiscount(HttpServletRequest request,Model model) throws Exception{
		
		String beforecoupon_rate=request.getParameter("coupon_rate");
		int coupon_rate=Integer.parseInt(beforecoupon_rate);
		
		
		model.addAttribute("coupon_rate", coupon_rate);
		
		return "redirect:/purchase/pcpage";
	}
	
	@PostMapping("final_result")
	public String go_result(@RequestParam HashMap<String,String> paramMap,Model model,ProductVO productVO) throws Exception{
		
		// 물건 수량 빼기 위해서
		String bf_amount = paramMap.get("pdqnt");
		Integer amount =Integer.parseInt(bf_amount); 

		
		String bf_code = paramMap.get("product_code");
		
		System.out.println("프로덕트 코드 타입:"+bf_code.getClass().getName());
		
		// NumberFormatException 에러가 날때는 Integer.parseInt() 를 사용할때 제대로 숫자형으로 변환이
		// 안됬을 경우에 뜨는 오류기 때문에 빈칸이나 숫자가 아닌 문자가 들어있는지 확인해본다!!
		Integer pd_code =Integer.parseInt(bf_code.trim());
		
		productVO.setProduct_amount(amount);
		productVO.setProduct_code(pd_code);
		
		
		purchaseService.buyproduct(productVO);
		
		paramMap.remove("radioTxt");
		
		model.addAttribute("resulthashmap", paramMap);
		
		
		if (paramMap.get("coupon_id") != null) {
			
			String bf_coupon_id=paramMap.get("coupon_id");
			
			Integer coupon_id =Integer.parseInt(bf_coupon_id);
			
			
			purchaseService.usecoupon(coupon_id);
			
			return "purchase/resultpage";
		}else {
			
			return "purchase/resultpage";
		}
		
	}

}
