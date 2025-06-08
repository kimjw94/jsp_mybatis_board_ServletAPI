package com.jw.jsp_mybatis_board_servletapi.mapper;

import com.jw.jsp_mybatis_board_servletapi.dto.TestDTO;

import java.util.List;

public interface TestMapper {
    TestDTO selectById(TestDTO testDTO);
    int InsertTest(TestDTO testDTO);
    List<TestDTO>selectAllTest(TestDTO testDTO);
    int updateNameById(TestDTO tDTO);
    int deleteById(TestDTO testDTO);

}