package af.cmr.indyli.akdemia.ws.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import af.cmr.indyli.akdemia.business.dto.CompanyDto;
import af.cmr.indyli.akdemia.business.dto.TrainingDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.ITrainingService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaConstantesWeb.AkdemiaConstantesURI;
import jakarta.annotation.Resource;

@RestController
@RequestMapping(AkdemiaConstantesURI.PATH_TRAINING)
public class TrainingRestController {
    
    @Resource(name = AkdemiaConstantesService.TRAINING_SERVICE_KEY)
    private ITrainingService trainingService;
    
    @RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TrainingDto>> listAllTraining(){
        List<TrainingDto> trainingsList = trainingService.findAll();
        return ResponseEntity.ok(trainingsList);
        
    }
    

    @RequestMapping(value=AkdemiaConstantesURI.PATH_TRAINING_ID,method = RequestMethod.GET)
    public ResponseEntity<TrainingDto> gettrainingById(@PathVariable Integer trainingId)  {
        TrainingDto foundExposedAlerte = null;
        try {
            foundExposedAlerte = trainingService.findById(trainingId);
        } catch (AkdemiaBusinessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(foundExposedAlerte);
    }

    @RequestMapping(value=AkdemiaConstantesURI.PATH_TRAINING_ID,method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTrainingById(@PathVariable Integer trainingId)  {
        try {
            trainingService.deleteById(trainingId);
        } catch (AkdemiaBusinessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createtraining(@RequestBody TrainingDto training) {
        try {
        	TrainingDto trainingdto = trainingService.update((this.trainingService.create(training)));
            return ResponseEntity.ok(trainingdto);
        } catch (AkdemiaBusinessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping(value = "/{trainingId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatetraining(@RequestBody TrainingDto training, @PathVariable("trainingId") Integer trainingId) {
        try {
            this.trainingService.update(training);
        } catch (AkdemiaBusinessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.noContent().build();
    }

}
