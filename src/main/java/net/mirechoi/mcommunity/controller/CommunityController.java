package net.mirechoi.mcommunity.controller;

import java.sql.Timestamp;
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
	public String boardList(@RequestParam(value="bid", defaultValue = "1")int bid, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
			if(auth !=null) {
				Users user = userMapper.getUserForUserid(auth.getName());
				model.addAttribute("user",user);
			}
			
		BoardAdminDTO adto = baService.getSelectById(bid);
		List<BoardDTO> dtos = bservice.getAllList(bid);
		
		String skin = adto.getSkin();
		
		String skinPath = "/WEB-INF/views/board/"+skin+"/list.jsp";
		model.addAttribute("skinPath", skinPath );
		model.addAttribute("badmin", adto);
		model.addAttribute("lists",dtos);

		return "board.list";
	}
	
}