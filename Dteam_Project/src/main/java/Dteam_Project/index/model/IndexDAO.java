package Dteam_Project.index.model;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import Dteam_Project.product.model.ProductVO;
import groovyjarjarpicocli.CommandLine.Parameters;

@Repository
@Mapper
public interface IndexDAO {
	
	public List<ProductVO> getProductlist() throws SQLException;
	
	public ProductVO getProductInfo(Integer product_code) throws SQLException;
	
	public List<ProductVO> getBoardListPaging(PageObj pageObj) throws SQLException;
	
	public int getCount() throws SQLException;
	
	public List<ProductVO> cate_getBoardListPaging(PageObj pageObj) throws SQLException;
	
	public int cate_Count(Integer category_id) throws SQLException;
	
	public List<ProductVO> searchname(ProductVO productVO) throws SQLException;
} 
