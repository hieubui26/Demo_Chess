package com.mad.g1.bui_minh_hieu.demo_chess.model;

public class Match {
    private Integer id;
    private String name;
    private String date;
    private String level;
    private String description;
    private Boolean status;
//    private Member member;

    public Match() {

    }

    public Match(Integer id, String name, String date, String level, String description, Boolean status) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.level = level;
        this.description = description;
        this.status = status;
    }

    public Match(String name, String date, String level, String description, boolean status) {
        this.name = name;
        this.date = date;
        this.level = level;
        this.description = description;
        this.status = status;
//        this.member = member;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

//    public Member getMember() {
//        return member;
//    }
//
//    public void setMember(Member member) {
//        this.member = member;
//    }
}
