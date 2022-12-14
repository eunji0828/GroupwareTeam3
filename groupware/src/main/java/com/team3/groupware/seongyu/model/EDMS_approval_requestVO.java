package com.team3.groupware.seongyu.model;

public class EDMS_approval_requestVO {
    private String approval_request_docu_title; // 제목
    private String approval_request_docu_contents; // 내용
    private int EDMS_docu_num; // 문서번호


    public String getApproval_request_docu_title() {
        return approval_request_docu_title;
    }

    public void setApproval_request_docu_title(String approval_request_docu_title) {
        this.approval_request_docu_title = approval_request_docu_title;
    }

    public String getApproval_request_docu_contents() {
        return approval_request_docu_contents;
    }

    public void setApproval_request_docu_contents(String approval_request_docu_contents) {
        this.approval_request_docu_contents = approval_request_docu_contents;
    }

    public int getEDMS_docu_num() {
        return EDMS_docu_num;
    }

    public void setEDMS_docu_num(int EDMS_docu_num) {
        this.EDMS_docu_num = EDMS_docu_num;
    }

    @Override
    public String toString() {
        return "EDMS_approval_requestVO{" +
                "approval_request_docu_title='" + approval_request_docu_title + '\'' +
                ", approval_request_docu_contents='" + approval_request_docu_contents + '\'' +
                ", EDMS_docu_num=" + EDMS_docu_num +
                '}';
    }
}
