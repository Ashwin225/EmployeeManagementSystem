package com.thymelaf.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.thymelaf.project.model.Employee;
import com.thymelaf.project.service.EmployeeService;
@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	
	
	/*
	 * @GetMapping("/") 
	 * public String HomePage() {
	 * 
	 * return "header"; }
	 */
	
	@GetMapping("/login")
	public String loginPage() {
		
		return "login";
	}
	@GetMapping("/register")
	public String registerPage() {
		
		return "register";
	}
	
	@GetMapping("/")
	public String viewEmployeeList(Model model) {
		
		model.addAttribute("listEmployees", employeeService.getAllEmployees());  //attribut-value and service class.because getallemloyeelit is in it
		//display list of employees
		return "employee-list";
	}
	@GetMapping("/add-employee")
	public String showNewEmployeeForm(Model model) {
		Employee employee=new Employee();
		model.addAttribute("employee", employee); //eey model use chyeth model data bind cheyam
		return "add-employee";
	}
	
	@PostMapping("/save-employee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee)  //ellla form data umme employee lekke bind akkum
	{
		//save employee to database
		employeeService.saveEmployee(employee);
		
		return "redirect:/"; //register cheyth itt home page lekke thanne thrich varan
	}
	
	@GetMapping("/updateEmployeeForm/{id}")  //nammal employee-list le kodutha id.i dbind cheyan path variable kodukanam
	public String updateEmployeeForm(@PathVariable (value = "id") long id , Model model) {
		//get employee from the service
		Employee employee = employeeService.getEmployeeById(id);
		
		//set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		return "update-employee";    //save employee kkum update employee kkum same page thanne use cheyam
	}
	
	@GetMapping("/deleteEmployeeForm/{id}")
	public String deleteEmployeeForm(@PathVariable (value = "id") long id , Model model) {
		//calldelete employee method from the service
		this.employeeService.deleteEmployeeById(id);
		
		return "redirect:/"; //register cheyth itt home page lekke thanne thrich varan
	}
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable ( value= "pageNo")int pageNo, Model model) {
		
		int pageSize = 5; //page size is fixed
		
		Page<Employee> page = employeeService.findingPaginated(pageNo, pageSize);
		List<Employee> listEmployees = page.getContent(); //get lsit of employees from page object/
		
		//we wantto return somethinf to thymeeleaf page so we are giving model
		model.addAttribute("currentpage", pageNo);  //currentpage - page number
		model.addAttribute("totalpages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listEmployees", listEmployees);
		
		return "index";
	}

}
