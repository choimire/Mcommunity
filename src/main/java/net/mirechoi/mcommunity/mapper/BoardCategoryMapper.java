package net.mirechoi.mcommunity.mapper;

import net.mirechoi.mcommunity.dto.BoardCategory;

public interface BoardCategoryMapper {
	int saveCategory(BoardCategory category);
	int deleteCategory(int id);
	int updateCategory(BoardCategory category);
	BoardCategory selectBoardCategoryById(int id);

}
