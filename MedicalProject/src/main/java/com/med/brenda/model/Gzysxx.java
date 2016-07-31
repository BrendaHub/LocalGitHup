package com.med.brenda.model;

public class Gzysxx {
    private Long ID;

    private Long HZID;

    private String HZNAME;

    private Long KSID;

    private String KSNAME;

    private Long YSID;

    private String YSNAME;

    private Long SFJHID;

    private String SFJHNAME;

    private Long ZCDATE;

    private String TEMP1;

    private String TEMP2;

    private String TEMP3;

    private String TEMP4;

    private String TEMP5;

    public Gzysxx(Long ID, Long HZID, String HZNAME, Long KSID, String KSNAME, Long YSID, String YSNAME, Long SFJHID, String SFJHNAME, Long ZCDATE, String TEMP1, String TEMP2, String TEMP3, String TEMP4, String TEMP5) {
        this.ID = ID;
        this.HZID = HZID;
        this.HZNAME = HZNAME;
        this.KSID = KSID;
        this.KSNAME = KSNAME;
        this.YSID = YSID;
        this.YSNAME = YSNAME;
        this.SFJHID = SFJHID;
        this.SFJHNAME = SFJHNAME;
        this.ZCDATE = ZCDATE;
        this.TEMP1 = TEMP1;
        this.TEMP2 = TEMP2;
        this.TEMP3 = TEMP3;
        this.TEMP4 = TEMP4;
        this.TEMP5 = TEMP5;
    }

    public Gzysxx() {
        super();
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getHZID() {
        return HZID;
    }

    public void setHZID(Long HZID) {
        this.HZID = HZID;
    }

    public String getHZNAME() {
        return HZNAME;
    }

    public void setHZNAME(String HZNAME) {
        this.HZNAME = HZNAME == null ? null : HZNAME.trim();
    }

    public Long getKSID() {
        return KSID;
    }

    public void setKSID(Long KSID) {
        this.KSID = KSID;
    }

    public String getKSNAME() {
        return KSNAME;
    }

    public void setKSNAME(String KSNAME) {
        this.KSNAME = KSNAME == null ? null : KSNAME.trim();
    }

    public Long getYSID() {
        return YSID;
    }

    public void setYSID(Long YSID) {
        this.YSID = YSID;
    }

    public String getYSNAME() {
        return YSNAME;
    }

    public void setYSNAME(String YSNAME) {
        this.YSNAME = YSNAME == null ? null : YSNAME.trim();
    }

    public Long getSFJHID() {
        return SFJHID;
    }

    public void setSFJHID(Long SFJHID) {
        this.SFJHID = SFJHID;
    }

    public String getSFJHNAME() {
        return SFJHNAME;
    }

    public void setSFJHNAME(String SFJHNAME) {
        this.SFJHNAME = SFJHNAME == null ? null : SFJHNAME.trim();
    }

    public Long getZCDATE() {
        return ZCDATE;
    }

    public void setZCDATE(Long ZCDATE) {
        this.ZCDATE = ZCDATE;
    }

    public String getTEMP1() {
        return TEMP1;
    }

    public void setTEMP1(String TEMP1) {
        this.TEMP1 = TEMP1 == null ? null : TEMP1.trim();
    }

    public String getTEMP2() {
        return TEMP2;
    }

    public void setTEMP2(String TEMP2) {
        this.TEMP2 = TEMP2 == null ? null : TEMP2.trim();
    }

    public String getTEMP3() {
        return TEMP3;
    }

    public void setTEMP3(String TEMP3) {
        this.TEMP3 = TEMP3 == null ? null : TEMP3.trim();
    }

    public String getTEMP4() {
        return TEMP4;
    }

    public void setTEMP4(String TEMP4) {
        this.TEMP4 = TEMP4 == null ? null : TEMP4.trim();
    }

    public String getTEMP5() {
        return TEMP5;
    }

    public void setTEMP5(String TEMP5) {
        this.TEMP5 = TEMP5 == null ? null : TEMP5.trim();
    }
}