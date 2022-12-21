package management.system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import management.system.model.Employee;
import management.system.sevice.EmployeeService;

@Controller
@RequestMapping(value = "employees")
public class EmployeeController {
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	// build create employee REST API
	/*
	 * @PostMapping public ResponseEntity<Employee> saveEmployee(@RequestBody
	 * Employee employee) { return new
	 * ResponseEntity<Employee>(employeeService.saveEmployee(employee),
	 * HttpStatus.CREATED); }
	 */

	@GetMapping("/add")
	public String saveEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employee/employeeList";
	}

	// build get all employee REST API
	@GetMapping
	public String getAllEmployees(Model model) {
		model.addAttribute("employees", employeeService.getAllEmployees());
		return "employee/employeeList";
	}

	// build get employee by id REST API
	// http://localhost:8080/api/employees/1
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId) {
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
	}

	// build update employee by id REST API
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,
			@PathVariable("id") long employeeId) {
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, employeeId), HttpStatus.OK);

	}

	// build delete employee by id REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") long id) {
		employeeService.deleteEmployeeById(id);
		return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
	}
}
