package net.mirechoi.mcommunity.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.mirechoi.mcommunity.dto.BoardAdminDTO;
import net.mirechoi.mcommunity.dto.BoardCategory;
import net.mirechoi.mcommunity.dto.BoardDTO;
import net.mirechoi.mcommunity.dto.Users;
import net.mirechoi.mcommunity.mapper.UserMapper;
import net.mirechoi.mcommunity.service.BoardAdminService;
import net.mirechoi.mcommunity.service.BoardService;
import net.mirechoi.mcommunity.service.FileService;

@Controller
@RequestMapping("/board")
public class CommunityController {
   
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BoardAdminService baService;
	
	@Autowired
	private BoardService bservice;
	
	@Autowired
	   private FileService fileService;
	
	  @GetMapping("")
	   public String boardList(@RequestParam(value="bid", defaultValue = "1") int bid, Model model, HttpServletRequest request) {
		  
		  //쓰레기 파일 삭제를 위한 경로 세팅
		  	String uploadPath = request.getSession().getServletContext().getRealPath("res/upload/"+bid);
		  	fileService.trashFile(uploadPath);
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        System.out.println("로그인 정보 : " + auth.getName());
	        
	        if(auth != null) {
	           Users user = userMapper.getUserForUserid(auth.getName());
	           model.addAttribute("user", user);
	        }
	      
	      BoardAdminDTO adto = baService.getSelectById(bid);
	      List<BoardDTO> dtos = bservice.getAllList(bid);
	      
	      String skin = adto.getSkin();
	      
	      String skinPath = "/WEB-INF/views/board/"+skin+"/list.jsp";
	      model.addAttribute("skinPath", skinPath );
	      model.addAttribute("badmin", adto);   
	      model.addAttribute("lists", dtos );

	      return "board.list";
	   }

	   @GetMapping("/view")
	   public String boardView(@RequestParam("id") long id, Model model) {
	      System.out.println("daadad");
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        System.out.println("로그인 정보 : " + auth.getName());
	        
	        if(auth != null) {
	           Users user = userMapper.getUserForUserid(auth.getName());
	           model.addAttribute("user", user);
	        }
	      
	        BoardDTO dto = bservice.getBoardById(id);
	        BoardAdminDTO adto = baService.getSelectById(dto.getBbsid());
	      String skinPath = "/WEB-INF/views/board/"+adto.getSkin()+"/view.jsp";
	      model.addAttribute("skinPath", skinPath );
	      
	      
	        model.addAttribute("badmin", adto);
	        model.addAttribute("bbs", dto);      
	      
	      return "board.list";
	   }
		@PostMapping("/checkPass")
		@ResponseBody
		public String checkPassword(
				@RequestParam("id") int id, 
				@RequestParam("password") String password) {
			BoardDTO bDto = bservice.getBoardByPass(id, password);
			if(bDto == null) {
				return "failure";
			}else {
			    return "success";
			}    
		}
		

	@GetMapping("/write")
	   public String boardWriteForm(@RequestParam("bid") int bbsid, Model model) {
	      
	       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        
	        if(auth != null) {
	           Users user = userMapper.getUserForUserid(auth.getName());
	           model.addAttribute("user", user);
	        }
	      BoardAdminDTO adto = baService.getSelectById(bbsid);
	      String skinPath = "/WEB-INF/views/board/"+adto.getSkin()+"/write.jsp";
	      
	    //카테고리가 사용가능 이면 카테고리를 검증
			List<BoardCategory> categories = new ArrayList<>();
			
			if(adto.getCategory() == 1) {
				categories = baService.getAllCategory(bbsid);
			}
			 model.addAttribute("skinPath", skinPath );
			model.addAttribute("categories", categories);
	      model.addAttribute("badmin", adto);
	      
	      return "board.list";
	   }
	@PostMapping("/write")
	public String writeAction(
		   @RequestParam("bbsid") int bbsid,	
		   @RequestParam("writer") String writer,
		   @RequestParam("userid") String userid,
		   @RequestParam(value="password", required=false) String password,
		   @RequestParam("title") String title,
		   @RequestParam("content") String content,
		   @RequestParam(value="sec", defaultValue="0") byte sec,
		   @RequestParam("category") String category,
	       RedirectAttributes redirectAttributes) {
		
		   BoardDTO dto = new BoardDTO();
		   dto.setBbsid(bbsid);
		   dto.setWriter(writer);
		   dto.setUserid(userid);
		   dto.setPassword(password);
		   dto.setTitle(title);
		   dto.setContent(content);
		   dto.setSec(sec);
		   dto.setCategory(category);
		   
		
		   int res = bservice.insertBoard(dto);
		   if(res == 1) {
			   redirectAttributes.addFlashAttribute("message", "등록되었습니다.");
		   }else {
			   redirectAttributes.addFlashAttribute("message", "문제가 발생해서 등록에 실패했습니다.");
		   }
		
		return "redirect:/board?bid="+bbsid;
	}
}