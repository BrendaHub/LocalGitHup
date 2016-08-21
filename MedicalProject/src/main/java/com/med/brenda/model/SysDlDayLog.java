package com.med.brenda.model;

public class SysDlDayLog {
    private Long id;

    private String sfzh;

    private String hzid;

    private String hzname;

    private String sex;

    private String mz;

    private String writedate;

    private String itemvalue;

    public SysDlDayLog(Long id, String sfzh, String hzid, String hzname, String sex, String mz, String writedate, String itemvalue) {
        this.id = id;
        this.sfzh = sfzh;
        this.hzid = hzid;
        this.hzname = hzname;
        this.sex = sex;
        this.mz = mz;
        this.writedate = writedate;
        this.itemvalue = itemvalue;
    }

    public SysDlDayLog() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh == null ? null : sfzh.trim();
    }

    public String getHzid() {
        return hzid;
    }

    public void setHzid(String hzid) {
        this.hzid = hzid == null ? null : hzid.trim();
    }

    public String getHzname() {
        return hzname;
    }

    public void setHzname(String hzname) {
        this.hzname = hzname == null ? null : hzname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz == null ? null : mz.trim();
    }

    public String getWritedate() {
        return writedate;
    }

    public void setWritedate(String writedate) {
        this.writedate = writedate == null ? null : writedate.trim();
    }

    public String getItemvalue() {
        return itemvalue;
    }

    public void setItemvalue(String itemvalue) {
        this.itemvalue = itemvalue == null ? null : itemvalue.trim();
    }
}