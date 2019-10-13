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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kenzan.model.Employee;
import com.kenzan.service.IEmployeeService;

@RestController
@RequestMapping(path = "/api")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	// - Get employees by an ID
	@GetMapping(path = "/employees/{id}", produces = "application/json")
	public Employee getEmployee(@PathVariable Long id) {

		return 	employeeService.findById(id).stream()
		.filter(e -> e == null || e.getStatus() != 0)
		.findAny()
		.orElse(null);
	}
	
	// - Create new employees
	@PostMapping(path = "/employees", produces = "application/json")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		//By default, all employees are active (1 = Active)
		employee.setStatus(1);
		Employee savedEmployee = employeeService.saveEmployee(employee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedEmployee.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	// - Update existing employees
	@PutMapping(path = "/employees/{id}", produces = "application/json")
	public ResponseEntity<Employee> updateStudent(@RequestBody Employee employee, @PathVariable Long id) {
		Optional<Employee> employeeOptional = employeeService.findById(id);
		if (!employeeOptional.isPresent())
			return ResponseEntity.notFound().build();
		employee.setId(id);
		employeeService.updateEmployee(employee);

		return ResponseEntity.noContent().build();
	}
	
	// - Inactivate an Employee
	@PutMapping(path = "/employees/deactivate/{id}", produces = "application/json")
	public void deactivateStudent(@PathVariable Long id) {
		employeeService.deactivateEmployee(id);
	}
	
	// - Activate an Employee
	@PutMapping(path = "/employees/activate/{id}", produces = "application/json")
	public void activateStudent(@PathVariable Long id) {
		employeeService.activateEmployee(id);
	}
	
	// - Get all employees
	@GetMapping(path = "/employees", produces = "application/json")
	public List<Employee> retrieveAllEmployees(Model model, Employee employee) {
		return employeeService.findAll();
	}
	
	
}
