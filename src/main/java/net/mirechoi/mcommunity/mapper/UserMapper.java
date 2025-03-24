package net.mirechoi.mcommunity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.mirechoi.mcommunity.dto.Users;

@Mapper
public interface UserMapper {

		List<Users> allUser();
		Users getUserForUserid(String userid);
		Users setInsertUser(Users user);
		Users setUpdateUser(Users user);
		Users setDeleteUser(Users user);
		
}
