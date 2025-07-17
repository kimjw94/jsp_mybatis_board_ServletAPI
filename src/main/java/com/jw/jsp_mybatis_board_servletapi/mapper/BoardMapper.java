package com.jw.jsp_mybatis_board_servletapi.mapper;

import com.jw.jsp_mybatis_board_servletapi.dto.boardDTO.*;

import java.util.List;

public interface BoardMapper {
    List<BoardViewDTO> selectBoardByCategoryIdWithPagination(long category_id,int startRow,int endRow);
    int countTotalRowsBoardByCategoryId(long category_id);
    int createPost(InsertPostDTO insertPostDTO);
    int insertPostAttachment(PostAttachmentDTO postAttachmentDTO);
    ViewPostDTO getPostById(long board_id);
    List<PostAttachmentDTO> getAttachmentsByBoardId(long board_id);
    int InsertComment(InsertCommentDTO insertCommentDTO);
    List<GetBoardCommentDTO> getCommentsbyBoardId(long board_id);
}
