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

import af.cmr.indyli.akdemia.business.dto.UserDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IUserService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaConstantesWeb.AkdemiaConstantesURI;
import jakarta.annotation.Resource;

@RestController
@RequestMapping(AkdemiaConstantesURI.PATH_USER)
public class UserRestController {
	
	@Resource(name = AkdemiaConstantesService.USER_SERVICE_KEY)
	private IUserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<UserDto> listAllUsers() {
	   List<UserDto> usersList = userService.findAll();
	   return usersList;
	}
	
	@RequestMapping(method = RequestMethod.POST,
		      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createNewUser(@RequestBody UserDto user) throws AkdemiaBusinessException {
		  
		 if(StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getLogin())) {
		  	return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED) 
		  	            .body("L'email ou le login semble non renseigné...");
		 }
		  	
		return ResponseEntity.ok(this.userService.create(user));
	}
	
	@PutMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userACreerView, @PathVariable("userId") Integer userId) throws AkdemiaBusinessException {
		UserDto userOne = this.userService.findById(userId);
		if(userOne == null) {
			return ResponseEntity.notFound().build(); 
		}
		userACreerView.setId(userId);
	  
		UserDto updateNewUser =  this.userService.update(userACreerView);
	  
		return ResponseEntity.ok().body(updateNewUser);
	}
	
	@RequestMapping(value="/{userId}",method = RequestMethod.DELETE)
	public ResponseEntity<UserDto> deleteUserById(@PathVariable Integer userId) throws AkdemiaBusinessException  {
		this.userService.deleteById(userId);
		return ResponseEntity.ok().build(); 
	}
	
	@GetMapping(value = AkdemiaConstantesURI.PATH_USER_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findOneUser(@PathVariable Integer userId) {
	    UserDto user;
	    try {
	      user = this.userService.findById(userId);
	    } catch (AkdemiaBusinessException e) {
	      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	    return ResponseEntity.ok(user);
	}
}
