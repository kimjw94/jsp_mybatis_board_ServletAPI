package com.jw.jsp_mybatis_board_servletapi.dao;

import com.jw.jsp_mybatis_board_servletapi.dto.boardDTO.*;
import com.jw.jsp_mybatis_board_servletapi.dto.memberDTO.MemberDTO;
import com.jw.jsp_mybatis_board_servletapi.mapper.BoardMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Service
public class BoardDAO {

    @Autowired
    SqlSession ss;

    public void getBoardByCategoryIdWithPagination(Long category_id, Integer page, Integer size, HttpServletRequest req) {
        //페이징을 위한 startRow 및 endRow 계산
        int startRow = (page - 1) * size + 1;
        int endRow = page * size;
        // 2. 게시글 목록 조회
        List<BoardViewDTO> boardList = ss.getMapper(BoardMapper.class).selectBoardByCategoryIdWithPagination(category_id, startRow, endRow);
        // 3. 게시글 총 갯수 조회
        int totalRows = ss.getMapper(BoardMapper.class).countTotalRowsBoardByCategoryId(category_id);
        // 4. 전체 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalRows / size);


        req.setAttribute("boardList", boardList);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
    }

    //게시글 작성 비즈니스 로직
    public long createPost(HttpServletRequest req) {
        //MultipartRequest 설정
        String path = req.getSession().getServletContext().getRealPath("/resources/img/boardAttachment/");
        File uploadAttachment = new File(path);

        //path 에 폴더 없으면 생성
        if (!uploadAttachment.exists()) {
            uploadAttachment.mkdirs();
        }

        //최대 10MB
        int maxSize = 10 * 1024 * 1024;

        MultipartRequest mr = null;
        Long board_category_id = null;
        try {
            mr = new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());

            //member_id 가져오기
            MemberDTO loginUser = (MemberDTO) req.getSession().getAttribute("LoginMember");
            Long member_id = loginUser.getMember_id();

            //board_category_id 가져오기
            board_category_id = Long.parseLong(mr.getParameter("board_category_id"));

            //title 가져오기
            String title = mr.getParameter("title");

            //내용 가져오기
            String contents = mr.getParameter("contents");

            //DTO 에 넣기
            InsertPostDTO insertPostDTO = new InsertPostDTO();
            insertPostDTO.setBoard_category_id(board_category_id);
            insertPostDTO.setMember_id(member_id);
            insertPostDTO.setTitle(title);
            insertPostDTO.setContents(contents);

            //DB 매핑하기
            int InsertPost = ss.getMapper(BoardMapper.class).createPost(insertPostDTO);

            //매핑 결과 확인
            if (InsertPost > 0) {
                req.setAttribute("serverReply", "게시글 작성 완료");

            } else {
                req.setAttribute("serverReply", "게시글 작성 실패");
            }


            Long board_id = insertPostDTO.getBoard_id();
            //첨부파일 처리
            String[] fileNames = {"boardAttachment1", "boardAttachment2", "boardAttachment3", "boardAttachment4", "boardAttachment5"};
            for (String fileName : fileNames) {
                String file_name = mr.getFilesystemName(fileName);
                if (file_name != null && !file_name.isEmpty()) {
                    File uploadedfile = new File(path + file_name);
                    String fileType = req.getServletContext().getMimeType(file_name);
                    long file_size = uploadedfile.length();

                    // DTO 새로 생성 (재사용 금지!)
                    PostAttachmentDTO attachmentDTO = new PostAttachmentDTO();
                    attachmentDTO.setBoard_id(board_id);
                    attachmentDTO.setFile_name(file_name);
                    attachmentDTO.setFile_size(file_size);
                    attachmentDTO.setFile_type(fileType);

                    // DB에는 상대경로 또는 웹 접근 경로만 저장
                    attachmentDTO.setFile_path("/resources/img/boardAttachment/" + file_name);

                    ss.getMapper(BoardMapper.class).insertPostAttachment(attachmentDTO);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return board_category_id;
    }

    //게시글 조회 로직
    public void getPostById(Long board_id,
                            HttpServletRequest req) {
        ViewPostDTO post = ss.getMapper(BoardMapper.class).getPostById(board_id);
        List<PostAttachmentDTO> files = ss.getMapper(BoardMapper.class).getAttachmentsByBoardId(board_id);
        // 디버깅 코드
        if (files != null && !files.isEmpty()) {
            System.out.println("첨부 파일이 " + files.size() + "개 발견되었습니다.");
            for (PostAttachmentDTO attachment : files) {
                System.out.println("파일 이름: " + attachment.getFile_name());
            }
        } else {
            System.out.println("첨부 파일이 없거나 조회에 실패했습니다.");
        }
        post.setAttachments(files);
        req.setAttribute("post", post);

    }

    //댓글 등록 로직
    public void InsertBoardComment(Long board_id,
                                   InsertCommentDTO insertCommentDTO,
                                   HttpServletRequest req) {

        insertCommentDTO.setBoard_id(board_id);

        MemberDTO loginUser = (MemberDTO) req.getSession().getAttribute("LoginMember");
        long member_id = loginUser.getMember_id();
        insertCommentDTO.setMember_id(member_id);

        insertCommentDTO.setParent_id(null);

        System.out.println("parent_id = " + insertCommentDTO.getParent_id());


        int InsertCommentResult = ss.getMapper(BoardMapper.class).InsertComment(insertCommentDTO);

        if (InsertCommentResult > 0) {
            req.setAttribute("serverReply", "댓글 등록 성공");
        } else {
            req.setAttribute("serverReply", "댓글 등록 실패");
        }
    }

    //댓글 조회 로직
    public void getCommentsbyBoardId(Long board_id,
                                     HttpServletRequest req) {
        List<GetBoardCommentDTO> getBoardComments = ss.getMapper(BoardMapper.class).getCommentsbyBoardId(board_id);
        req.setAttribute("commentList", getBoardComments);
    }

}




