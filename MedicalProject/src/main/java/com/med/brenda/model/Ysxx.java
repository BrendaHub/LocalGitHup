package com.med.brenda.model;

public class Ysxx {
    private Long id;

    private String name;

    private String sex;

    private String zc;

    private Long ssksid;

    private String ssks;

    private String szyy;

    private String dlh;

    private String password;

    private String yszyjs;

    private String ysll;

    private String temp1;

    private String temp2;

    private String temp3;

    private String temp4;

    private String temp5;

    public Ysxx(Long id, String name, String sex, String zc, Long ssksid, String ssks, String szyy, String dlh, String password, String yszyjs, String ysll, String temp1, String temp2, String temp3, String temp4, String temp5) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.zc = zc;
        this.ssksid = ssksid;
        this.ssks = ssks;
        this.szyy = szyy;
        this.dlh = dlh;
        this.password = password;
        this.yszyjs = yszyjs;
        this.ysll = ysll;
        this.temp1 = temp1;
        this.temp2 = temp2;
        this.temp3 = temp3;
        this.temp4 = temp4;
        this.temp5 = temp5;
    }

    public Ysxx() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc == null ? null : zc.trim();
    }

    public Long getSsksid() {
        return ssksid;
    }

    public void setSsksid(Long ssksid) {
        this.ssksid = ssksid;
    }

    public String getSsks() {
        return ssks;
    }

    public void setSsks(String ssks) {
        this.ssks = ssks == null ? null : ssks.trim();
    }

    public String getSzyy() {
        return szyy;
    }

    public void setSzyy(String szyy) {
        this.szyy = szyy == null ? null : szyy.trim();
    }

    public String getDlh() {
        return dlh;
    }

    public void setDlh(String dlh) {
        this.dlh = dlh == null ? null : dlh.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getYszyjs() {
        return yszyjs;
    }

    public void setYszyjs(String yszyjs) {
        this.yszyjs = yszyjs == null ? null : yszyjs.trim();
    }

    public String getYsll() {
        return ysll;
    }

    public void setYsll(String ysll) {
        this.ysll = ysll == null ? null : ysll.trim();
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1 == null ? null : temp1.trim();
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2 == null ? null : temp2.trim();
    }

    public String getTemp3() {
        return temp3;
    }

    public void setTemp3(String temp3) {
        this.temp3 = temp3 == null ? null : temp3.trim();
    }

    public String getTemp4() {
        return temp4;
    }

    public void setTemp4(String temp4) {
        this.temp4 = temp4 == null ? null : temp4.trim();
    }

    public String getTemp5() {
        return temp5;
    }

    public void setTemp5(String temp5) {
        this.temp5 = temp5 == null ? null : temp5.trim();
    }
}