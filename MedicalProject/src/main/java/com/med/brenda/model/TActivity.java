package com.med.brenda.model;

public class TActivity {
    private Long id;

    private String name;

    private String phone;

    private Integer age;

    private Long createtime;

    private Long modifytime;

    private String source;

    private Integer status;

    public TActivity(Long id, String name, String phone, Integer age, Long createtime, Long modifytime, String source, Integer status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.createtime = createtime;
        this.modifytime = modifytime;
        this.source = source;
        this.status = status;
    }

    public TActivity() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}