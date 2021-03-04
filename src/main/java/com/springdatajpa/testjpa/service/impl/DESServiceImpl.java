package com.springdatajpa.testjpa.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springdatajpa.testjpa.dao.DeptDao;
import com.springdatajpa.testjpa.dao.EmpDao;
import com.springdatajpa.testjpa.dao.SalgradeDao;
import com.springdatajpa.testjpa.model.Dept;
import com.springdatajpa.testjpa.model.Emp;
import com.springdatajpa.testjpa.model.QDept;
import com.springdatajpa.testjpa.model.QEmp;
import com.springdatajpa.testjpa.service.DESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DESServiceImpl implements DESService {

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private EmpDao empDao;

    @Autowired
    private SalgradeDao salgradeDao;

    @PersistenceContext
    private EntityManager entityManager;

    private JPAQueryFactory jpaQueryFactory;

    @PostConstruct
    public void init(){
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }


    @Override
    public Emp findEmpById(Integer empno) {

        return empDao.findById(empno).get();
    }

    @Override
    public boolean saveEmp(Emp emp) {

        empDao.save(emp);
        return empDao.existsById(emp.getEmpno());

    }

    @Override
    public Integer updateEmpByEmpno(Double sal,Integer empno) {

        return empDao.updateEmp(sal, empno);

    }

    @Override
    public boolean deleteEmpByEmpno(Integer empno) {

        empDao.deleteById(empno);
        return empDao.existsById(empno);


    }

    @Override
    public List<Map> findBySal(Double sal) {

        QEmp qEmp = QEmp.emp;
        QDept qDept = QDept.dept;
        //查询条件
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qEmp.sal.gt(sal));
        booleanBuilder.and(qDept.deptno.eq(qEmp.deptno));
        QueryResults<Tuple> queryResults = jpaQueryFactory.select(qEmp.emp,qDept.dept)
                .from(qEmp, qDept)
                .where(booleanBuilder)
                .fetchResults();
        //流转换为集合
        List<Map> collect = queryResults.getResults().stream().map(tuple -> {
            Emp emp = tuple.get(qEmp);
            Dept dept = tuple.get(qDept);
            Map map = new HashMap();
            map.put("emp", emp);
            map.put("dname", dept.getDname());
            return map;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<Emp> findAll() {

        //分页排序
        int page = 0;
        int size = 5;
        Sort sort = Sort.by("empno");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Emp> p = empDao.findAll(pageable);
        List<Emp> list = p.getContent();
        return list;

    }

    @Override
    public List<Map> findDept() {

        QEmp qEmp = QEmp.emp;
        QDept qDept = QDept.dept;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qEmp.deptno.eq(qDept.deptno));
        QueryResults<Tuple> queryResults = jpaQueryFactory
                .select(qDept, qEmp.deptno.count())
                .from(qEmp)
                .rightJoin(qDept)
                .on(booleanBuilder)
                .groupBy(qDept.deptno)
                .fetchResults();
        List<Map> collect = queryResults.getResults().stream().map(tuple -> {
            Long count = tuple.get(qEmp.deptno.count());
            Dept dept = tuple.get(qDept);
            Map map = new HashMap();
            map.put("dept", dept);
            map.put("count", count);
            return map;
        }).collect(Collectors.toList());

        return collect;
    }


}
