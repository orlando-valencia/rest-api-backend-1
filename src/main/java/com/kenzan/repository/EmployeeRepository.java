package com.kenzan.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kenzan.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
	public List<Employee> findAll();
}
