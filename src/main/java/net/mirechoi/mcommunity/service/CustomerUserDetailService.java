package net.mirechoi.mcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.mirechoi.mcommunity.dto.Users;
import net.mirechoi.mcommunity.mapper.UserMapper;

@Service
public class CustomerUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		
		Users user = userMapper.getUserForUserid(userid);
		if(user == null){
			throw new UsernameNotFoundException("회원정보가 없습니다.");
			
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
		
	      return new User(user.getUserid(), user.getUserpass(), authorities);
	   }

	}
	
	
