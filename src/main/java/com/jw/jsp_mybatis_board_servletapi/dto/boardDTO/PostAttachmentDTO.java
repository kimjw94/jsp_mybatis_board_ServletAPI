package com.jw.jsp_mybatis_board_servletapi.dto.boardDTO;

import java.util.Date;

public class PostAttachmentDTO {
    private Long board_attachment_id;
    private Long board_id;
    private String file_name;
    private String file_path;
    private String file_type;
    private Long file_size;
    private Date uploaded_at;

    public PostAttachmentDTO() {
    }

    public Long getBoard_attachment_id() {
        return board_attachment_id;
    }

    public void setBoard_attachment_id(Long board_attachment_id) {
        this.board_attachment_id = board_attachment_id;
    }

    public Long getBoard_id() {
        return board_id;
    }

    public void setBoard_id(Long board_id) {
        this.board_id = board_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public Long getFile_size() {
        return file_size;
    }

    public void setFile_size(Long file_size) {
        this.file_size = file_size;
    }

    public Date getUploaded_at() {
        return uploaded_at;
    }

    public void setUploaded_at(Date uploaded_at) {
        this.uploaded_at = uploaded_at;
    }
}


