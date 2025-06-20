package com.jw.jsp_mybatis_board_servletapi.mapper;

import com.jw.jsp_mybatis_board_servletapi.dto.MemberDTO;

public interface MemberMapper {
    int SignupMember(MemberDTO mDTO);
}
