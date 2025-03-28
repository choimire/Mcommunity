package net.mirechoi.mcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.mirechoi.mcommunity.dto.BoardAdminDTO;
import net.mirechoi.mcommunity.dto.Users;
import net.mirechoi.mcommunity.mapper.UserMapper;
import net.mirechoi.mcommunity.service.BoardAdminService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BoardAdminService baService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("로그인 정보 : " + auth.getName());
        
        if(auth != null) {
        	Users user = userMapper.getUserForUserid(auth.getName());
        	model.addAttribute("user", user);
        }
        
        List<BoardAdminDTO> baList = baService.getAllList();
       // System.out.println(baList.toString());
        model.addAttribute("baLists", baList);
        
		return "main.home";
	}
	
	
	@GetMapping("/login")
	public String loginForm(@RequestParam(value="error", required=false) String error, Model model) {
		if(error != null)  model.addAttribute("errorMessage", "아이디 또는 비밀번호가 틀렸습니다.");
		
		return "main.login";
	}
	@GetMapping("/register")
	public String RegisterForm(Model model) {
        List<BoardAdminDTO> baList = baService.getAllList();
       // System.out.println(baList.toString());
        model.addAttribute("baLists", baList);
		return "main.register";
	}
	/** 이 부분은 직접 작업해 보세요 **/
	@PostMapping("/register")
	public String Register() {
		return "redirect: /";
	}
	

}
