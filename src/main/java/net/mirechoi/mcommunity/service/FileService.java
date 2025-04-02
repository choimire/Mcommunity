package net.mirechoi.mcommunity.service;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
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
	public void trashFile(String filePath) {
		File baseDir = new File(filePath);
		if(!baseDir.exists()) return;
		
		long todayZero = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
		
		File[] files = baseDir.listFiles();
		if(files == null) return;
		//db x file o 삭제
		for(File file:files) {
			String fname = file.getName();
			FileDTO dto = fileMapper.fileByFileName(fname);
			boolean isTrash = false;
			if(dto == null) {
				isTrash = true;
			}else {
				long bid = dto.getBid();
				if(String.valueOf(bid).matches("\\d{12,14}")&&bid<todayZero) {
					isTrash = true;
				}
			}
			if(isTrash) {
				boolean del = file.delete();
				System.out.println(del?"[삭제됨]" : "[삭제실패]" + fname);
				if(dto != null) {
					fileMapper.deleteFile(dto.getId());
					
				}
			}
		}

	}
	public List<FileDTO> selectFileList(long bid){
		return fileMapper.selectFileById(bid);
	}
	public FileDTO selectFileById(long id) {
		 return fileMapper.fileById(id);
	}
	public FileDTO selectFileByFileName(String fileName) {
		
		return fileMapper.fileByFileName(fileName);
	}
}
