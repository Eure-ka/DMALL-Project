package Dteam_Project.review.model;

import java.util.Date;

public class ReviewVO {
	
	private Integer product_code;
	private Integer rnum;
	private String review_content;
	private String user_id;
	private Date review_date;
	private String review_img;
	private Integer helpful;

	public Integer getProduct_code() {
		return product_code;
	}
	public void setProduct_code(Integer product_code) {
		this.product_code = product_code;
	}
	public Integer getRnum() {
		return rnum;
	}
	public void setRnum(Integer rnum) {
		this.rnum = rnum;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getReview_date() {
		return review_date;
	}
	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}
	public String getReview_img() {
		return review_img;
	}
	public void setReview_img(String review_img) {
		this.review_img = review_img;
	}
    public Integer getHelpful() {
		return helpful;
	}
	public void setHelpful(Integer helpful) {
		this.helpful = helpful;
	}
	
}
