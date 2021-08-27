package Dteam_Project.review.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dteam_Project.product.model.ProductVO;
import  Dteam_Project.review.model.ReviewDAO;
import  Dteam_Project.review.model.ReviewVO;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewDAO reviewDAO;
	
	@Override
	   public List<ProductVO> getReviewList(Integer product_code) throws SQLException {
		
	      List<ProductVO> productVO = reviewDAO.getReviewList(product_code);
	      
	      return productVO;
	   }

	@Override
	public ReviewVO getReview(Integer rnum) throws SQLException {
		
		ReviewVO reviewVO = reviewDAO.getReview(rnum);
		
		return reviewVO;
	}

	@Override
	public ReviewVO reviewProc(Integer rnum) throws SQLException {
		
		ReviewVO reviewVO = reviewDAO.getReview(rnum);
		
		reviewDAO.helpfulIncrease(rnum);
		return reviewVO;
	}




	

	
}
