package com.med.brenda.model;

public class Roles {
    private Long id;

    private String rolename;

    private String roledesc;

    private Long createtime;

    private Long modifytime;

    private Long adduid;

    private Long moduid;

    public Roles(Long id, String rolename, String roledesc, Long createtime, Long modifytime, Long adduid, Long moduid) {
        this.id = id;
        this.rolename = rolename;
        this.roledesc = roledesc;
        this.createtime = createtime;
        this.modifytime = modifytime;
        this.adduid = adduid;
        this.moduid = moduid;
    }

    public Roles() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc == null ? null : roledesc.trim();
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

    public Long getAdduid() {
        return adduid;
    }

    public void setAdduid(Long adduid) {
        this.adduid = adduid;
    }

    public Long getModuid() {
        return moduid;
    }

    public void setModuid(Long moduid) {
        this.moduid = moduid;
    }
}