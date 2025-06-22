package com.jw.jsp_mybatis_board_servletapi.dao;

import com.jw.jsp_mybatis_board_servletapi.dto.LoginDTO;
import com.jw.jsp_mybatis_board_servletapi.dto.MemberDTO;
import com.jw.jsp_mybatis_board_servletapi.mapper.MemberMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MemberDAO {

    @Autowired
    SqlSession ss;

    public void SignupMember(MemberDTO mDTO, HttpServletRequest req) {
        // 1. 업로드 경로 설정
        String uploadPath = req.getSession().getServletContext().getRealPath("/resources/profileImageUpload/");

        // 2. 업로드 폴더 존재 확인 및 생성
        File profileFolder = new File(uploadPath);
        if (!profileFolder.exists()) {
            profileFolder.mkdirs();
        }

        // 3. 설정 값 지정
        int maxSize = 10 * 1024 * 1024; // 10MB
        String encodingType = "UTF-8";

        try {
            // 4. MultipartRequest 객체 생성
            MultipartRequest mr = new MultipartRequest(req, uploadPath, maxSize, encodingType, new DefaultFileRenamePolicy());

            // 5. 업로드된 데이터 추출 및 DTO 세팅 (필드명 = 소문자 기준)
            String loginId = mr.getParameter("login_id");
            String password = mr.getParameter("password");
            String name = mr.getParameter("name");
            String gender = mr.getParameter("gender");
            String nickname = mr.getParameter("nickname");

            // 프로필 이미지
            String profileImageName = mr.getFilesystemName("profile_image");
            if (profileImageName == null || profileImageName.isEmpty()) {
                profileImageName = "defaultImage.png";
            }

            // 이메일 처리
            String emailId = mr.getParameter("email_first");
            String emailDomain;
            if ("Enter_directly".equals(mr.getParameter("email_domain"))) {
                emailDomain = mr.getParameter("email_domain_direct_enter");
            } else {
                emailDomain = mr.getParameter("email_domain");
            }
            String email = emailId + "@" + emailDomain;

            // 생년월일 처리
            String birthdayStr = mr.getParameter("birthday");
            Date birthday = null;
            if (birthdayStr != null && !birthdayStr.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                birthday = sdf.parse(birthdayStr);
            }

            // DTO에 세팅
            mDTO.setLogin_id(loginId);
            mDTO.setPassword(password);
            mDTO.setName(name);
            mDTO.setGender(gender);
            mDTO.setNickname(nickname);
            mDTO.setProfile_image(profileImageName);
            mDTO.setEmail(email);
            mDTO.setBirthday(birthday);
            mDTO.setStatus("ACTIVE");

            // DB 저장
            int InsertMemberInfo = ss.getMapper(MemberMapper.class).SignupMember(mDTO);
            if (InsertMemberInfo == 1) {
                req.setAttribute("serverReply", "회원가입 성공");
            } else {
                req.setAttribute("serverReply", "회원가입 실패");
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("serverReply", "회원가입 중 오류 발생");
        }
    }


    //로그인 기능
    public void LoginMember(LoginDTO lDTO, HttpServletRequest req) {
        try {
            //입력받은 아이디와 동일한 아이디 값을 가진 행 조회
            List<MemberDTO> loginUser = ss.getMapper(MemberMapper.class).LoginMember(lDTO);
            //아이디가 있다면 로그인 진행
            if (!loginUser.isEmpty()) {
                MemberDTO loginMember = loginUser.get(0);
                //DB에서 조회해서 List MemberDTO, 그 중 첫번째 행에 담긴 password 컬럼과 jsp에서 입력받은 ldto에 password가 동일한지 검사
                if (loginMember.getPassword().equals(lDTO.getPassword())) {
                    req.setAttribute("serverReply", "로그인 성공");
                    req.getSession().setAttribute("LoginMember", loginMember);
                    req.getSession().setMaxInactiveInterval(3000);
                } else {
                    req.setAttribute("serverReply", "로그인 실패:Password 틀림");
                }
            }else{
                req.setAttribute("serverReply","로그인 실패:ID 없음");
            }


        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("serverReply", "로그인 중 오류가 발생했습니다.");
        }

    }

}