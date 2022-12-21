package management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import management.system.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
