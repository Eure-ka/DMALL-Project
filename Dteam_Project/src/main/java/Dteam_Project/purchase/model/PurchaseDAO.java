package Dteam_Project.purchase.model;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import Dteam_Project.userlogin.model.UserLoginVO;

@Repository
@Mapper
public interface PurchaseDAO {
	
	public UserLoginVO getLoginInfo(String user_id) throws SQLException;
	
	public ProductVO getProductInfo(Integer product_code) throws SQLException;
	
	public List<CouponVO> getCouponList() throws SQLException;
	
	public int getcouponCount() throws SQLException;
	
	public int usecoupon(Integer coupon_id) throws SQLException;
	
	public int buyproduct(ProductVO productVO) throws SQLException;
	
}
