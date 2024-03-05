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

import af.cmr.indyli.akdemia.business.dto.RequirementDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IRequirementService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaConstantesWeb.AkdemiaConstantesURI;
import jakarta.annotation.Resource;


@RestController
@RequestMapping(AkdemiaConstantesURI.PATH_REQUIREMENT)
public class RequirementRestController {

	@Resource(name = AkdemiaConstantesService.REQUIREMENT_SERVICE_KEY)
	private IRequirementService requirementService;
	
	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RequirementDTO>> listAllrequirement() {
		List<RequirementDTO> requirementsList = requirementService.findAll();
		return ResponseEntity.ok(requirementsList);
	}


	@RequestMapping(value=AkdemiaConstantesURI.PATH_REQUIREMENT_ID,method = RequestMethod.GET)
	public ResponseEntity<RequirementDTO> getRequirementById(@PathVariable Integer requirementId)  {
		RequirementDTO foundExposedAlerte = null;
		try {
			foundExposedAlerte = requirementService.findById(requirementId);
		} catch (AkdemiaBusinessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(foundExposedAlerte);
	}

	@RequestMapping(value=AkdemiaConstantesURI.PATH_REQUIREMENT_ID,method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRequirementById(@PathVariable Integer requirementId)  {
		try {
			requirementService.deleteById(requirementId);
		} catch (AkdemiaBusinessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createRequirement(@RequestBody RequirementDTO requirement) {
		try {
			return ResponseEntity.ok(this.requirementService.create(requirement));
		} catch (AkdemiaBusinessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping(value = AkdemiaConstantesURI.PATH_REQUIREMENT_ID,
			consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateRequirement(@RequestBody RequirementDTO requirement, @PathVariable("requirementId") Integer requirementId) {
		try {
			this.requirementService.update(requirement);
		} catch (AkdemiaBusinessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return ResponseEntity.noContent().build();
	}
	
}
