package com.jw.jsp_mybatis_board_servletapi.controller;

import com.jw.jsp_mybatis_board_servletapi.dao.BoardDAO;
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
                                      @RequestParam(value="page",required=false,defaultValue="1")int page,
                                      @RequestParam(value="size",required=false,defaultValue="20")int size,
                                      HttpServletRequest req) {
        bDAO.getBoardByCategoryIdWithPagination(category_id,page,size,req);
        return "board/boardBasicList";
    }

}
