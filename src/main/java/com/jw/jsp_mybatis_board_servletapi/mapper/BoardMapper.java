package com.jw.jsp_mybatis_board_servletapi.mapper;

import com.jw.jsp_mybatis_board_servletapi.dto.boardDTO.BoardViewDTO;

import java.util.List;

public interface BoardMapper {
    List<BoardViewDTO> selectBoardByCategoryIdWithPagination(long category_id,int startRow,int endRow);
    int countTotalRowsBoardByCategoryId(long category_id);
}
