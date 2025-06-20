package com.jw.jsp_mybatis_board_servletapi.controller;

import com.jw.jsp_mybatis_board_servletapi.dao.MemberDAO;
import com.jw.jsp_mybatis_board_servletapi.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    MemberDAO mDAO;


    //메인으로 가기
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String index() {
        return "index";
    }

    //회원가입 페이지로 가기
    @RequestMapping(value="/signup",method =RequestMethod.GET)
    public String signup(){
        return "/member/signup";
    }

    //회원가입
    @RequestMapping(value="/signup",method = RequestMethod.POST)
    public String signup(MemberDTO mDTO, HttpServletRequest req){
       mDAO.SignupMember(mDTO,req);
        return"index";
    }

}
