package Dteam_Project.review.service;

import java.sql.SQLException;
import java.util.List;

import Dteam_Project.product.model.ProductVO;
import Dteam_Project.review.model.ReviewVO;

public interface ReviewService {

	public List<ProductVO> getReviewList(Integer product_code) throws SQLException;
	
	public ReviewVO getReview(Integer rnum) throws SQLException;

	public ReviewVO reviewProc(Integer rnum) throws SQLException;

}
