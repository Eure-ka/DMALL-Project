package Dteam_Project.review.model;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import Dteam_Project.product.model.ProductVO;

@Repository
@Mapper
public interface ReviewDAO {
	
	public List<ProductVO> getReviewList(Integer product_code) throws SQLException;
	
	public ReviewVO getReview(Integer rnum) throws SQLException;
	
	public int helpfulIncrease(Integer rnum) throws SQLException;
		
}
