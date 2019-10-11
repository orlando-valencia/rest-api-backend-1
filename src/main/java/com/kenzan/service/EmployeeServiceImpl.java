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
		return employeeRepository.findById(id);
	}

	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Optional<Employee> e = employeeRepository.findById(employee.getId());
		
		if(employeeRepository.findById(employee.getId()) != null) {
			e.get().setFirstName(employee.getFirstName());
			e.get().setLastName(employee.getLastName());
			e.get().setDateOfBirth(employee.getDateOfBirth());
			e.get().setDateOfEmployment(employee.getDateOfEmployment());
			e.get().setStatus(employee.getStatus());
		}
		return e.get();
	}

	@Override
	public Boolean deleteEmployee(Integer id) {
		Optional<Employee> e = employeeRepository.findById(id);
		
		if(employeeRepository.findById(id) != null) {
			e.get().setStatus(0);
			return true;
		}
		
		return false;
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

}
