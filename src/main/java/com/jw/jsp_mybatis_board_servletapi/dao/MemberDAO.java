package com.jw.jsp_mybatis_board_servletapi.dao;

import com.jw.jsp_mybatis_board_servletapi.dto.MemberDTO;
import com.jw.jsp_mybatis_board_servletapi.mapper.MemberMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MemberDAO {

    @Autowired
    SqlSession ss;

    public void SignupMember(MemberDTO mDTO, HttpServletRequest req) {

        //1. 업로드 경로 설정
        String uploadPath = req.getSession().getServletContext().getRealPath("/resources/profileImageUpload/");
        //2. 업로드 폴더 존재 확인 및 생성
        File profileFolder = new File(uploadPath);
        if (!profileFolder.exists()) {
            profileFolder.mkdirs();
        }
        //3. 설정 값 지정
        int maxSize = 10 * 1024 * 1024; // 10MB
        String encodingType = "UTF-8";

        //4. MutlipartRequest 객체 생성
        MultipartRequest mr = null;
        try {
            // MultipartRequest를 여기서 한 번만 생성합니다.
            mr = new MultipartRequest(req, uploadPath, maxSize, encodingType, new DefaultFileRenamePolicy());

            //업로드된 데이터 추출 및 DTO 세팅
            String loginId = mr.getParameter("LOGIN_ID");
            String password = mr.getParameter("PASSWORD");
            String name = mr.getParameter("NAME");
            String gender = mr.getParameter("GENDER");
            String nickname = mr.getParameter("NICKNAME");

            //프로필 이미지 첨부 로직
            String profileImageName = mr.getFilesystemName("PROFILE_IMAGE");
            if (profileImageName == null || profileImageName.isEmpty()) {
                profileImageName = "defaultImage.png";
            }

            //이메일 처리 로직
            String emailId = mr.getParameter("EMAIL_FIRST");
            String emailDomain = null;
            if (("Enter_directly").equals(mr.getParameter("EMAIL_DOMAIN"))) {
                emailDomain = mr.getParameter("EMAIL_DOMAIN_DIRECT_ENTER");
            } else {
                emailDomain = mr.getParameter("EMAIL_DOMAIN");
            }
            String email = emailId + "@" + emailDomain;

            //생년월일
            String birthdayStr = mr.getParameter("BIRTHDAY");
            Date birthday = null;
            if (birthdayStr != null && !birthdayStr.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                birthday = sdf.parse(birthdayStr);
            }

            //DTO 에 수동 입력
            mDTO.setLogin_id(loginId);
            mDTO.setPassword(password);
            mDTO.setName(name);
            mDTO.setGender(gender);
            mDTO.setNickname(nickname);
            mDTO.setProfile_image(profileImageName);
            mDTO.setEmail(email);
            mDTO.setBirthday(birthday);
            mDTO.setStatus("ACTIVE");

            int InsertMemberInfo = ss.getMapper(MemberMapper.class).SignupMember(mDTO);
            if(InsertMemberInfo==1){
                req.setAttribute("serverReply", "회원가입 성공");
            } else{
                req.setAttribute("serverReply","회원가입 실패");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}