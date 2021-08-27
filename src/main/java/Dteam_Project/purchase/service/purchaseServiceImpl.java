package Dteam_Project.purchase.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dteam_Project.index.model.IndexDAO;
import Dteam_Project.purchase.model.CouponVO;
import Dteam_Project.purchase.model.ProductVO;
import Dteam_Project.purchase.model.PurchaseDAO;
import Dteam_Project.userlogin.model.UserLoginDAO;
import Dteam_Project.userlogin.model.UserLoginVO;

@Service
@Transactional
public class purchaseServiceImpl implements purchaseService{
	
	@Autowired
	private UserLoginDAO userLoginDAO;
	
	@Autowired
	private PurchaseDAO purchaseDAO;
	
	
	@Override
	public ProductVO getProductInfo(Integer product_code) throws SQLException {
		
		ProductVO productVo =purchaseDAO.getProductInfo(product_code);
		return productVo;
	}
	

	@Override
	public UserLoginVO getLoginInfo(String user_Id) throws SQLException {
		UserLoginVO userLoginVO = userLoginDAO.getLoginInfo(user_Id);		
		return userLoginVO;
	}

	@Override
	public List<CouponVO> getCouponList() throws SQLException {
		
		List<CouponVO> couponlist=purchaseDAO.getCouponList();
		
		return couponlist;
	}


	@Override
	public int getcouponCount() throws SQLException {
		int cnt =purchaseDAO.getcouponCount();
		
		return cnt;
	}


	@Override
	public int usecoupon(Integer coupon_id) throws SQLException {
		int cnt=purchaseDAO.usecoupon(coupon_id);
		return cnt;
	}


	@Override
	public int buyproduct(ProductVO productVO) throws SQLException {
		
		int cnt =purchaseDAO.buyproduct(productVO);
		
		return cnt;
	}
	
	
}
