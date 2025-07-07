package com.jw.jsp_mybatis_board_servletapi.dao;

import com.jw.jsp_mybatis_board_servletapi.dto.boardDTO.BoardViewDTO;
import com.jw.jsp_mybatis_board_servletapi.mapper.BoardMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class BoardDAO {

    @Autowired
    SqlSession ss;

    public void getBoardByCategoryIdWithPagination(Long category_id, int page, int size, HttpServletRequest req) {
        //페이징을 위한 startRow 및 endRow 계산
        int startRow = (page - 1) * size + 1;
        int endRow = page * size;
        // 2. 게시글 목록 조회
        List<BoardViewDTO> boardList = ss.getMapper(BoardMapper.class).selectBoardByCategoryIdWithPagination(category_id, startRow, endRow);
        // 3. 게시글 총 갯수 조회
        int totalRows = ss.getMapper(BoardMapper.class).countTotalRowsBoardByCategoryId(category_id);
        // 4. 전체 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalRows / size);


        req.setAttribute("boardList", boardList);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
    }
}



