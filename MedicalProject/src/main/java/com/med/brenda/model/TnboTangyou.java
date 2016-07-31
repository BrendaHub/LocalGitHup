package com.med.brenda.model;

public class TnboTangyou {
    private Long id;

    private String name;

    private String sex;

    private String csrq;

    private String sheng;

    private String shi;

    private String xian;

    private String sfzcode;

    private String jbzd;

    private String jzxm;

    private String lxdh;

    private String txdz;

    private String jzyy;

    private String qzsj;

    private String datarq;

    private String temp1;

    private String temp2;

    private String temp3;

    private String temp4;

    private String temp5;

    public TnboTangyou(Long id, String name, String sex, String csrq, String sheng, String shi, String xian, String sfzcode, String jbzd, String jzxm, String lxdh, String txdz, String jzyy, String qzsj, String datarq, String temp1, String temp2, String temp3, String temp4, String temp5) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.csrq = csrq;
        this.sheng = sheng;
        this.shi = shi;
        this.xian = xian;
        this.sfzcode = sfzcode;
        this.jbzd = jbzd;
        this.jzxm = jzxm;
        this.lxdh = lxdh;
        this.txdz = txdz;
        this.jzyy = jzyy;
        this.qzsj = qzsj;
        this.datarq = datarq;
        this.temp1 = temp1;
        this.temp2 = temp2;
        this.temp3 = temp3;
        this.temp4 = temp4;
        this.temp5 = temp5;
    }

    public TnboTangyou() {
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

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq == null ? null : csrq.trim();
    }

    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng == null ? null : sheng.trim();
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi == null ? null : shi.trim();
    }

    public String getXian() {
        return xian;
    }

    public void setXian(String xian) {
        this.xian = xian == null ? null : xian.trim();
    }

    public String getSfzcode() {
        return sfzcode;
    }

    public void setSfzcode(String sfzcode) {
        this.sfzcode = sfzcode == null ? null : sfzcode.trim();
    }

    public String getJbzd() {
        return jbzd;
    }

    public void setJbzd(String jbzd) {
        this.jbzd = jbzd == null ? null : jbzd.trim();
    }

    public String getJzxm() {
        return jzxm;
    }

    public void setJzxm(String jzxm) {
        this.jzxm = jzxm == null ? null : jzxm.trim();
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh == null ? null : lxdh.trim();
    }

    public String getTxdz() {
        return txdz;
    }

    public void setTxdz(String txdz) {
        this.txdz = txdz == null ? null : txdz.trim();
    }

    public String getJzyy() {
        return jzyy;
    }

    public void setJzyy(String jzyy) {
        this.jzyy = jzyy == null ? null : jzyy.trim();
    }

    public String getQzsj() {
        return qzsj;
    }

    public void setQzsj(String qzsj) {
        this.qzsj = qzsj == null ? null : qzsj.trim();
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