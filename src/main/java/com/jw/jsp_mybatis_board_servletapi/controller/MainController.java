package com.jw.jsp_mybatis_board_servletapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    //메인으로 가기
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }


}
