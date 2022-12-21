package management.system.sevice.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import management.system.exception.ResourceNotFoundException;
import management.system.model.Employee;
import management.system.repository.EmployeeRepository;
import management.system.sevice.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {

		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {

		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		/*
		 * Optional<Employee> employeeOptional = employeeRepository.findById(id); if
		 * (employeeOptional.isPresent()) { return employeeOptional.get(); } else {
		 * throw new ResourceNotFoundException("Employee", "Id", id); }
		 */
		return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// we need to check whether employee with given id is exist in DB or not
		Employee exsistingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
		exsistingEmployee.setFirstName(employee.getFirstName());
		exsistingEmployee.setLastName(employee.getLastName());
		exsistingEmployee.setEmail(employee.getEmail());
		// save existing employee in DB with modification
		employeeRepository.save(exsistingEmployee);
		return exsistingEmployee;
	}

	@Override
	public void deleteEmployeeById(long id) {
		Employee exsistingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
		employeeRepository.delete(exsistingEmployee);

	}

}
