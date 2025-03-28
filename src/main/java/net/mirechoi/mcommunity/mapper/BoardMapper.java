package net.mirechoi.mcommunity.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.mirechoi.mcommunity.dto.BoardDTO;

@Mapper
public interface BoardMapper {
	
	List<BoardDTO> allList(int bid);
	BoardDTO getBoardById(long id);
	int setBoard(BoardDTO dto);
	int setUpdateBoard(BoardDTO dto);
	int setDeleteBoard(long id);
	BoardDTO getBoardByPassword(Map<String, Object> params);
}
