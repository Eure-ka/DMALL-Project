package Dteam_Project.product.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dteam_Project.product.model.CategoryVO;
import Dteam_Project.product.model.ProductVO;


public interface ProductService {
	
	
	public List<CategoryVO> getCategorylist () throws SQLException;
	
	
	public int insertProduct(ProductVO productVO) throws SQLException;
	
	public List<ProductVO> getProductlist() throws SQLException;
	
	public ProductVO getProductInfo(Integer product_code) throws SQLException;
	
	public CategoryVO getCategoryInfo(Integer category_id) throws SQLException;
	
	public int uptProduct(ProductVO productVO) throws SQLException;
	
	public int delProduct(Integer product_code) throws SQLException;
}
