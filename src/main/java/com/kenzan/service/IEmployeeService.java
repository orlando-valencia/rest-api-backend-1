package com.kenzan.service;

import java.util.List;
import java.util.Optional;

import com.kenzan.model.Employee;

public interface IEmployeeService{

	// - Get employees by an ID
	public Optional<Employee> findById(Integer id);

	// - Create new employees
	public Employee createEmployee(Employee employee);

	// - Update existing employees
	public Employee updateEmployee(Employee employee);

	// - Delete employees - Deactivate an Employee
	public Boolean deleteEmployee(Integer id);

	// - Get all employees
	public List<Employee> findAll();

}