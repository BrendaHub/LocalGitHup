package com.med.brenda.model;

public class ＳmsＶerifＣode {
    private Long id;

    private String randcode;

    private String paircode;

    private String tmp1;

    public ＳmsＶerifＣode(Long id, String randcode, String paircode, String tmp1) {
        this.id = id;
        this.randcode = randcode;
        this.paircode = paircode;
        this.tmp1 = tmp1;
    }

    public ＳmsＶerifＣode() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRandcode() {
        return randcode;
    }

    public void setRandcode(String randcode) {
        this.randcode = randcode == null ? null : randcode.trim();
    }

    public String getPaircode() {
        return paircode;
    }

    public void setPaircode(String paircode) {
        this.paircode = paircode == null ? null : paircode.trim();
    }

    public String getTmp1() {
        return tmp1;
    }

    public void setTmp1(String tmp1) {
        this.tmp1 = tmp1 == null ? null : tmp1.trim();
    }
}