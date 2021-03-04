package com.springdatajpa.testjpa.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "emp")
@Data
@ApiModel(value = "emp对象",description = "员工信息")
public class Emp implements Serializable {

    @Id
    @Column(name = "empno")
    @ApiModelProperty(value = "员工编号")
    private Integer empno;

    @Column(name = "ename")
    @ApiModelProperty(value = "员工姓名")
    private String ename;

    @Column(name = "job")
    @ApiModelProperty(value = "员工职位")
    private String job;

    @Column(name = "mgr")
    @ApiModelProperty(value = "上级编号")
    private Integer mgr;

    @Column(name = "hiredate")
    @ApiModelProperty(value = "入职日期")
    private Date hiredate;

    @Column(name = "sal")
    @ApiModelProperty(value = "工资")
    private Double sal;

    @Column(name = "comm")
    @ApiModelProperty(value = "奖金")
    private Double comm;

    @Column(name = "deptno")
    @ApiModelProperty(value = "部门编号")
    private Integer deptno;
}
