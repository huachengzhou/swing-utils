package com.general.recruitment;

import java.io.Serializable;

/**
 * Created by Lenovo on 2020/5/17.
 */
public class JobBean implements Serializable {
    private String jobName;
    private String company;
    private String address;
    private String salary;
    private String date;

    public void set(String jobName, String company, String address, String salary, String date) {
        this.jobName = jobName;
        this.company = company;
        this.address = address;
        this.salary = salary;
        this.date = date;
    }
    public String getJobName() {
        return jobName;
    }
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getSalary() {
        return salary;
    }
    public void setSalary(String salary) {
        this.salary = salary;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String toString() {
        return "jobName=" + jobName + ", company=" + company + ", address=" + address + ", salary=" + salary
                + ", date=" + date;
    }


}
