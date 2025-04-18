package com.thymelaf.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thymelaf.project.model.Employee;
import com.thymelaf.project.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired  //we are taking employee repositroy functions
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();   //this will retrun employee list to controller
	}

	@Override
	public void saveEmployee(Employee employee) {
		// service leorru emthod kodukumbo ivide varum
		//employeeRepositorycall cheyth save cheyua
		
		this.employeeRepository.save(employee); //save employ method employee nne database lekke save cheyum
	}

	@Override
	public Employee getEmployeeById(long id) {
		// findbyId optional object return cheyum
		
		Optional<Employee> optional= employeeRepository.findById(id);
		Employee employee = null;  //employee object null ayyi default declare chyethu
		//inni optional le ninie employee ne edukam
		if(optional.isPresent()) {
			employee = optional.get(); //to get the employee
		}else {
			throw new RuntimeException("Employee not found for id  :: "+id);
		}
		return employee;   //inni eey ivide kodutha method controller le nine call cheynam
		
	}

	@Override
	public void deleteEmployeeById(long id) {
		// we justcall employee repository and call deletebyId method fromservice
		
		this.employeeRepository.deleteById(id);
		
	}

	@Override
	public Page<Employee> findingPaginated(int pageNo, int pageSize) {
		// letscreate pagable object.base page number 1.starts with 1.if page no is 2,then 2 -1 =1 like that.if page number is 1, then 1-1 = 0 we pass 0 to page request. spring consider page start with 0. 
		Pageable pageable= PageRequest.of(pageNo - 1, pageSize);  //service le pass cheythath. pagable orru interface ann . pagerequest orru class ume
		
		return this.employeeRepository.findAll(pageable);  //finds all pageable object. now go to controller
	}

}
