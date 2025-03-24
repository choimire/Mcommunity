package net.mirechoi.mcommunity.dto;

public class BoardCategory {

	private int id;
	private int bid;
	private String categoryText;
	private int categoryNum;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getCategoryText() {
		return categoryText;
	}
	public void setCategoryText(String categoryText) {
		this.categoryText = categoryText;
	}
	public int getCategoryNum() {
		return categoryNum;
	}
	public void setCategoryNum(int categoryNum) {
		this.categoryNum = categoryNum;
	}
	@Override
	public String toString() {
		return "BoardCategory [id=" + id + ", bid=" + bid + ", categoryText=" + categoryText + ", categoryNum="
				+ categoryNum + "]";
	}
}

