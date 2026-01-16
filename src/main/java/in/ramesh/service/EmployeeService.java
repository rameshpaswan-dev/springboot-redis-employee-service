package in.ramesh.service;

import java.util.List;

import in.ramesh.model.Employee;

public interface EmployeeService {
	
	// add employee
	public void saveEmployee(Employee employee);
	
	// read employee
	public Employee getEmployee(String id);
	
	// read all Employee
	public List<Employee> getAllEmployees();
	
	//update employee
	
	public void updateEmployee(Employee employee);
	
	// delete Employee
	
	public void deleteEmployee(String id);
}
