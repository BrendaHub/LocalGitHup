package com.med.brenda.model;

public class Eventlb {
    private Long ID;

    private Long YSID;

    private String YSNAME;

    private Long HZID;

    private String HZNAME;

    private String BH;

    private String BHXZ;

    private String TEMP1;

    private String TEMP2;

    private String TEMP3;

    private String TEMP4;

    private String TEMP5;

    public Eventlb(Long ID, Long YSID, String YSNAME, Long HZID, String HZNAME, String BH, String BHXZ, String TEMP1, String TEMP2, String TEMP3, String TEMP4, String TEMP5) {
        this.ID = ID;
        this.YSID = YSID;
        this.YSNAME = YSNAME;
        this.HZID = HZID;
        this.HZNAME = HZNAME;
        this.BH = BH;
        this.BHXZ = BHXZ;
        this.TEMP1 = TEMP1;
        this.TEMP2 = TEMP2;
        this.TEMP3 = TEMP3;
        this.TEMP4 = TEMP4;
        this.TEMP5 = TEMP5;
    }

    public Eventlb() {
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

    public String getYSNAME() {
        return YSNAME;
    }

    public void setYSNAME(String YSNAME) {
        this.YSNAME = YSNAME == null ? null : YSNAME.trim();
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

    public String getBH() {
        return BH;
    }

    public void setBH(String BH) {
        this.BH = BH == null ? null : BH.trim();
    }

    public String getBHXZ() {
        return BHXZ;
    }

    public void setBHXZ(String BHXZ) {
        this.BHXZ = BHXZ == null ? null : BHXZ.trim();
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