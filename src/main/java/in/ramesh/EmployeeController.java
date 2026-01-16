package in.ramesh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ramesh.model.ApiResponse;
import in.ramesh.model.Employee;
import in.ramesh.service.EmployeeService;

@RestController
@Fiv
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// Create employee
	@PostMapping
	public ResponseEntity<ApiResponse<Employee>> createEmployee(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);

		ApiResponse<Employee> apiResponse = ApiResponse.<Employee>builder().status(HttpStatus.CREATED.value())
				.message("Employee create Successfully.").data(employee).build();

		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
	}

	// read employee
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<?>> getEmEntity(@PathVariable String id) {
		Employee employee = employeeService.getEmployee(id);
		if (employee == null) {
			return new ResponseEntity<>(ApiResponse.builder().status(HttpStatus.NOT_FOUND.value())
					.message("Employee not found: " + id).data(null).build(), HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(ApiResponse.builder().status(HttpStatus.OK.value())
				.message("Employee fetch successfull!").data(employeeService).build());
	}

	// read all Employee

	@GetMapping
	public ResponseEntity<ApiResponse<List<?>>> getAllEmployee() {
		List<Employee> employees = employeeService.getAllEmployees();
		
		if (employees.isEmpty()) {
		    return ResponseEntity.noContent().build();
		}


		ApiResponse<List<?>> response = ApiResponse.<List<?>>builder().status(HttpStatus.OK.value())
				.message("All employees fetched successfully").data(employees).build();

		return ResponseEntity.ok(response);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<?>> updateEmployee(@PathVariable String id , @RequestBody Employee employee){
		Employee existEmployee = employeeService.getEmployee(id);
		
		if(existEmployee == null) {
			return new ResponseEntity<>(ApiResponse.builder()
					.status(HttpStatus.NOT_FOUND.value())
					.message("Employee not found with id: "+id)
					.data(null).build(), 
					HttpStatus.NOT_FOUND
					);
		}
		employee.setId(id);
		employeeService.updateEmployee(employee);
		
		return ResponseEntity.ok(ApiResponse.builder()
				.status(HttpStatus.OK.value())
				.message("Employee update successfull!")
				.data(employee).build()
				);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<?>> deleteEmployee(@PathVariable  String id){
		Employee employee = employeeService.getEmployee(id);
		if(employee == null) {
			return new ResponseEntity<>(
					ApiResponse.builder()
					.status(HttpStatus.NOT_FOUND.value())
					.message("Employee not found with id: "+id)
					.data(null).build()
					,HttpStatus.NOT_FOUND);
		}
		employeeService.deleteEmployee(id);
		
		return ResponseEntity.ok(
				ApiResponse.builder()
				.status(HttpStatus.OK.value())
				.message("Employee delete successfully!")
				.data(null).build()
				);
	}
}
