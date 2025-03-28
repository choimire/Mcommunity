package net.mirechoi.mcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mirechoi.mcommunity.dto.FileDTO;
import net.mirechoi.mcommunity.mapper.FileMapper;

@Service
public class FileService {

	@Autowired
	FileMapper fileMapper;
	
	public int insertFile(FileDTO fileDTO) {
		return fileMapper.insertFile(fileDTO);
		
	}
	public int updateFile(long bid, long tempId) {
		return fileMapper.updateFileById(bid, tempId);
	}
	public int deleteFile(long id) {
		return fileMapper.deleteFile(id);
		
	}
	public void trashFile() {
		fileMapper.deleteTrashFile();
	}
	public List<FileDTO> selectFileList(long bid){
		return fileMapper.selectFileById(bid);
	}
	public FileDTO selectFileById(long id) {
		 return fileMapper.fileById(id);
	}
}
