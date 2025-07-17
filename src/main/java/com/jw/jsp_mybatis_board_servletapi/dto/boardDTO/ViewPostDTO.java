package com.jw.jsp_mybatis_board_servletapi.dto.boardDTO;

import java.util.Date;
import java.util.List;

public class ViewPostDTO {
    private Long board_id;
    private Long member_id;
    private Long board_category_id;
    private String title;
    private String contents;
    private Date created_at;
    private Long view_count;
    private Long recommend;
    private Long not_recommend;

    private String writer_nickname;           // ★ 새로 추가
    private String writer_profile_image;
    private String board_category_name;

    List<PostAttachmentDTO> attachments;

    public ViewPostDTO() {
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

    public Long getBoard_category_id() {
        return board_category_id;
    }

    public void setBoard_category_id(Long board_category_id) {
        this.board_category_id = board_category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Long getView_count() {
        return view_count;
    }

    public void setView_count(Long view_count) {
        this.view_count = view_count;
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

    public String getWriter_nickname() {
        return writer_nickname;
    }

    public void setWriter_nickname(String writer_nickname) {
        this.writer_nickname = writer_nickname;
    }

    public String getWriter_profile_image() {
        return writer_profile_image;
    }

    public void setWriter_profile_image(String writer_profile_image) {
        this.writer_profile_image = writer_profile_image;
    }

    public String getBoard_category_name() {
        return board_category_name;
    }

    public void setBoard_category_name(String board_category_name) {
        this.board_category_name = board_category_name;
    }

    public List<PostAttachmentDTO> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<PostAttachmentDTO> attachments) {
        this.attachments = attachments;
    }
}
