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

import af.cmr.indyli.akdemia.business.dto.SubThemeDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.ISubThemeService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaConstantesWeb.AkdemiaConstantesURI;
import jakarta.annotation.Resource;

@RestController
@RequestMapping(AkdemiaConstantesURI.PATH_SUBTHEME)
public class SubThemeRestController {

    
    @Resource(name = AkdemiaConstantesService.SUBTHEME_SERVICE_KEY)
    private ISubThemeService subThemeService;
    
    @RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubThemeDto>> listAllSubTheme() {
        List<SubThemeDto> subThemesList = subThemeService.findAll();
        return ResponseEntity.ok(subThemesList);
    }


    @RequestMapping(value=AkdemiaConstantesURI.PATH_SUBTHEME_ID,method = RequestMethod.GET)
    public ResponseEntity<SubThemeDto> getSubThemeById(@PathVariable("subThemeId") Integer subThemeId)  {
        SubThemeDto foundExposedAlerte = null;
        try {
            foundExposedAlerte = subThemeService.findById(subThemeId);
        } catch (AkdemiaBusinessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(foundExposedAlerte);
    }

    @RequestMapping(value=AkdemiaConstantesURI.PATH_SUBTHEME_ID,method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSubThemeById(@PathVariable("subThemeId") Integer subThemeId)  {
        try {
            subThemeService.deleteById(subThemeId);
        } catch (AkdemiaBusinessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createSubTheme(@RequestBody SubThemeDto subTheme) {
        try {
            return ResponseEntity.ok(this.subThemeService.create(subTheme));
        } catch (AkdemiaBusinessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping(value = AkdemiaConstantesURI.PATH_SUBTHEME_ID,
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubThemeDto> updateSubTheme(@RequestBody SubThemeDto subTheme, @PathVariable("subThemeId") Integer subThemeId) throws AkdemiaBusinessException {
        
        SubThemeDto subThemeOne = this.subThemeService.findById(subThemeId);
        if(subThemeOne == null) {
            return ResponseEntity.notFound().build(); 
        }
        subTheme.setId(subThemeId);
      
        SubThemeDto updateNewSubTheme =  this.subThemeService.update(subTheme);
      
        return ResponseEntity.ok().body(updateNewSubTheme);
    }
}