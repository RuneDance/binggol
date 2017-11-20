package com.binggol.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "EMP")
public class QueryEmp implements Serializable {
    @Id
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Integer sal;

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        if (ename != null) {
            this.ename = ename.toString().trim();
        } else {
            this.ename = ename;
        }
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        if (job != null) {
            this.job = job.toString().trim();
        } else {
            this.job = job;
        }
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Integer getSal() {
        return sal;
    }

    public void setSal(Integer sal) {
        this.sal = sal;
    }
}
