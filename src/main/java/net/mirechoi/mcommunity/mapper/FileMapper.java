package net.mirechoi.mcommunity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.mirechoi.mcommunity.dto.FileDTO;

public interface FileMapper {
	
	int insertFile(FileDTO file);
	int updateFileById(@Param("bid") long bid, @Param("tempBid")long tempBid);
	int deleteFile(long id);
	List<FileDTO> selectFileById(long bid);
	FileDTO fileById(long id);
	FileDTO fileByFileName(String fname);
}
