package in.ramesh.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import in.ramesh.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final String KEY = "EMPLOYEE";

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public void saveEmployee(Employee employee) {
		
		String id = UUID.randomUUID().toString();
		
		Employee emp = Employee.builder().id(id).name(employee.getName())
				.department(employee.getDepartment()).salary(employee.getSalary()).build();
		
		// Optional safety check (rare collision case)
		if (redisTemplate.opsForHash().hasKey(KEY, id)) {
		    throw new RuntimeException("Employee already exists");
		}

		redisTemplate.opsForHash().put(KEY, id, emp);

	}

	@Override
	public Employee getEmployee(String id) {
		// TODO Auto-generated method stub
		return (Employee) redisTemplate.opsForHash().get(KEY, id);
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return redisTemplate.opsForHash().entries(KEY).values().stream().map(emp -> (Employee) emp).toList();
	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		redisTemplate.opsForHash().put(KEY, employee.getId(), employee);

	}

	@Override
	public void deleteEmployee(String id) {
		// TODO Auto-generated method stub
		redisTemplate.opsForHash().delete(KEY, id);
	}

}
