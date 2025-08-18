package com.jw.jsp_mybatis_board_servletapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    //메인으로 가기
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest req) {
        if (req.getSession().getAttribute("LoginMember") != null) {
            req.setAttribute("lp", "member/successLogin.jsp");
        } else {
            req.setAttribute("lp", "loginBox.jsp");
        }
        req.setAttribute("cp","home.jsp");
        return "index";
    }


}
