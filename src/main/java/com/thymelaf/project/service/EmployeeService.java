package com.thymelaf.project.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.thymelaf.project.model.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();  
	 void saveEmployee(Employee employee);
	 Employee getEmployeeById(long id);///employee nte update cheyan
	 
	 void deleteEmployeeById(long id); //long id orru paramter ayittan ivide pass cheyunath
//ivide nammal page nte declare cheyuvann . athayath orru page nne next page lekke data full akumbo pookan
	 Page<Employee>  findingPaginated(int pageNo,int pageSize);  //now go and implement this method
}
