package com.med.brenda.model;

public class StnbSd {
    private Long id;

    private Long hzid;

    private Long ysid;

    private String datarq;

    private String temp1;

    private String temp2;

    private String temp3;

    private String temp4;

    private String temp5;

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

    public Long getYsid() {
        return ysid;
    }

    public void setYsid(Long ysid) {
        this.ysid = ysid;
    }

    public String getDatarq() {
        return datarq;
    }

    public void setDatarq(String datarq) {
        this.datarq = datarq == null ? null : datarq.trim();
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