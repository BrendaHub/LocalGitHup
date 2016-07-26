package com.doctor330.model;

public class Ssfjhmb {
    private Long id;

    private String daymonth;

    private String sfcontent;

    private String sc;

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

    public String getDaymonth() {
        return daymonth;
    }

    public void setDaymonth(String daymonth) {
        this.daymonth = daymonth == null ? null : daymonth.trim();
    }

    public String getSfcontent() {
        return sfcontent;
    }

    public void setSfcontent(String sfcontent) {
        this.sfcontent = sfcontent == null ? null : sfcontent.trim();
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc == null ? null : sc.trim();
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