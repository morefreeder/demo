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
@Table(name = "sal")
@Data
@ApiModel(value = "salgrade对象",description = "工资情况")
public class Salgrade implements Serializable {

    @Id
    @Column(name = "grade")
    @ApiModelProperty(value = "工资等级")
    private Integer grade;

    @Column(name = "losal")
    @ApiModelProperty(value = "最低工资")
    private Integer losal;

    @Column(name = "hisal")
    @ApiModelProperty(value = "最高工资")
    private Integer hisal;

}
