package com.jw.jsp_mybatis_board_servletapi.controller;

import com.jw.jsp_mybatis_board_servletapi.dao.MemberDAO;
import com.jw.jsp_mybatis_board_servletapi.dto.memberDTO.LoginDTO;
import com.jw.jsp_mybatis_board_servletapi.dto.memberDTO.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MemberController {


    @Autowired
    MemberDAO mDAO;

    //회원가입 페이지로 가기
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String Signup() {
        return "/member/signup";
    }

    //회원가입
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String Signup(MemberDTO mDTO, HttpServletRequest req) {
        mDAO.SignupMember(mDTO, req);
        return "index";
    }

    //로그인
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginMember(LoginDTO lDTO, HttpServletRequest req) {

        mDAO.LoginMember(lDTO, req);
        if (req.getSession().getAttribute("LoginMember") != null) {
            return "member/successLogin";
        } else {
            return "member/failLogin";
        }

    }

    //로그아웃
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutMember(HttpServletRequest req) {
        req.getSession().setAttribute("LoginMember", null);
        return "redirect:/";

    }

    //회원정보조회
    @RequestMapping(value = "/getMemberInfo", method = RequestMethod.GET)
    public String getMemberInfo(HttpServletRequest req) {
        mDAO.getMemberInfo(req);
        return "/member/memberInfo";
    }

    //회원정보수정페이지 이동
    @RequestMapping(value = "/updateMemberInfo", method = RequestMethod.GET)
    public String goUpdateMemberInfo(HttpServletRequest req) {
        mDAO.getMemberInfo(req);
        return "/member/updateMember";
    }

    //회원정보수정 기능
    @RequestMapping(value = "/updateMemberInfo", method = RequestMethod.POST)
    public String updateMemberInfo(MemberDTO mDTO, HttpServletRequest req) {
        mDAO.updateMemberInfo(mDTO, req);
        return "/member/memberInfo";
    }

    //회원탈퇴페이지 이동
    @RequestMapping(value = "/withdrawMember", method = RequestMethod.GET)
    public String goWithdrawMember(HttpServletRequest req) {
        return "/member/withdrawMember";
    }

    //회원탈퇴 기능
    @RequestMapping(value = "/withdrawMember", method = RequestMethod.POST)
    public String withdrawMember(MemberDTO mDTO, HttpServletRequest req) {
        boolean success = mDAO.withdrawMember(mDTO, req);
        if (success) {
            return "/index";
        } else {
            return "/member/withdrawMember"; // 탈퇴 실패 시 다시 탈퇴 페이지로 이동
        }
    }
}
