package com.med.brenda.model;

public class Hzflag {
    private Long ID;

    private Long YSID;

    private Long HZID;

    private String TEMP1;

    private String TEMP2;

    public Hzflag(Long ID, Long YSID, Long HZID, String TEMP1, String TEMP2) {
        this.ID = ID;
        this.YSID = YSID;
        this.HZID = HZID;
        this.TEMP1 = TEMP1;
        this.TEMP2 = TEMP2;
    }

    public Hzflag() {
        super();
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getYSID() {
        return YSID;
    }

    public void setYSID(Long YSID) {
        this.YSID = YSID;
    }

    public Long getHZID() {
        return HZID;
    }

    public void setHZID(Long HZID) {
        this.HZID = HZID;
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
}