package com.luv2code.springboot.curddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.curddemo.entity.Employee;
import com.luv2code.springboot.curddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	//quick and dirty : inject employee dao
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService){
		employeeService =theEmployeeService;
	}
	
	//expose "/employees" and returmn list of employees
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();	
	}
	//add mapping for GET /employee/employeeId
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId){
		Employee theEmployee = employeeService.findById(employeeId);
		
		if(theEmployee == null){
			throw new RuntimeException("EmployeeId not found - " + employeeId);
		}
		return theEmployee;
	}
	//add mapping for POST /employees add new employee
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee){
		
		theEmployee.setId(0);
	 employeeService.save(theEmployee);
		return theEmployee;
	}
	//add mapping for PUT /employees to update excisting employee
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee){
		
		employeeService.save(theEmployee);
	
		return theEmployee;
	}
	
	//add mapping for Delete  /employee/employeeId
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId){
		Employee theEmployee = employeeService.findById(employeeId);
		if(theEmployee == null){
			throw new RuntimeException("EmployeeId not found - " + employeeId);
		}
		employeeService.deleteById(employeeId);
		return "Deleted employee id =" + employeeId;
	}
}
