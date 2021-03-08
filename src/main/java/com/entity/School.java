package com.entity;

import java.io.Serializable;

/**
 * Created by Lenovo on 2020/5/17.
 */
public class School implements Serializable {


    private Integer index;

    private String name;

    private String code;

    private String competentDepartment;

    private String location;

    private String level;

    private String remarks;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompetentDepartment() {
        return competentDepartment;
    }

    public void setCompetentDepartment(String competentDepartment) {
        this.competentDepartment = competentDepartment;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", competentDepartment='" + competentDepartment + '\'' +
                ", location='" + location + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
