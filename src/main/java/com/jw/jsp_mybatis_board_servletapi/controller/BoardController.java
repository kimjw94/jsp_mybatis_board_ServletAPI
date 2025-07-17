package com.jw.jsp_mybatis_board_servletapi.controller;

import com.jw.jsp_mybatis_board_servletapi.dao.BoardDAO;
import com.jw.jsp_mybatis_board_servletapi.dto.boardDTO.InsertCommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BoardController {

    @Autowired
    BoardDAO bDAO;

    //게시판 페이지 이동
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public String goBoardByCategoryIdWithPagination(@RequestParam("category_id") Long category_id,
                                                    @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                    @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                                    HttpServletRequest req) {
        bDAO.getBoardByCategoryIdWithPagination(category_id, page, size, req);
        return "board/boardBasicList";
    }

    //게시글 작성 페이지 이동
    @RequestMapping(value = "/createPost", method = RequestMethod.GET)
    public String goCreatePostPage(HttpServletRequest req) {
        return "board/createPost";
    }

    //게시글 작성 페이지
    @RequestMapping(value = "/createPost", method = RequestMethod.POST)
    public String createPost(HttpServletRequest req) {
        long board_category_id = bDAO.createPost(req);
        return "redirect:/board?category_id=" + board_category_id;
    }

    //게시글 조회 페이지 + 댓글도 함께 조회해야함
    @RequestMapping(value = "/viewPost", method = RequestMethod.GET)
    public String goViewPostPage(@RequestParam("board_id") Long board_id,
                                 HttpServletRequest req) {
        bDAO.getPostById(board_id, req);
        bDAO.getCommentsbyBoardId(board_id, req);
        return "board/viewPost";
    }

    //댓글 등록 로직
    @RequestMapping(value = "/createComment", method = RequestMethod.POST)
    public String createComment(@RequestParam Long board_id,
                                InsertCommentDTO insertCommentDTO,
                                HttpServletRequest req) {
        bDAO.InsertBoardComment(board_id, insertCommentDTO, req);

        return "redirect:/viewPost?board_id=" + board_id;
    }
}
