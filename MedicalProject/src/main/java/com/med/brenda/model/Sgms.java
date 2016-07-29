package com.med.brenda.model;

public class Sgms {
    private Long ID;

    private Long HZID;

    private String HZNAME;

    private String GMWZMC;

    private String FY;

    private String CD;

    private String BZHMS;

    private String TEMP1;

    private String TEMP2;

    private String TEMP3;

    private String TEMP4;

    private String TEMP5;

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

    public String getGMWZMC() {
        return GMWZMC;
    }

    public void setGMWZMC(String GMWZMC) {
        this.GMWZMC = GMWZMC == null ? null : GMWZMC.trim();
    }

    public String getFY() {
        return FY;
    }

    public void setFY(String FY) {
        this.FY = FY == null ? null : FY.trim();
    }

    public String getCD() {
        return CD;
    }

    public void setCD(String CD) {
        this.CD = CD == null ? null : CD.trim();
    }

    public String getBZHMS() {
        return BZHMS;
    }

    public void setBZHMS(String BZHMS) {
        this.BZHMS = BZHMS == null ? null : BZHMS.trim();
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