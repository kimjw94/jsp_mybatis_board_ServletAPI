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
                    req.getSession().setMaxInactiveInterval(3600);
                } else {
                    req.setAttribute("serverReply", "로그인 실패:Password 틀림");
                }
            } else {
                req.setAttribute("serverReply", "로그인 실패:ID 없음");
            }


        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("serverReply", "로그인 중 오류가 발생했습니다.");
        }

    }

    //회원정보 조회 기능 (로그인 체크로 사용 가능)
    public void getMemberInfo(HttpServletRequest req) {
        MemberDTO loginUser = (MemberDTO) req.getSession().getAttribute("LoginMember");

        if (loginUser != null) {
            long memberId = loginUser.getMember_id();
            MemberDTO memberInfo = ss.getMapper(MemberMapper.class).getMemberInfo(memberId);
            req.setAttribute("memberInfo", memberInfo);
        } else {
            req.setAttribute("serverReply", "로그인 정보가 없음");
        }
    }

    //회원 정보 수정 기능
    public void updateMemberInfo(MemberDTO mDTO, HttpServletRequest req) {
        // 프로필이미지의 존재로 MultipartRequest 사용 준비
        // 단, 설정은 회원가입시 설정과 동일해야함
        String uploadPath = req.getSession().getServletContext().getRealPath("/resources/profileImageUpload/");

        File profileFolder = new File(uploadPath);
        if (!profileFolder.exists()) {
            profileFolder.mkdirs();
        }
        int maxSize = 10 * 1024 * 1024; // 10MB
        String encodingType = "UTF-8";

        MultipartRequest mr = null;
        //또한 회원 정보 기능을 사용해서 현재와 일치한지 비교해야하기 때문에 LoginMember Session 가져옴
        MemberDTO loginUser = (MemberDTO) req.getSession().getAttribute("LoginMember");
        if (loginUser == null) {
            req.setAttribute("serverReply", "로그인이 필요합니다.");
            return;
        }
        try {
            mr = new MultipartRequest(req, uploadPath, maxSize, encodingType, new DefaultFileRenamePolicy());

            String newProfileImageName = mr.getFilesystemName("profile_image");
            if (newProfileImageName == null || newProfileImageName.isEmpty()) {
                newProfileImageName = loginUser.getProfile_image();
            }

            String newPassword = mr.getParameter("password");
            if (newPassword == null || newPassword.isEmpty() || newPassword.equals(loginUser.getPassword())) {
                newPassword = loginUser.getPassword();
            }

            String newNickname = mr.getParameter("nickname");
            if (newNickname == null || newNickname.isEmpty() || newNickname.equals(loginUser.getNickname())) {
                newNickname = loginUser.getNickname();
            }
            String newEmail = mr.getParameter("email");

            if (newEmail == null || newEmail.isEmpty() || newEmail.equals(loginUser.getEmail())) {
                newEmail = loginUser.getEmail();
            }
            //where절로 조회를 인한 setting
            mDTO.setMember_id(loginUser.getMember_id());

            //변경값 세팅
            mDTO.setProfile_image(newProfileImageName);
            mDTO.setPassword(newPassword);
            mDTO.setNickname(newNickname);
            mDTO.setEmail(newEmail);

            // mapper를 통해 수정
            int UpdateMemberInfo = ss.getMapper(MemberMapper.class).UpdateMember(mDTO);

            //수정이 완료되면 갱신된 정보를 Session에 있던 정보로 넣으며 새로 갱신
            if (UpdateMemberInfo == 1) {
                MemberDTO updatedInfo = ss.getMapper(MemberMapper.class).getMemberInfo(loginUser.getMember_id());
                req.getSession().setAttribute("LoginMember", updatedInfo);
                req.setAttribute("memberInfo", updatedInfo);
                req.setAttribute("serverReply", "회원정보 수정 성공");
            } else {
                req.setAttribute("serverReply", "회원정보 수정 실패");
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("serverReply", "회원정보 수정 중 오류가 발생했습니다.");
        }


    }

    //회원탈퇴(논리적탈퇴)
    public boolean withdrawMember(MemberDTO mDTO, HttpServletRequest req) {
        String inputPw = req.getParameter("password");
        MemberDTO loginUser = (MemberDTO) req.getSession().getAttribute("LoginMember");

        if (!loginUser.getPassword().equals(inputPw)) {
            req.setAttribute("serverReply", "탈퇴 실패: 비밀번호가 다릅니다.");
            return false;
        }

        // 랜덤 ID 생성 및 중복 검사
        String loginId;
        do {
            int number = (int) (Math.random() * 1_000_000);
            String sixDigits = String.format("%06d", number);
            loginId = "탈퇴한 회원" + sixDigits;
        } while (ss.getMapper(MemberMapper.class).checkLoginIdExists(loginId) > 0);

        // 탈퇴용 DTO 생성
        mDTO.setMember_id(loginUser.getMember_id()); // WHERE 조건용
        mDTO.setLogin_id(loginId);
        mDTO.setNickname(loginId);
        mDTO.setStatus("WITHDRAWN");

        int result = ss.getMapper(MemberMapper.class).withdrawMember(mDTO);
        if (result == 1) {
            req.getSession().invalidate(); // 세션 만료
            req.setAttribute("serverReply", "회원 탈퇴 완료");
            return true;
        } else {
            req.setAttribute("serverReply", "회원 탈퇴 실패: DB 오류");
            return false;
        }
    }

}

