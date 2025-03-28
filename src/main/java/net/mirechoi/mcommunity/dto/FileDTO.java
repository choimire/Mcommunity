package net.mirechoi.mcommunity.dto;

public class FileDTO {
	   private long id;
	   private long bid;  //게시판 번호
	   private String ofilename; //올릴때 파일이름
	   private String nfilename; //변경한 이름
	   private String ext;      //파일의 확장자
	   private long filesize;  //파일의 사이즈
	   private long addFileSize;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getBid() {
		return bid;
	}
	public void setBid(long bid) {
		this.bid = bid;
	}
	public String getOfilename() {
		return ofilename;
	}
	public void setOfilename(String ofilename) {
		this.ofilename = ofilename;
	}
	public String getNfilename() {
		return nfilename;
	}
	public void setNfilename(String nfilename) {
		this.nfilename = nfilename;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public long getAddFileSize() {
		return addFileSize;
	}
	public void setAddFileSize(long addFileSize) {
		this.addFileSize = addFileSize;
	}
	@Override
	public String toString() {
		return "FileDTO [id=" + id + ", bid=" + bid + ", ofilename=" + ofilename + ", nfilename=" + nfilename + ", ext="
				+ ext + ", filesize=" + filesize + ", addFileSize=" + addFileSize + "]";
	}
	
	}

