package com.med.brenda.model;

public class TYinshi {
    private Long id;

    private String title;

    private String desc;

    private Long createtime;

    private Long modifytime;

    private Integer status;

    private Long author_id;

    private String author;

    private String content;

    public TYinshi(Long id, String title, String desc, Long createtime, Long modifytime, Integer status, Long author_id, String author, String content) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.createtime = createtime;
        this.modifytime = modifytime;
        this.status = status;
        this.author_id = author_id;
        this.author = author;
        this.content = content;
    }

    public TYinshi() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}