package Dteam_Project.index.controller;

import java.sql.SQLException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;

import Dteam_Project.index.model.PageObj;
import Dteam_Project.index.service.IndexService;
import Dteam_Project.product.model.ProductVO;
import Dteam_Project.review.model.ReviewVO;
import Dteam_Project.review.service.ReviewService;


@Controller
@RequestMapping("main")
public class IndexController {
	
	@Autowired
	IndexService indexservice;
	
	@Autowired
	ReviewService reviewservice;
	
	
	@GetMapping("home")
	public String go_index(@RequestParam(name="page", defaultValue = "1") Integer page,HttpServletRequest request,ProductVO productVO,Model model) throws SQLException{
		
		String order=request.getParameter("order");
		
		
		int pageRow = 9; // 게시판 리스트에 표시되는 상품수
		int pageSetSize = 5;
		PageObj pageObj = new PageObj();
		int stRow = page <= 1 ? 0 : (page-1) * pageRow;  
		int edRow = pageRow;
		
		pageObj.setStRow(stRow);
		pageObj.setEdRow(edRow);
		
		
		//낮은상품순,신상품 순 같은 순서대로 정렬하기 위해서
		pageObj.setOrder(order);
		
		List<ProductVO>productlist=indexservice.getBoardListPaging(pageObj);
		int totalcount =indexservice.getCount();
		
		int totalPage = (int)Math.ceil((double)totalcount / pageRow);  //전체 페이지수
		int curPageSet = (int)Math.ceil((double)page / pageSetSize);  //현재 페이지
		List<Integer> pageSetList = new ArrayList<Integer>();
		int curPageSetSt = curPageSet-1 == 0 ? 1 : (curPageSet-1)*pageSetSize+1;

		for(int i = curPageSetSt; i <= curPageSet*pageSetSize; i++ ) {
			if(totalPage<i) break;
				pageSetList.add(i);
		}
		
		pageObj.setPageSetList(pageSetList);
		pageObj.setTotalPage(totalPage);
		
		
		model.addAttribute("productlist",productlist);
		model.addAttribute("pageObj", pageObj);
		
		return "index";
	}
	
	@GetMapping("pdinfo/{product_code}")
	public String go_productinfo(@PathVariable("product_code") Integer product_code,Model model) throws SQLException{
		
		
		ProductVO productVO=indexservice.getProductInfo(product_code);
		
		
		List<ProductVO> productJoinReviewVO =reviewservice.getReviewList(product_code);
		
		
		 
		
		model.addAttribute("productInfo",productVO);
		model.addAttribute("productJoinReviewVO",productJoinReviewVO);
		
		return "user_product/userproductinfo";
	}
	
	
	@GetMapping("category/{category_id}")
	public String go_category(@PathVariable(value="category_id") Integer category_id,HttpServletRequest request,@RequestParam(name="page", defaultValue = "1") Integer page,Model model) throws SQLException{
		
		
		int pageRow = 9; // 게시판 리스트에 표시되는 상품수
		int pageSetSize = 5;
		PageObj pageObj = new PageObj();
		int stRow = page <= 1 ? 0 : (page-1) * pageRow;  
		int edRow = pageRow;
		
		pageObj.setStRow(stRow);
		pageObj.setEdRow(edRow);
		pageObj.setCategory_id(category_id);
		
		String order=request.getParameter("order");
		pageObj.setOrder(order);
		
		
		List<ProductVO>productlist=indexservice.cate_getBoardListPaging(pageObj);
		
		int totalcount =indexservice.cate_Count(category_id);
		
		int totalPage = (int)Math.ceil((double)totalcount / pageRow);  //전체 페이지수
		int curPageSet = (int)Math.ceil((double)page / pageSetSize);  //현재 페이지
		List<Integer> pageSetList = new ArrayList<Integer>();
		int curPageSetSt = curPageSet-1 == 0 ? 1 : (curPageSet-1)*pageSetSize+1;
		System.out.println("curPageSetSt:"+curPageSetSt);
		for(int i = curPageSetSt; i <= curPageSet*pageSetSize; i++ ) {
			if(totalPage<i) break;
				pageSetList.add(i);
		}
		
		pageObj.setPageSetList(pageSetList);
		pageObj.setTotalPage(totalPage);
		
		
		model.addAttribute("productlist",productlist);
		model.addAttribute("pageObj", pageObj);
		
		return "user_product/categoryproduct";
	}
	
	
	
	@PostMapping("home")
	public String search_name(@RequestParam(name="search") String product_name,@RequestParam(name="category_id") Integer category_id,ProductVO productVO,Model model) throws SQLException{
		
		
		String plus_product_name="%"+product_name+"%";
		
		System.out.println("카테고리 아이디 넘어오나 확인:"+category_id);
		
		productVO.setProduct_name(plus_product_name);
		productVO.setCategory_id(category_id);
		
		List<ProductVO> dbproductlist =indexservice.searchname(productVO);
		
		
		System.out.println("dbproductlist 찍어보기:"+dbproductlist);
		
		
		if (dbproductlist.isEmpty()) {

			model.addAttribute("message", "검색된 상품이 없습니다.");
			
		}else {
			
			model.addAttribute("productlist",dbproductlist);
		}
		
		
		
		return "user_product/searchproduct";
	}
	
	@GetMapping("/review/{product_code}/{rnum}")
    public String helpful_Increase(@PathVariable("rnum") Integer rnum, @PathVariable("product_code") Integer product_code, Model model) throws SQLException {
      
      ReviewVO reviewbean = reviewservice.reviewProc(rnum);
      model.addAttribute("reviewbean", reviewbean);
      
      return "redirect:/main/pdinfo/{product_code}";
 }
}
