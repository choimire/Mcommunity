package net.mirechoi.mcommunity.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.mirechoi.mcommunity.dto.BoardAdminDTO;
import net.mirechoi.mcommunity.dto.FileDTO;
import net.mirechoi.mcommunity.service.BoardAdminService;
import net.mirechoi.mcommunity.service.FileService;
import net.mirechoi.mcommunity.util.UploadUtil;

@Controller	
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private UploadUtil uploadUtil;
	
	@Autowired
	private BoardAdminService baService;
	
	@Autowired
	private FileService fileService;
	
	@PostMapping("/upload")
	@ResponseBody	
	public ResponseEntity<?> uploadFileAjax(
			@RequestParam("files") List<MultipartFile> files,
			@RequestParam("tempBid") long bid,
			@RequestParam("bbsid") int bbsid,
			@RequestParam("addFileSize") long addFileSize,
			HttpServletRequest request
			){
		
		
			//관리자 보드 검색
		BoardAdminDTO adto = baService.getSelectById(bbsid);
		
		int fileTypeCondition = adto.getFilechar();
		long fileSizeLimit = adto.getFilesize();
		long totalFileSizeLimit = adto.getAllfilesize();
		String uploadPath = request.getSession().getServletContext().getRealPath("res/upload/"+bbsid);
		System.out.println(uploadPath);
		
		try {
			List<FileDTO> uploadFiles = uploadUtil.uploadFile(
					files, 
					uploadPath, 
					fileTypeCondition, 
					fileSizeLimit, 
					totalFileSizeLimit, 
					addFileSize
					
					);
			System.out.println(addFileSize + "," + totalFileSizeLimit + "," + fileSizeLimit + ","+fileTypeCondition);
			for(FileDTO dto : uploadFiles) {
				dto.setBid(bid);
				fileService.insertFile(dto);
			}
			
			return ResponseEntity.ok(uploadFiles);
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
	}

		@PostMapping("/delete")
		   @ResponseBody
		   public String deleteFile(
		         @RequestParam("fileName") String fileName
		         , @RequestParam("bbsid") int bbsid
		         , HttpServletRequest request){
			System.out.println("와라");
			System.out.println(fileName + "," +bbsid);
		      try {
		      String deletePath = request.getSession().getServletContext().getRealPath("/res/upload/" + bbsid);
		      File file = new File(deletePath, fileName);
		      
		      boolean fileDeleted = false;
		      
		//      DB에서 크기 가져옴
		      FileDTO fdto = fileService.selectFileByFileName(fileName);
		      
		      if(file.exists()) {
		    	  fileDeleted = file.delete();
		      }
		//      내일 삭제완료
		      if(fdto != null){
		    	  fileService.deleteFile(fdto.getId());
		      }
		      return fileDeleted? "1" : "0";
		      }catch(Exception e) {
		    	  return "0";
		      }
   }
}
