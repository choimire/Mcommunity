package net.mirechoi.mcommunity.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import net.mirechoi.mcommunity.dto.FileDTO;

@Component
public class FileUploadUtil implements UploadUtil {

	@Override
	public List<FileDTO> uploadFile(
			List<MultipartFile> files, 
			String uploadPath, 
			int fileTypeCondition,
			long fileSizeLimit, 
			long totalFileSizeLimit,
			long addFileSize
			) throws Exception {
			
		List<FileDTO> resultList = new ArrayList<>();
		long totalSize=0;
		
		for(MultipartFile file: files) {
			if(file.isEmpty()) continue;
			
			//1.확장자 체크
			String ext =FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
			if(fileTypeCondition == 0 && !isImage(ext)) {
				throw new IllegalArgumentException("이미지만 업로드 가능합니다.");
			}
			//2.개별 사이즈 체크
			long fileSize = file.getSize();
			if(fileSize > fileSizeLimit) {
				throw new IllegalArgumentException("파일용량이 초과하였습니다.");
			}
			//3.토탈 사이즈 체크
			totalSize += fileSize;
			totalSize = addFileSize + fileSize;
			if(totalSize >totalFileSizeLimit) {
				throw new IllegalArgumentException("전체 업로드 용량을 초과하였습니다. 더 이상 업로드 할 수 없습니다.");
			}
			//4.이름 변환
			String uuid = UUID.randomUUID().toString();
			String nFilename= uuid + "." + ext;
			
			//5.업로드
			File targetDir = new File(uploadPath);
			if(!targetDir.exists()) targetDir.mkdir();
			
			//6.저장
			File destination = new File(targetDir,nFilename);
			file.transferTo(destination);
			
			FileDTO fdto = new FileDTO();
			fdto.setOfilename(file.getOriginalFilename());
			fdto.setNfilename(nFilename);
			fdto.setExt(ext);
			fdto.setFilesize(fileSize);
			fdto.setAddFileSize(addFileSize);
			
			
			resultList.add(fdto);
		}
		return null;
	}
	
	private boolean isImage(String ext) {
		return Arrays.asList("jpg" , "jpeg", "png", "gif", "webp", "svg" , "bmp").contains(ext);
	}
}
