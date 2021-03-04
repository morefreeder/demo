package com.springdatajpa.testjpa.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "dept")
@Data
@ApiModel(value = "dept对象",description = "部门信息")
public class Dept implements Serializable {

    @Id
    @Column(name = "deptno")
    @ApiModelProperty(value = "部门编号")
    private Integer deptno;

    @Column(name = "dname")
    @ApiModelProperty(value = "部门名称")
    private String dname;

    @Column(name = "loc")
    @ApiModelProperty(value = "部门地址")
    private String loc;

}
