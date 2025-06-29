-- MEMBER 테이블 생성
CREATE TABLE member (
                        member_id       NUMBER(19) PRIMARY KEY,
                        login_id        VARCHAR2(50 BYTE) NOT NULL UNIQUE,
                        password        VARCHAR2(20) NOT NULL,
                        name            VARCHAR2(30) NOT NULL,         -- 이름 컬럼 추가
                        gender          VARCHAR2(1) NOT NULL,
                        nickname        VARCHAR2(50 BYTE) NOT NULL UNIQUE,
                        profile_image   VARCHAR2(255),
                        email           VARCHAR2(50) NOT NULL,
                        birthday        DATE NOT NULL,
                        created_at      DATE DEFAULT SYSDATE NOT NULL,
                        status          VARCHAR2(20) DEFAULT 'ACTIVE' NOT NULL,

                        CONSTRAINT chk_gender CHECK (gender IN ('M', 'F')),
                        CONSTRAINT chk_status CHECK (status IN ('ACTIVE', 'WITHDRAWN', 'BANNED'))
);


--MEMBER_ID 시퀀스 생성
CREATE SEQUENCE member_id_seq;

-- MEMBER 테이블 및 시퀀스 삭제
drop table member purge;
DROP SEQUENCE MEMBER_ID_SEQ;

ALTER TABLE MEMBER MODIFY LOGIN_ID VARCHAR2(50 BYTE);
ALTER TABLE MEMBER MODIFY nickname VARCHAR2(50 BYTE);

