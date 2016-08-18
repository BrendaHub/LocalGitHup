package com.med.brenda.model;

public class AppDlLog {
    private Long id;

    private String mobile;

    private String sfzcode;

    private Long createtime;

    public AppDlLog(Long id, String mobile, String sfzcode, Long createtime) {
        this.id = id;
        this.mobile = mobile;
        this.sfzcode = sfzcode;
        this.createtime = createtime;
    }

    public AppDlLog() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getSfzcode() {
        return sfzcode;
    }

    public void setSfzcode(String sfzcode) {
        this.sfzcode = sfzcode == null ? null : sfzcode.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }
}