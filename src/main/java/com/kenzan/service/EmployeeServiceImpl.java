package com.kenzan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenzan.model.Employee;
import com.kenzan.repository.EmployeeRepository;

@Service("employeeServiceImpl")
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Optional<Employee> findById(Integer id) {
		return null;
	}

	@Override
	public Employee createEmployee(Employee employee) {
		return null;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return null;
	}

	@Override
	public Boolean deleteEmployee(Integer id) {
		return null;
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

}
