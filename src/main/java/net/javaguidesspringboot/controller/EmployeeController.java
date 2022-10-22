package net.javaguidesspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.javaguidesspringboot.exception.ResourceNotFoundException;
import net.javaguidesspringboot.model.Employee;
import net.javaguidesspringboot.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	// get all employee
	// http://localhost:8080/api/v1/empl

	@GetMapping("/empl")
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	// create employee
	// http://localhost:8080/api/v1/AddEmployee

	@PostMapping("/AddEmployee")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	// get employee by id rest api
	// http://localhost:8080/api/v1/getemployee/3

	@GetMapping("/getemployee/{Id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long Id) {
		Employee employee = employeeRepository.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee doesnt exist with Id:" + Id));
		return ResponseEntity.ok(employee);
	}

	// delete employee by id rest api
	// http://localhost:8080/api/v1/deleteemployee/3

	@GetMapping("/deleteemployee/{Id}")
	public void deleteEmployeeById(@PathVariable Long Id) {
		employeeRepository.deleteById(Id);

	}

	@PutMapping("/update/{Id}")
	public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long Id, @RequestBody Employee employeeDetails) {
		Employee employee = employeeRepository.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee doesnt exist with Id:" + Id));
		String email = employeeDetails.getEmailId();
		String fname = employeeDetails.getFirstName();
		String lname = employeeDetails.getLastName();

		employee.setEmailId(email);
		employee.setFirstName(fname);
		employee.setLastName(lname);
		Employee emp = employeeRepository.save(employee);
		return ResponseEntity.ok(emp);
	}
}
