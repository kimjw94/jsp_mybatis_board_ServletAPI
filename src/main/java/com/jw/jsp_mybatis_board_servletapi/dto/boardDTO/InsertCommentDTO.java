package com.jw.jsp_mybatis_board_servletapi.dto.boardDTO;

import java.util.Date;

public class InsertCommentDTO {
    private Long board_comment_id;
    private Long board_id;
    private Long member_id;
    private Long parent_id;
    private String comments;
    private Date created_at;
    private String status;
    private Long recommend;
    private Long not_recommend;

    public InsertCommentDTO() {
    }

    public Long getBoard_comment_id() {
        return board_comment_id;
    }

    public void setBoard_comment_id(Long board_comment_id) {
        this.board_comment_id = board_comment_id;
    }

    public Long getBoard_id() {
        return board_id;
    }

    public void setBoard_id(Long board_id) {
        this.board_id = board_id;
    }

    public Long getMember_id() {
        return member_id;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getRecommend() {
        return recommend;
    }

    public void setRecommend(Long recommend) {
        this.recommend = recommend;
    }

    public Long getNot_recommend() {
        return not_recommend;
    }

    public void setNot_recommend(Long not_recommend) {
        this.not_recommend = not_recommend;
    }
}
