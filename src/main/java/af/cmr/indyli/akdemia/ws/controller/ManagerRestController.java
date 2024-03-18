package af.cmr.indyli.akdemia.ws.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import af.cmr.indyli.akdemia.business.dto.ManagerDto; 
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IManagerService; 
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaConstantesWeb.AkdemiaConstantesURI;
import jakarta.annotation.Resource;


@RestController
@RequestMapping(AkdemiaConstantesURI.PATH_MANAGER)
public class ManagerRestController {

	@Resource(name = AkdemiaConstantesService.MANAGER_SERVICE_KEY)
	private IManagerService managerService;
	
	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ManagerDto>> listAllManager() {
		List<ManagerDto> managersList = managerService.findAll();
		return ResponseEntity.ok(managersList);
	}


	@RequestMapping(value=AkdemiaConstantesURI.PATH_MANAGER_ID,method = RequestMethod.GET)
	public ResponseEntity<ManagerDto> getManagerById(@PathVariable("managerId") Integer managerId)  {
		ManagerDto foundExposedAlerte = null;
		try {
			foundExposedAlerte = managerService.findById(managerId);
		} catch (AkdemiaBusinessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(foundExposedAlerte);
	}

	@RequestMapping(value=AkdemiaConstantesURI.PATH_MANAGER_ID,method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteManagerById(@PathVariable("managerId") Integer managerId)  {
		try {
			managerService.deleteById(managerId);
		} catch (AkdemiaBusinessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createManager(@RequestBody ManagerDto manager) {
		try {
			return ResponseEntity.ok(this.managerService.create(manager));
		} catch (AkdemiaBusinessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping(value = AkdemiaConstantesURI.PATH_MANAGER_ID,
			consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ManagerDto> updateManager(@RequestBody ManagerDto manager, @PathVariable("managerId") Integer managerId) throws AkdemiaBusinessException {
		
		ManagerDto managerOne = this.managerService.findById(managerId);
		if(managerOne == null) {
			return ResponseEntity.notFound().build(); 
		}
		manager.setId(managerId);
	  
		ManagerDto updateNewManager =  this.managerService.update(manager);
	  
		return ResponseEntity.ok().body(updateNewManager);
	}
	
}
