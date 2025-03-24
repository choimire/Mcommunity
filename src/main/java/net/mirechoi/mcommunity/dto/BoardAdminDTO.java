package net.mirechoi.mcommunity.dto;

import java.util.List;

public class BoardAdminDTO {
	private int id;
	private String btitle; //게시판 제목
	private String skin; //게시판 모양
	private byte category; //카테고리 허용 유무
	private byte listcount; // 목록의 크기
	private byte pagecount; //한 페이지에 보일 페이지
	private byte lgrade; //목록보기 권한
	private byte vgrade; //게시물보기 권한 
	private byte rgrade; // 게시물쓰기 권한
	private byte fgrade;//파일 업로드 권한
	private byte fdgrade;//파일 다운로드 권한
	private byte cgrade; //댓글 권한
	private byte regrade; //답글 권한
	private byte upload; //파일 업로그 권한
	private int filesize; //허용 파일 크기
	private int allfilesize; //허용 파일 총 크기
	private String thimgsize; //썸네일 크기 150*150
	private byte filechar; //0이면 이미지만 업로드 1이면 모든 업로드 허용
	private List<BoardCategory> boardCategory;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getSkin() {
		return skin;
	}
	public void setSkin(String skin) {
		this.skin = skin;
	}
	public byte getCategory() {
		return category;
	}
	public void setCategory(byte category) {
		this.category = category;
	}
	public byte getListcount() {
		return listcount;
	}
	public void setListcount(byte listcount) {
		this.listcount = listcount;
	}
	public byte getPagecount() {
		return pagecount;
	}
	public void setPagecount(byte pagecount) {
		this.pagecount = pagecount;
	}
	public byte getLgrade() {
		return lgrade;
	}
	public void setLgrade(byte lgrade) {
		this.lgrade = lgrade;
	}
	public byte getVgrade() {
		return vgrade;
	}
	public void setVgrade(byte vgrade) {
		this.vgrade = vgrade;
	}
	public byte getRgrade() {
		return rgrade;
	}
	public void setRgrade(byte rgrade) {
		this.rgrade = rgrade;
	}
	public byte getFgrade() {
		return fgrade;
	}
	public void setFgrade(byte fgrade) {
		this.fgrade = fgrade;
	}
	public byte getFdgrade() {
		return fdgrade;
	}
	public void setFdgrade(byte fdgrade) {
		this.fdgrade = fdgrade;
	}
	public byte getCgrade() {
		return cgrade;
	}
	public void setCgrade(byte cgrade) {
		this.cgrade = cgrade;
	}
	public byte getRegrade() {
		return regrade;
	}
	public void setRegrade(byte regrade) {
		this.regrade = regrade;
	}
	public byte getUpload() {
		return upload;
	}
	public void setUpload(byte upload) {
		this.upload = upload;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	public int getAllfilesize() {
		return allfilesize;
	}
	public void setAllfilesize(int allfilesize) {
		this.allfilesize = allfilesize;
	}
	public String getThimgsize() {
		return thimgsize;
	}
	public void setThimgsize(String thimgsize) {
		this.thimgsize = thimgsize;
	}
	public byte getFilechar() {
		return filechar;
	}
	public void setFilechar(byte filechar) {
		this.filechar = filechar;
	}
	public List<BoardCategory> getBoardCategory() {
		return boardCategory;
	}
	public void setBoardCategory(List<BoardCategory> boardCategory) {
		this.boardCategory = boardCategory;
	}
	@Override
	public String toString() {
		return "BoardAdminDTO [id=" + id + ", btitle=" + btitle + ", skin=" + skin + ", category=" + category
				+ ", listcount=" + listcount + ", pagecount=" + pagecount + ", lgrade=" + lgrade + ", vgrade=" + vgrade
				+ ", rgrade=" + rgrade + ", fgrade=" + fgrade + ", fdgrade=" + fdgrade + ", cgrade=" + cgrade
				+ ", regrade=" + regrade + ", upload=" + upload + ", filesize=" + filesize + ", allfilesize="
				+ allfilesize + ", thimgsize=" + thimgsize + ", filechar=" + filechar + ", boardCategory="
				+ boardCategory + "]";
	}
	
	
	
	
}
