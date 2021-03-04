package com.springdatajpa.testjpa.dao;

import com.springdatajpa.testjpa.model.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SalgradeDao extends JpaRepository<Emp,Integer>, QuerydslPredicateExecutor<Emp> {
}
