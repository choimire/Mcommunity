package net.mirechoi.mcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mirechoi.mcommunity.dto.BoardDTO;
import net.mirechoi.mcommunity.mapper.BoardMapper;

@Service
public class BoardService {
	
	@Autowired //mapper를 가져옴
	
	private BoardMapper bMapper;
	
	public  List<BoardDTO> getAllList(int bbsid){
		return bMapper.allList(bbsid);
		
	}
	
	public BoardDTO getBoardById(long id) {
		return bMapper.getBoardById(id);
		
	}
	
	public int insertBoard(BoardDTO dto) {
		return bMapper.setBoard(dto);
		
	}
	
	public int updateBoard(BoardDTO dto) {
		return bMapper.setUpdateBoard(dto);
		
	}
	
	public int deleteBoard(long id) {
		return bMapper.setDeleteBoard(id);
	}
}


