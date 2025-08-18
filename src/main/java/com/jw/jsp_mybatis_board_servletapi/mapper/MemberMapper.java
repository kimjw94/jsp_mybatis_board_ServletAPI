package com.jw.jsp_mybatis_board_servletapi.mapper;

import com.jw.jsp_mybatis_board_servletapi.dto.memberDTO.MemberDTO;
import com.jw.jsp_mybatis_board_servletapi.dto.memberDTO.LoginDTO;

import java.util.List;

public interface MemberMapper {
    int SignupMember(MemberDTO mDTO);
    List<MemberDTO> LoginMember(LoginDTO lDTO);
    MemberDTO getMemberInfo(Long member_Id);
    int UpdateMember(MemberDTO mDTO);
    int withdrawMember(MemberDTO mDTO);
    int checkLoginIdExists(String login_id);
    int checkNicknameExists(String nickname);
}
