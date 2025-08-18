package com.jw.jsp_mybatis_board_servletapi.controller;

import com.jw.jsp_mybatis_board_servletapi.dao.MemberDAO;
import com.jw.jsp_mybatis_board_servletapi.dto.memberDTO.LoginDTO;
import com.jw.jsp_mybatis_board_servletapi.dto.memberDTO.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MemberController {


    @Autowired
    MemberDAO mDAO;

    //회원가입 페이지로 가기
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String Signup(HttpServletRequest req) {
        req.setAttribute("lp", "loginBox.jsp");
        req.setAttribute("cp", "member/signup.jsp");
        return "index";
    }

    //회원가입
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String Signup(MemberDTO mDTO, HttpServletRequest req) {
        mDAO.SignupMember(mDTO, req);
        return "redirect:/";
    }

    //아이디 중복체크
    @RequestMapping(value = "/checkLoginIdExists", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> checkLoginIdExists(@RequestParam("login_id") String login_id) {
        boolean checkLoginIdExists = mDAO.checkLoginIdExists(login_id);

        Map<String, Object> result = new HashMap<>();
        result.put("checkLoginIdExists", checkLoginIdExists);
        result.put("message", checkLoginIdExists ? "이미 사용 중인 아이디입니다." : "사용 가능한 아이디입니다.");
        return result;
    }

    //닉네임 중복체크
    @RequestMapping(value = "/checkNicknameExists", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> checkNicknameExists(@RequestParam("nickname") String nickname) {
        boolean checkNicknameExists = mDAO.checkNicknameExists(nickname);

        Map<String, Object> result = new HashMap<>();
        result.put("checkNicknameExists", checkNicknameExists);
        result.put("message", checkNicknameExists ? "이미 사용 중인 닉네임입니다." : "사용 가능한 닉네임입니다.");
        return result;
    }

    //로그인
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginMember(LoginDTO lDTO, HttpServletRequest req) {
        mDAO.LoginMember(lDTO, req);
        return "redirect:/";
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
        if (req.getSession().getAttribute("LoginMember") != null) {
            req.setAttribute("lp", "member/successLogin.jsp");
        } else {
            req.setAttribute("lp", "loginBox.jsp");
        }
        req.setAttribute("cp", "member/memberInfo.jsp");
        return "index";
    }

    //회원정보수정페이지 이동
    @RequestMapping(value = "/updateMemberInfo", method = RequestMethod.GET)
    public String goUpdateMemberInfo(HttpServletRequest req) {
        mDAO.getMemberInfo(req);
        if (req.getSession().getAttribute("LoginMember") != null) {
            req.setAttribute("lp", "member/successLogin.jsp");
        } else {
            req.setAttribute("lp", "loginBox.jsp");
        }
        req.setAttribute("cp","member/updateMember.jsp");
        return "index";
    }

    //회원정보수정 기능
    @RequestMapping(value = "/updateMemberInfo", method = RequestMethod.POST)
    public String updateMemberInfo(MemberDTO mDTO, HttpServletRequest req) {
        mDAO.updateMemberInfo(mDTO, req);
        return "redirect:/";
    }

    //회원탈퇴페이지 이동
    @RequestMapping(value = "/withdrawMember", method = RequestMethod.GET)
    public String goWithdrawMember(HttpServletRequest req) {

        if (req.getSession().getAttribute("LoginMember") != null) {
            req.setAttribute("lp", "member/successLogin.jsp");
        } else {
            req.setAttribute("lp", "loginBox.jsp");
        }
        req.setAttribute("cp", "member/withdrawMember.jsp");
        return "index";
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
