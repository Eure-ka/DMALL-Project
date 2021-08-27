package Dteam_Project.product.model;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;






@Repository
@Mapper
public interface ProductDAO {
	
	public List<CategoryVO> getCategorylist () throws SQLException;
	
	public int insertProduct(ProductVO productVO) throws SQLException;
	
	public List<ProductVO> getProductlist() throws SQLException;
	
	public ProductVO getProductInfo(Integer product_code) throws SQLException;
	
	public CategoryVO getCategoryInfo(Integer category_id) throws SQLException;
	
	public int uptProduct(ProductVO productVO) throws SQLException;
	
	public int delProduct(Integer product_code) throws SQLException;
}
