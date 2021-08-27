package Dteam_Project.product.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dteam_Project.product.model.CategoryVO;
import Dteam_Project.product.model.ProductDAO;
import Dteam_Project.product.model.ProductVO;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	
	@Autowired
	ProductDAO productdao;
	
	
	@Override
	public List<CategoryVO> getCategorylist() throws SQLException {
		
		List<CategoryVO> categorylist = productdao.getCategorylist();
		
		System.out.println("리스트:"+categorylist);
		
		return categorylist;
	}


	@Override
	public int insertProduct(ProductVO productVO) throws SQLException {
		System.out.println("서비스 vo:"+ productVO);
		
		int cnt=0;
		cnt = productdao.insertProduct(productVO);
		
		// 나중에 상품목록있을때 체크
		//		if(dbproductVO == null) {
		//			
		//		}else {
		//			cnt = -1; //이미 있는 코드 체크
		//		}
		
		return cnt;
	}


	@Override
	public List<ProductVO> getProductlist() throws SQLException {
		
		List<ProductVO> dbproductlist=productdao.getProductlist();
		return dbproductlist;
	}

	

	@Override
	public ProductVO getProductInfo(Integer product_code) throws SQLException {
		
		ProductVO productVo =productdao.getProductInfo(product_code);
		return productVo;
	}


	
	@Override
	public CategoryVO getCategoryInfo(Integer category_id) throws SQLException {
		
		CategoryVO categoryVO = productdao.getCategoryInfo(category_id);
		
		return categoryVO;
	}


	@Override
	public int uptProduct(ProductVO productVO) throws SQLException {
		int cnt=productdao.uptProduct(productVO);
		return cnt;
	}


	@Override
	public int delProduct(Integer product_code) throws SQLException {
		int cnt =productdao.delProduct(product_code);
		return cnt;
	}
	
}
