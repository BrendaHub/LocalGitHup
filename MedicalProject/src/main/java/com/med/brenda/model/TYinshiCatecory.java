package com.med.brenda.model;

public class TYinshiCatecory {
    private Long id;

    private String name;

    private String desc;

    private Long createtime;

    private Long modifytime;

    private Integer status;

    public TYinshiCatecory(Long id, String name, String desc, Long createtime, Long modifytime, Integer status) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.createtime = createtime;
        this.modifytime = modifytime;
        this.status = status;
    }

    public TYinshiCatecory() {
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getModifytime() {
        return modifytime;
    }

    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}