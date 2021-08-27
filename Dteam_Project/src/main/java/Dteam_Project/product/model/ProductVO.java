package Dteam_Project.product.model;

import java.util.Date;
import java.util.List;

import Dteam_Project.review.model.ReviewVO;

public class ProductVO {
	
	private Integer product_code;
	private String product_name;
	private Integer product_price;
	private Integer category_id;
	private String img_path;
	private Integer product_amount;
	private Date product_reg_date;
	private Integer product_shipping_fee;
	private String admin_id;
	private CategoryVO categoryVO;
	private String sumimg_path;
	private List<ReviewVO> reviewList;
	
	public CategoryVO getCategoryVO() {
		return categoryVO;
	}
	public void setCategoryVO(CategoryVO categoryVO) {
		this.categoryVO = categoryVO;
	}

	public Integer getProduct_code() {
		return product_code;
	}
	public void setProduct_code(Integer product_code) {
		this.product_code = product_code;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	
	
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	
	public String getImg_path() {
		return img_path;
	}
	
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	public int getProduct_amount() {
		return product_amount;
	}
	
	public void setProduct_amount(int product_amount) {
		this.product_amount = product_amount;
	}
	
	public Date getProduct_reg_date() {
		return product_reg_date;
	}
	
	public void setProduct_reg_date(Date product_reg_date) {
		this.product_reg_date = product_reg_date;
	}
	
	public int getProduct_shipping_fee() {
		return product_shipping_fee;
	}
	
	public void setProduct_shipping_fee(int product_shipping_fee) {
		this.product_shipping_fee = product_shipping_fee;
	}
	
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getSumimg_path() {
		return sumimg_path;
	}
	public void setSumimg_path(String sumimg_path) {
		this.sumimg_path = sumimg_path;
	}
	
	public List<ReviewVO> getReviewList() {
		return reviewList;
	}
	public void setReviewList(List<ReviewVO> reviewList) {
		this.reviewList = reviewList;
	}
	
	
}
