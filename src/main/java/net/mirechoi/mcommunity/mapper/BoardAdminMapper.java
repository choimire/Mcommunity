package net.mirechoi.mcommunity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.mirechoi.mcommunity.dto.BoardAdminDTO;
import net.mirechoi.mcommunity.dto.BoardCategory;

@Mapper
public interface BoardAdminMapper {
	
	int setBoardAdmin(String btitle);
	int updateBoardAdmin(BoardAdminDTO dto);
	int deleteBoardAdmin(int id);
	BoardAdminDTO getBoardAdmin(int id);
	List<BoardAdminDTO> allList();
	List<BoardCategory> allCategoryByBoardId(int id);
	int updateBoardFileAdmin(BoardAdminDTO dto);
}
