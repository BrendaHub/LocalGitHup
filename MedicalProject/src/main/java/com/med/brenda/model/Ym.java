package com.med.brenda.model;

public class Ym {
    private Long id;

    private Long hzid;

    private String hzname;

    private String nlbh;

    private String nl;

    private Long savedate;

    private String temp1;

    private String temp2;

    private String temp3;

    private String temp4;

    private String temp5;

    public Ym(Long id, Long hzid, String hzname, String nlbh, String nl, Long savedate, String temp1, String temp2, String temp3, String temp4, String temp5) {
        this.id = id;
        this.hzid = hzid;
        this.hzname = hzname;
        this.nlbh = nlbh;
        this.nl = nl;
        this.savedate = savedate;
        this.temp1 = temp1;
        this.temp2 = temp2;
        this.temp3 = temp3;
        this.temp4 = temp4;
        this.temp5 = temp5;
    }

    public Ym() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHzid() {
        return hzid;
    }

    public void setHzid(Long hzid) {
        this.hzid = hzid;
    }

    public String getHzname() {
        return hzname;
    }

    public void setHzname(String hzname) {
        this.hzname = hzname == null ? null : hzname.trim();
    }

    public String getNlbh() {
        return nlbh;
    }

    public void setNlbh(String nlbh) {
        this.nlbh = nlbh == null ? null : nlbh.trim();
    }

    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl == null ? null : nl.trim();
    }

    public Long getSavedate() {
        return savedate;
    }

    public void setSavedate(Long savedate) {
        this.savedate = savedate;
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