package com.kenzan.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kenzan.model.Employee;
import com.kenzan.service.IEmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	// - Get employees by an ID
	@GetMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable Long id) {
		Optional<Employee> employee = employeeService.findById(id);
		if(!employee.isPresent()) {
			return null;
		}
		return employee.get();
	}
	
	// - Create new employees
	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		Employee savedEmployee = employeeService.saveEmployee(employee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedEmployee.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	// - Update existing employees
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateStudent(@RequestBody Employee employee, @PathVariable Long id) {
		Optional<Employee> employeeOptional = employeeService.findById(id);
		if (!employeeOptional.isPresent())
			return ResponseEntity.notFound().build();
		employee.setId(id);
		employeeService.updateEmployee(employee);

		return ResponseEntity.noContent().build();
	}
	
	// - Inactivate an Employee
	@PutMapping("/employees/deactivate/{id}")
	public void deactivateStudent(@PathVariable Long id) {
		employeeService.deactivateEmployee(id);
	}
	
	// - Activate an Employee
	@PutMapping("/employees/activate/{id}")
	public void activateStudent(@PathVariable Long id) {
		employeeService.activateEmployee(id);
	}
	
	// - Get all employees
	@GetMapping("/employees")
	public List<Employee> retrieveAllEmployees(Model model, Employee employee) {
		return employeeService.findAll();
	}
	
	
}
