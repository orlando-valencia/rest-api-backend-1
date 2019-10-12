package com.kenzan.service;

import java.util.List;
import java.util.Optional;

import com.kenzan.model.Employee;

public interface IEmployeeService{

	// - Get employees by an ID
	public Optional<Employee> findById(Long id);

	// - Create new employees
	public Employee saveEmployee(Employee employee);

	// - Update existing employees
	public Employee updateEmployee(Employee employee);

	// - Deactivate an Employee
	public Employee deactivateEmployee(Long id);

	// - Activate an Employee
	public Employee activateEmployee(Long id);
	
	// - Get all employees
	public List<Employee> findAll();

}