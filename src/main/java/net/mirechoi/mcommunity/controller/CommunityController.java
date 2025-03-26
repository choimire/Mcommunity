package net.mirechoi.mcommunity.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.mirechoi.mcommunity.dto.BoardAdminDTO;
import net.mirechoi.mcommunity.dto.BoardCategory;
import net.mirechoi.mcommunity.dto.BoardDTO;
import net.mirechoi.mcommunity.dto.Users;
import net.mirechoi.mcommunity.mapper.UserMapper;
import net.mirechoi.mcommunity.service.BoardAdminService;
import net.mirechoi.mcommunity.service.BoardService;

@Controller
@RequestMapping("/board")
public class CommunityController {
   
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BoardAdminService baService;
	
	@Autowired
	private BoardService bservice;
	
	  @GetMapping("")
	   public String boardList(@RequestParam(value="bid", defaultValue = "1") int bid, Model model) {
	      
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

}