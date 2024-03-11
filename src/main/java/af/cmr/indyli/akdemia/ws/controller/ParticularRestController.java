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

import af.cmr.indyli.akdemia.business.dto.ParticularDto; 
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IParticularService; 
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaConstantesWeb.AkdemiaConstantesURI;
import jakarta.annotation.Resource;

@RestController
@RequestMapping(AkdemiaConstantesURI.PATH_PARTICULAR)
public class ParticularRestController {

	@Resource(name = AkdemiaConstantesService.PARTICULAR_SERVICE_KEY)
	private IParticularService particularService;
	
	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ParticularDto>> listAllParticular() {
		List<ParticularDto> particularsList = particularService.findAll();
		return ResponseEntity.ok(particularsList);
	}


	@RequestMapping(value=AkdemiaConstantesURI.PATH_PARTICULAR_ID,method = RequestMethod.GET)
	public ResponseEntity<ParticularDto> getParticularById(@PathVariable("particularId") Integer particularId)  {
		ParticularDto foundExposedAlerte = null;
		try {
			foundExposedAlerte = particularService.findById(particularId);
		} catch (AkdemiaBusinessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(foundExposedAlerte);
	}

	@RequestMapping(value=AkdemiaConstantesURI.PATH_PARTICULAR_ID,method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteParticularById(@PathVariable("particularId") Integer particularId)  {
		try {
			particularService.deleteById(particularId);
		} catch (AkdemiaBusinessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createParticular(@RequestBody ParticularDto particular) {
		try {
			return ResponseEntity.ok(this.particularService.create(particular));
		} catch (AkdemiaBusinessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping(value = AkdemiaConstantesURI.PATH_PARTICULAR_ID,
			consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ParticularDto> updateParticular(@RequestBody ParticularDto particular, @PathVariable("particularId") Integer particularId) throws AkdemiaBusinessException {
		
		ParticularDto particularOne = this.particularService.findById(particularId);
		if(particularOne == null) {
			return ResponseEntity.notFound().build(); 
		}
	  
		ParticularDto updateNewParticular =  this.particularService.update(particular);
	  
		return ResponseEntity.ok().body(updateNewParticular);
	}
	
}
