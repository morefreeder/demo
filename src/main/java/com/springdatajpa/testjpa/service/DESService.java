package com.springdatajpa.testjpa.service;

import com.springdatajpa.testjpa.model.Emp;

import java.util.List;
import java.util.Map;


public interface DESService {

    Emp findEmpById(Integer empno);

    boolean saveEmp(Emp emp);

    Integer updateEmpByEmpno(Double sal,Integer empno);

    boolean deleteEmpByEmpno(Integer empno);

    List<Map> findBySal(Double sal);

    List<Emp> findAll();

    List<Map> findDept();

}
