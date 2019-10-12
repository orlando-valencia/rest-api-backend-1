package com.kenzan.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kenzan.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{
	public List<Employee> findAll();
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO employee (ID, FIRST_NAME, MIDDLE_INITIAL, LAST_NAME, DATE_OF_BIRTH, DATE_OF_EMPLOYMENT, STATUS)\r\n" + 
			"VALUES (?, ?, ?, ?, ?, ?, ?)", nativeQuery=true)
	public Employee saveEmployee(Employee employee);
}
