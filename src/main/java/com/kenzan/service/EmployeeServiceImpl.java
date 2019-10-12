package com.kenzan.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenzan.model.Employee;
import com.kenzan.repository.EmployeeRepository;

@Service("employeeServiceImpl")
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Optional<Employee> findById(Long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Optional<Employee> updatedEmployee = employeeRepository.findById(employee.getId());
		
		if(employeeRepository.findById(employee.getId()) != null) {
			updatedEmployee.get().setFirstName(employee.getFirstName());
			updatedEmployee.get().setMiddleInitial(employee.getMiddleInitial());
			updatedEmployee.get().setLastName(employee.getLastName());
			updatedEmployee.get().setDateOfBirth(employee.getDateOfBirth());
			updatedEmployee.get().setDateOfEmployment(employee.getDateOfEmployment());
			updatedEmployee.get().setStatus(employee.getStatus());
		}
		
		employeeRepository.save(updatedEmployee.get());
		
		return updatedEmployee.get();
	}

	@Override
	public Employee deactivateEmployee(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employeeRepository.findById(id) != null) {
			employee.get().setStatus(0);
		}
		employeeRepository.save(employee.get());
		return employee.get();
	}
	
	@Override
	public Employee activateEmployee(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employeeRepository.findById(id) != null) {
			employee.get().setStatus(1);
		}
		employeeRepository.save(employee.get());
		return employee.get();
	}

	@Override
	public List<Employee> findAll() {
		List<Employee> employees = employeeRepository.findAll();
		
		return employees.stream().
			    filter(employee -> employee.getStatus() == 1).
			    collect(Collectors.toList());
	}

}
