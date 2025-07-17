-- ================================
-- 1. 테이블 생성
-- ================================

-- 게시판 카테고리 테이블
CREATE TABLE BOARD_CATEGORY (
                                BOARD_CATEGORY_ID   NUMBER(19) PRIMARY KEY,
                                BOARD_CATEGORY_NAME VARCHAR2(50) NOT NULL,
                                BOARD_TYPE VARCHAR2(50) NOT NULL
);

-- 시퀀스
CREATE SEQUENCE BOARD_CATEGORY_ID_SEQ;

INSERT INTO BOARD_CATEGORY VALUES (BOARD_CATEGORY_ID_SEQ.nextval,'funny','기본');
INSERT INTO BOARD_CATEGORY VALUES (BOARD_CATEGORY_ID_SEQ.nextval,'picture','기본');
INSERT INTO BOARD_CATEGORY VALUES (BOARD_CATEGORY_ID_SEQ.nextval,'freeToTalK','기본');



DROP SEQUENCE BOARD_CATEGORY_ID_SEQ;
DROP TABLE BOARD_CATEGORY PURGE;

SELECT * FROM BOARD_CATEGORY;



-- 게시글 테이블
CREATE TABLE BOARD (
                       BOARD_ID           NUMBER(19) PRIMARY KEY,
                       MEMBER_ID          NUMBER(19) NOT NULL,
                       BOARD_CATEGORY_ID  NUMBER(19) NOT NULL,
                       TITLE              VARCHAR2(255) NOT NULL,
                       CONTENTS           CLOB NOT NULL,
                       CREATED_AT            DATE DEFAULT SYSDATE NOT NULL,
                       VIEW_COUNT         NUMBER(19) DEFAULT 0 NOT NULL,
                       RECOMMEND          NUMBER(19) DEFAULT 0 NOT NULL,
                       NOT_RECOMMEND      NUMBER(19) DEFAULT 0 NOT NULL,

                       CONSTRAINT fk_board_member
                           FOREIGN KEY (MEMBER_ID)
                               REFERENCES MEMBER(MEMBER_ID),

                       CONSTRAINT fk_board_category
                           FOREIGN KEY (BOARD_CATEGORY_ID)
                               REFERENCES BOARD_CATEGORY(BOARD_CATEGORY_ID)
);

SELECT * FROM BOARD;

-- 게시글 첨부파일 테이블
CREATE TABLE BOARD_ATTACHMENT (
                                  BOARD_ATTACHMENT_ID NUMBER(19) PRIMARY KEY,
                                  BOARD_ID            NUMBER(19) NOT NULL,
                                  FILE_NAME           VARCHAR2(100) NOT NULL,
                                  FILE_PATH           VARCHAR2(255) NOT NULL,
                                  FILE_TYPE           VARCHAR2(50),
                                  FILE_SIZE           NUMBER(10, 0),
                                  UPLOADED_AT         DATE DEFAULT SYSDATE,

                                  CONSTRAINT fk_board_attachment
                                      FOREIGN KEY (BOARD_ID)
                                          REFERENCES BOARD(BOARD_ID)
                                          ON DELETE CASCADE
);

--게시판 댓글 테이블
CREATE TABLE BOARD_COMMENT (
                               BOARD_COMMENT_ID   NUMBER(19) PRIMARY KEY,
                               BOARD_ID           NUMBER(19) NOT NULL,
                               MEMBER_ID          NUMBER(19) NOT NULL,
                               PARENT_ID          NUMBER(19), -- NULL이면 원댓글
                               COMMENTS           VARCHAR2(255) NOT NULL,
                               CREATED_AT         DATE DEFAULT SYSDATE,
                               STATUS             VARCHAR2(20) DEFAULT 'ACTIVE',
                               RECOMMEND          NUMBER(19) DEFAULT 0,
                               NOT_RECOMMEND      NUMBER(19) DEFAULT 0,

                               CONSTRAINT fk_comment_board
                                   FOREIGN KEY (BOARD_ID)
                                       REFERENCES BOARD(BOARD_ID)
                                           ON DELETE CASCADE,

                               CONSTRAINT fk_comment_member
                                   FOREIGN KEY (MEMBER_ID)
                                       REFERENCES MEMBER(MEMBER_ID),

                               CONSTRAINT fk_comment_parent
                                   FOREIGN KEY (PARENT_ID)
                                       REFERENCES BOARD_COMMENT(BOARD_COMMENT_ID)
);


