package Dteam_Project.index.model;

import java.util.List;

public class PageObj {
	
	private Integer stRow;
	private Integer edRow;
	private List<Integer> pageSetList;
	private Integer totalPage;
	private Integer category_id;
	private String order; 
	
	public Integer getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	public List<Integer> getPageSetList() {
		return pageSetList;
	}
	
	public void setPageSetList(List<Integer> pageSetList) {
		this.pageSetList = pageSetList;
	}
	
	public Integer getStRow() {
		return stRow;
	}
	
	public void setStRow(Integer stRow) {
		this.stRow = stRow;
	}
	
	public Integer getEdRow() {
		return edRow;
	}
	
	public void setEdRow(Integer edRow) {
		this.edRow = edRow;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	
}
