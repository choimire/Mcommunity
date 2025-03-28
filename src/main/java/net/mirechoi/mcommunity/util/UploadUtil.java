package net.mirechoi.mcommunity.util;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import net.mirechoi.mcommunity.dto.FileDTO;

@Component
public interface UploadUtil {
	List<FileDTO> uploadFile(
			List<MultipartFile> files,
			String uploadPath,
			int fileTypeCondition, //0:이미지 1:모든파일
			long fileSizeLimit, // 파일 용량 제한
			long totalFileSizeLimit, //총 파일 용량 제한
			long addFileSize //현재까지 업로드 된 용량을 더해서 보냄
			) throws Exception;
	
}