CREATE TABLE BOARD_VOTE_LOG (
                                BOARD_VOTE_LOG_ID NUMBER(19) PRIMARY KEY,
                                BOARD_ID          NUMBER(19) NOT NULL,
                                MEMBER_ID         NUMBER(19) NOT NULL,
                                VOTE_TYPE         VARCHAR2(15) NOT NULL,   -- 'RECOMMEND' or 'NOT_RECOMMEND'
                                VOTE_AT           DATE DEFAULT SYSDATE,

                                CONSTRAINT fk_vote_board
                                    FOREIGN KEY (BOARD_ID)
                                        REFERENCES BOARD(BOARD_ID)
                                            ON DELETE CASCADE,

                                CONSTRAINT fk_vote_member
                                    FOREIGN KEY (MEMBER_ID)
                                        REFERENCES MEMBER(MEMBER_ID),

                                CONSTRAINT UQ_VOTE_MEMBER_BOARD
                                    UNIQUE (BOARD_ID,MEMBER_ID)
);

CREATE TABLE COMMENT_VOTE_LOG(
    COMMENT_VOTE_LOG_ID NUMBER(19) PRIMARY KEY,
    BOARD_COMMENT_ID NUMBER(19) NOT NULL,
    MEMBER_ID NUMBER(19) NOT NULL,
    VOTE_TYPE VARCHAR2(15) NOT NULL,
    VOTE_AT DATE DEFAULT SYSDATE,

    CONSTRAINT fk_commentvote_boardcomment
        FOREIGN KEY (BOARD_COMMENT_ID)
            REFERENCES BOARD_COMMENT(BOARD_COMMENT_ID)
            ON DELETE CASCADE,

    CONSTRAINT fk_commentvote_member
        FOREIGN KEY (MEMBER_ID)
            references MEMBER(MEMBER_ID),

    CONSTRAINT UQ_COMMENTVOTE_MEMBER
                             UNIQUE (MEMBER_ID,BOARD_COMMENT_ID)


);

-- ================================
-- 2. 시퀀스 생성
-- ================================


CREATE SEQUENCE BOARD_ID_SEQ;
CREATE SEQUENCE BOARD_ATTACHMENT_ID_SEQ;
CREATE SEQUENCE BOARD_COMMENT_ID_SEQ;
CREATE SEQUENCE BOARD_VOTE_LOG_ID_SEQ;
CREATE SEQUENCE COMMENT_VOTE_LOG_ID_SEQ;

-- ================================
-- 3. 테이블 삭제 (역순 삭제 권장)
-- ================================
--댓글 추천 비추천 로그 삭제
DROP TABLE COMMENT_VOTE_LOG PURGE;
DROP SEQUENCE COMMENT_VOTE_LOG_ID_SEQ;


-- BOARD_VOTE_LOG 삭제
DROP TABLE BOARD_VOTE_LOG PURGE;
DROP SEQUENCE BOARD_VOTE_LOG_ID_SEQ;

-- 댓글 테이블 삭제
DROP TABLE BOARD_COMMENT PURGE;
DROP SEQUENCE BOARD_COMMENT_ID_SEQ;

-- 첨부파일 테이블 삭제
DROP TABLE BOARD_ATTACHMENT PURGE;
DROP SEQUENCE BOARD_ATTACHMENT_ID_SEQ;

-- 게시글 테이블 삭제
DROP TABLE BOARD PURGE;
DROP SEQUENCE BOARD_ID_SEQ;

-- 게시판 카테고리 테이블 삭제
DROP TABLE BOARD_CATEGORY PURGE;
DROP SEQUENCE BOARD_CATEGORY_ID_SEQ;
