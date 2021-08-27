package Dteam_Project.basket.model;

public class BasketVO {
	
	private Integer bk_num;   // 상품 리스트 번호
	private String product_name;  // 상품 이름
	private Integer product_price;  // 상품 가격 
	private String img_path; // 상품 이미지
	private Integer product_amount; // 수량 
	private Integer product_shipping_fee;  // 배송비
	private String user_id;    // 회원 아이디
	private Integer product_code;   // 상품 코드
	private String sumimg_path;
	private Integer bk_resultnumber;


public Integer getBk_resultnumber() {
		return bk_resultnumber;
	}
	public void setBk_resultnumber(Integer bk_resultnumber) {
		this.bk_resultnumber = bk_resultnumber;
	}
public String getSumimg_path() {
		return sumimg_path;
	}
	public void setSumimg_path(String sumimg_path) {
		this.sumimg_path = sumimg_path;
	}
	
	
public Integer getBk_num() {
	return bk_num;
}
public void setBk_num(Integer bk_num) {
	this.bk_num = bk_num;
}
public String getProduct_name() {
	return product_name;
}
public void setProduct_name(String product_name) {
	this.product_name = product_name;
}
public Integer getProduct_price() {
	return product_price;
}
public void setProduct_price(Integer product_price) {
	this.product_price = product_price;
}
public String getImg_path() {
	return img_path;
}
public void setImg_path(String img_path) {
	this.img_path = img_path;
}
public Integer getProduct_amount() {
	return product_amount;
}
public void setProduct_amount(Integer product_amount) {
	this.product_amount = product_amount;
}
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}

public Integer getProduct_shipping_fee() {
	return product_shipping_fee;
}
public void setProduct_shipping_fee(Integer product_shipping_fee) {
	this.product_shipping_fee = product_shipping_fee;
}
public Integer getProduct_code() {
	return product_code;
}
public void setProduct_code(Integer product_code) {
	this.product_code = product_code;
}
}
