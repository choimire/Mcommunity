package net.mirechoi.mcommunity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.mirechoi.mcommunity.dto.FileDTO;

public interface FileMapper {
	
	int insertFile(FileDTO file);
	int updateFileById(@Param("bid") long bid, @Param("tempId")long tempId);
	int deleteFile(long id);
	List<FileDTO> selectFileById(long bid);
	void deleteTrashFile();
	FileDTO fileById(long id);
}
