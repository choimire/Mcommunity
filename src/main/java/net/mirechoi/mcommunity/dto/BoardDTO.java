package net.mirechoi.mcommunity.dto;

import java.sql.Timestamp;

public class BoardDTO {
	private long id;
	private int bbsid;
	private long ref;
	private int step;
	private String writer;
	private String userid;
	private String password;
	private String title;
	private String content;
	private int comment;
	private byte sec;
	private String category;
	private int hit;
	private Timestamp wdate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getBbsid() {
		return bbsid;
	}
	public void setBbsid(int bbsid) {
		this.bbsid = bbsid;
	}
	public long getRef() {
		return ref;
	}
	public void setRef(long ref) {
		this.ref = ref;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getComment() {
		return comment;
	}
	public void setComment(int comment) {
		this.comment = comment;
	}
	public byte getSec() {
		return sec;
	}
	public void setSec(byte sec) {
		this.sec = sec;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Timestamp getWdate() {
		return wdate;
	}
	public void setWdate(Timestamp wdate) {
		this.wdate = wdate;
	}
	@Override
	public String toString() {
		return "BoardDTO [id=" + id + ", bbsid=" + bbsid + ", ref=" + ref + ", step=" + step + ", writer=" + writer
				+ ", userid=" + userid + ", password=" + password + ", title=" + title + ", content=" + content
				+ ", comment=" + comment + ", sec=" + sec + ", category=" + category + ", hit=" + hit + ", wdate="
				+ wdate + "]";
	}
}
