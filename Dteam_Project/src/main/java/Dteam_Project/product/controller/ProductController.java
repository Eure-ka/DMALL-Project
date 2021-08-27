package Dteam_Project.product.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;

import Dteam_Project.admin.model.AdminVO;
import Dteam_Project.product.model.CategoryVO;
import Dteam_Project.product.model.ProductVO;
import Dteam_Project.product.service.ProductService;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
@RequestMapping("pd")
public class ProductController {
	
	//썸네일 이미지 만들기 위해 크기 저장
	static final int THUMB_WIDTH = 300;
	static final int THUMB_HEIGHT = 300;
	
	@Autowired
	ProductService productservice;
	
	@GetMapping("main")
	public String go_main(AdminVO adminvo,Model model) throws SQLException{
		
		model.addAttribute("adminvo",adminvo);
		
		return "admin/adminmain";
	}
	
	@GetMapping("pdreg")
	public String ins_product_get(Model model,HttpSession session) throws SQLException{
		
		List<CategoryVO> categorylist =productservice.getCategorylist();
		
		System.out.println("리스트:"+categorylist);
		
		Object admin_id=session.getAttribute("admin_id");
		
		System.out.println("관리자 아이디"+admin_id);
		
		model.addAttribute("admin_id", admin_id);
		model.addAttribute("categorylist", categorylist);
		
		return "product/insproduct";
	}
	
	@PostMapping("pdreg")
	public String ins_product_post(@RequestParam("files") MultipartFile mfile,ProductVO productVO,HttpSession session) throws SQLException,IOException{
		
		String uploadPath="C:\\study\\Springworks\\Dteam_Project\\src\\main\\resources\\static\\image";

		String originalFileName = mfile.getOriginalFilename();
		
		
		
		// 랜덤 값 생성하기 위해 uuid 사용
		UUID uuid = UUID.randomUUID();
		String convertPw = UUID.randomUUID().toString().replace("-", "");
		String saveFileName = convertPw+originalFileName;
		
		
		File main_image = new File(uploadPath,saveFileName);
		productVO.setImg_path(saveFileName);
		mfile.transferTo(main_image);
		
		
		 String thumbFileName = "thumnail_" + saveFileName;
		 System.out.println("썸네일 파일 이름:"+thumbFileName);
		 File thum_image=new File(uploadPath,thumbFileName);
		 Thumbnails.of(new File(uploadPath,saveFileName)).size(THUMB_WIDTH, THUMB_HEIGHT).toFile(thum_image);
		 
		 productVO.setSumimg_path(thumbFileName);
		 System.out.println("썸네일 파일 업로드 완료");
		
		
		productservice.insertProduct(productVO);
		
		
		return "redirect:/pd/pdreg"; 
	}
	
	@GetMapping("pdlist")
	public String pdlist(Model model) throws SQLException{
		
		List<ProductVO> dbproductlist =productservice.getProductlist();
		
		model.addAttribute("productlist",dbproductlist);
		
		return "product/listproduct";
	}
	
	@GetMapping("uptpd/{product_code}")
	public String uptproduct_get(@PathVariable("product_code") Integer product_code,Model model) throws SQLException{
		
		ProductVO productVO=productservice.getProductInfo(product_code);
		
		model.addAttribute("productInfo",productVO);
		return "product/productinfo";
	}
	
	@PostMapping("uptpd")
	public String uptproduct_post(ProductVO productVO) throws SQLException{
		System.out.println("productVO정보:"+productVO);
		productservice.uptProduct(productVO);
		
		return "redirect:/pd/pdlist";
	}
	
	@PostMapping("delpd")
	public String delproduct(HttpServletRequest request) throws SQLException{
		
		String before= request.getParameter("product_code");
		
		Integer product_code=Integer.parseInt(before);
		
		productservice.delProduct(product_code);
		
		return "redirect:/pd/pdlist";
	}
	
}
