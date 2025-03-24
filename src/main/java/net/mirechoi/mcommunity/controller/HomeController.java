package net.mirechoi.mcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.mirechoi.mcommunity.dto.Users;
import net.mirechoi.mcommunity.mapper.UserMapper;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth !=null) {
			Users user = userMapper.getUserForUserid(auth.getName());
			model.addAttribute("user",user);
		}
		
		return "main.home";
	}
	
	@GetMapping("/login")
	public String loginForm(@RequestParam(value="error", required=false)String error, Model model) {
		if(error !=null) model.addAttribute("errorMessage","아이디 또는 비밀번호가 틀렸습니다.");
		
		return "main.login";
	}
	

}
