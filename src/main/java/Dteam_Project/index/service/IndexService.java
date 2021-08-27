package Dteam_Project.index.service;

import java.sql.SQLException;
import java.util.List;

import Dteam_Project.index.model.PageObj;
import Dteam_Project.product.model.ProductVO;

public interface IndexService {
	
	public List<ProductVO> getProductlist() throws SQLException;
	
	public ProductVO getProductInfo(Integer product_code) throws SQLException;
	
	public List<ProductVO> getBoardListPaging(PageObj pageObj) throws SQLException;
	
	public int getCount() throws SQLException;
	
	public List<ProductVO> cate_getBoardListPaging(PageObj pageObj) throws SQLException;
	
	public int cate_Count(Integer category_id) throws SQLException;
	
	public List<ProductVO> searchname(ProductVO productVO) throws SQLException;
}
