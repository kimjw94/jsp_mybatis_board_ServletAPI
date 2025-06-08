package com.jw.jsp_mybatis_board_servletapi.dao;

import com.jw.jsp_mybatis_board_servletapi.dto.TestDTO;
import com.jw.jsp_mybatis_board_servletapi.mapper.TestMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class TestDAO {

    @Autowired
    private SqlSession ss;

    public void insertTest(TestDTO tDTO, HttpServletRequest req) {
        Long test_id = Long.parseLong(req.getParameter("test_id"));
        String test_name = req.getParameter("test_name");
        tDTO.setTest_id(test_id);
        tDTO.setTest_name(test_name);

        int result = ss.getMapper(TestMapper.class).InsertTest(tDTO);
        if (result == 1) {
            req.setAttribute("resultInsert", "Insert Success");
        } else {
            req.setAttribute("resultInsert", "Insert Fail");
        }
    }

    public void selectById(TestDTO tDTO, HttpServletRequest req) {
        TestDTO result = ss.getMapper(TestMapper.class).selectById(tDTO);
        req.setAttribute("resultSelect", result);
    }

    public void selectAllTest(TestDTO tDTO, HttpServletRequest req) {
        String test_name = req.getParameter("test_name");
        tDTO.setTest_name(test_name);
        List<TestDTO> result = ss.getMapper(TestMapper.class).selectAllTest(tDTO);
        req.setAttribute("resultSelectAll", result);
    }

    public void updateTest(TestDTO tDTO, HttpServletRequest req) {
        int resultUpdate = ss.getMapper(TestMapper.class).updateNameById(tDTO);
        if (resultUpdate >= 1) {
            req.setAttribute("resultUpdate", "Update Success");
        } else {
            req.setAttribute("resultUpdate", "Update Fail");
        }

    }

    public void deleteTest(TestDTO tDTO, HttpServletRequest req) {
        int resultDelete = ss.getMapper(TestMapper.class).deleteById(tDTO);
        if (resultDelete >= 1) {
            req.setAttribute("resultDelete", "Delete Success");
        } else {
            req.setAttribute("resultDelete", "Delete Fail");
        }
    }
}
