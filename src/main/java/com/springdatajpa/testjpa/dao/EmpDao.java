package com.springdatajpa.testjpa.dao;

import com.springdatajpa.testjpa.model.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Map;

public interface EmpDao extends JpaRepository<Emp,Integer>, QuerydslPredicateExecutor<Emp> {

    @Query(value = "update emp set sal=? where empno=?",nativeQuery = true)
    @Modifying
    Integer updateEmp(Double sal,Integer empno);
    @Query(value = "select ename,max(sal) as maxsal,deptno from emp group by deptno",nativeQuery = true)
    Map<String,Object> findHiSalByDeptNo();
}
