package Dteam_Project.purchase.model;

public class CouponVO {
	private Integer coupon_id;
	private String coupon_name;
	private Integer coupon_amount;
	private Integer coupon_rate;
	private Integer category_id;
	
	public Integer getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(Integer coupon_id) {
		this.coupon_id = coupon_id;
	}
	public String getCoupon_name() {
		return coupon_name;
	}
	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}
	public Integer getCoupon_amount() {
		return coupon_amount;
	}
	public void setCoupon_amount(Integer coupon_amount) {
		this.coupon_amount = coupon_amount;
	}
	public Integer getCoupon_rate() {
		return coupon_rate;
	}
	public void setCoupon_rate(Integer coupon_rate) {
		this.coupon_rate = coupon_rate;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
}
