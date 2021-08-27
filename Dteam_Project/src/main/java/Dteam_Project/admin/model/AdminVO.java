package Dteam_Project.admin.model;

import java.util.Date;

public class AdminVO {

	private String admin_id;
	private String admin_pw;
	private String admin_position;
	private Date admin_reg_date;
	
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_pw() {
		return admin_pw;
	}
	public void setAdmin_pw(String admin_pw) {
		this.admin_pw = admin_pw;
	}
	public String getAdmin_position() {
		return admin_position;
	}
	public void setAdmin_position(String admin_position) {
		this.admin_position = admin_position;
	}
	public Date getAdmin_reg_date() {
		return admin_reg_date;
	}
	public void setAdmin_reg_date(Date admin_reg_date) {
		this.admin_reg_date = admin_reg_date;
	}
	
}
