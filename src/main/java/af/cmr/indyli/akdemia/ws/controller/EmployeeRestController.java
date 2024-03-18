package af.cmr.indyli.akdemia.ws.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import af.cmr.indyli.akdemia.business.dto.EmployeeDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IEmployeeService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaConstantesWeb.AkdemiaConstantesURI;
import jakarta.annotation.Resource;

@RestController
@RequestMapping(AkdemiaConstantesURI.PATH_EMPLOYEE)
public class EmployeeRestController {
	
	@Resource(name = AkdemiaConstantesService.EMPLOYEE_SERVICE_KEY)
	private IEmployeeService employeeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<EmployeeDto> listAllEmployees() {
	   List<EmployeeDto> employeesList = employeeService.findAll();
	   return employeesList;
	}
	
	@RequestMapping(method = RequestMethod.POST,
		      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createNewEmployee(@RequestBody EmployeeDto employee) throws AkdemiaBusinessException {
		  
		 if(StringUtils.isBlank(employee.getEmail()) || StringUtils.isBlank(employee.getLogin())) {
		  	return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED) 
		  	            .body("L'email ou le login semble non renseigné...");
		 }
		  	
		return ResponseEntity.ok(this.employeeService.create(employee));
	}
	
	@PutMapping(value = "/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeACreerView, @PathVariable("employeeId") Integer employeeId) throws AkdemiaBusinessException {
		EmployeeDto employeeOne = this.employeeService.findById(employeeId);
		if(employeeOne == null) {
			return ResponseEntity.notFound().build(); 
		}
		// Forcer l'id de l'employé à mettre à jour. 
		employeeACreerView.setId(employeeId);
	  
		EmployeeDto updateNewEmployee =  this.employeeService.update(employeeACreerView);
	  
		return ResponseEntity.ok().body(updateNewEmployee);
	}
	
	@RequestMapping(value="/{employeeId}",method = RequestMethod.DELETE)
	public ResponseEntity<EmployeeDto> deleteEmployeeById(@PathVariable Integer employeeId) throws AkdemiaBusinessException  {
		this.employeeService.deleteById(employeeId);
		return ResponseEntity.ok().build(); 
	}
	
	@GetMapping(value = AkdemiaConstantesURI.PATH_EMPLOYEE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findOneEmployee(@PathVariable Integer employeeId) {
	    EmployeeDto employee;
	    try {
	      employee = this.employeeService.findById(employeeId);
	    } catch (AkdemiaBusinessException e) {
	      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	    return ResponseEntity.ok(employee);
	}
}