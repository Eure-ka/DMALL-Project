package Dteam_Project.purchase.service;

import java.sql.SQLException;
import java.util.List;


import Dteam_Project.purchase.model.CouponVO;
import Dteam_Project.purchase.model.ProductVO;
import Dteam_Project.userlogin.model.UserLoginVO;

public interface purchaseService {
	
	public ProductVO getProductInfo(Integer product_code) throws SQLException;
	
	public UserLoginVO getLoginInfo(String user_id) throws SQLException;
	
	public List<CouponVO> getCouponList() throws SQLException;
	
	public int getcouponCount() throws SQLException;
	
	public int usecoupon(Integer coupon_id) throws SQLException;
	
	public int buyproduct(ProductVO productVO) throws SQLException;
	
	//public int updateAmount(ProductVO productVO) throws SQLException;

}
