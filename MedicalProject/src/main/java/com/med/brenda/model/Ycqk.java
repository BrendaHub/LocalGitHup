package com.med.brenda.model;

public class Ycqk {
    private Long id;

    private Long ksid;

    private String ksname;

    private Long ysid;

    private String ysname;

    private Long hzid;

    private String hzname;

    private String title;

    private String content;

    private Long tztime;

    private String temp1;

    private String temp2;

    private String temp3;

    private String temp4;

    private String temp5;

    public Ycqk(Long id, Long ksid, String ksname, Long ysid, String ysname, Long hzid, String hzname, String title, String content, Long tztime, String temp1, String temp2, String temp3, String temp4, String temp5) {
        this.id = id;
        this.ksid = ksid;
        this.ksname = ksname;
        this.ysid = ysid;
        this.ysname = ysname;
        this.hzid = hzid;
        this.hzname = hzname;
        this.title = title;
        this.content = content;
        this.tztime = tztime;
        this.temp1 = temp1;
        this.temp2 = temp2;
        this.temp3 = temp3;
        this.temp4 = temp4;
        this.temp5 = temp5;
    }

    public Ycqk() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKsid() {
        return ksid;
    }

    public void setKsid(Long ksid) {
        this.ksid = ksid;
    }

    public String getKsname() {
        return ksname;
    }

    public void setKsname(String ksname) {
        this.ksname = ksname == null ? null : ksname.trim();
    }

    public Long getYsid() {
        return ysid;
    }

    public void setYsid(Long ysid) {
        this.ysid = ysid;
    }

    public String getYsname() {
        return ysname;
    }

    public void setYsname(String ysname) {
        this.ysname = ysname == null ? null : ysname.trim();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getTztime() {
        return tztime;
    }

    public void setTztime(Long tztime) {
        this.tztime = tztime;
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