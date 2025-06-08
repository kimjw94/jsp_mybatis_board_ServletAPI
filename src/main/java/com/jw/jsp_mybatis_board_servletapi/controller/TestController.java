package com.jw.jsp_mybatis_board_servletapi.controller;

import com.jw.jsp_mybatis_board_servletapi.dao.TestDAO;
import com.jw.jsp_mybatis_board_servletapi.dto.TestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Controller
public class TestController {

    @Autowired
    TestDAO tDAO;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(HttpServletResponse res, HttpServletRequest req) {
        req.setAttribute("msg", "테스트 정상 작동");
        return "test";
    }


    @RequestMapping(value="/insert", method=RequestMethod.POST)
    public String insert(TestDTO tDTO, HttpServletRequest req) {
    tDAO.insertTest(tDTO,req);
      return"test2";
    }

    @RequestMapping(value="/selectById", method=RequestMethod.POST)
    public String selectById(TestDTO tDTO, HttpServletRequest req) {
        tDAO.selectById(tDTO, req);
        return "test2";
    }

    @RequestMapping(value="/selectAllTest", method=RequestMethod.POST)
    public String selectAllTest(TestDTO tDTO, HttpServletRequest req) {
        tDAO.selectAllTest(tDTO, req);
        return "test2";
    }

    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String update(TestDTO tDTO, HttpServletRequest req) {
        tDAO.updateTest(tDTO,req);
        return"test2";
    }

    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public String delete(TestDTO tDTO, HttpServletRequest req) {
        tDAO.deleteTest(tDTO,req);
        return "test2";
    }



}
