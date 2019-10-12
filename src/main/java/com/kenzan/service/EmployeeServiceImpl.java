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
		return employeeRepository.findById(employee.getId()).
				map(em -> {
					em.setFirstName(employee.getFirstName());
					em.setMiddleInitial(employee.getMiddleInitial());
					em.setLastName(employee.getLastName());
					em.setDateOfBirth(employee.getDateOfBirth());
					em.setDateOfEmployment(employee.getDateOfEmployment());
					em.setStatus(employee.getStatus());
					return employeeRepository.save(em);
				}).orElseGet(() -> {
					return employee;
				});
	}

	@Override
	public Employee deactivateEmployee(Long id) {

		return employeeRepository.findById(id).
				map(em -> {
					em.setStatus(0);
					employeeRepository.save(em);
					return em;
				}).orElseThrow(null);
	}

	@Override
	public Employee activateEmployee(Long id) {

		return employeeRepository.findById(id).
				map(em -> {
					em.setStatus(1);
					employeeRepository.save(em);
					return em;
				}).orElseThrow(null);
	}

	@Override
	public List<Employee> findAll() {
		List<Employee> employees = employeeRepository.findAll();

		return employees.stream().
				filter(employee -> employee.getStatus() == 1).
				collect(Collectors.toList());
	}

}
