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

import af.cmr.indyli.akdemia.business.dto.CompanyDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.ICompanyService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaConstantesWeb.AkdemiaConstantesURI;
import jakarta.annotation.Resource;

@RestController
@RequestMapping(AkdemiaConstantesURI.PATH_COMPANY)
public class CompanyRestController {

    @Resource(name = AkdemiaConstantesService.COMPANY_SERVICE_KEY)
    private ICompanyService companyService;
    
    @RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyDto>> listAllCompany() {
        List<CompanyDto> companysList = companyService.findAll();
        return ResponseEntity.ok(companysList);
    }


    @RequestMapping(value=AkdemiaConstantesURI.PATH_COMPANY_ID,method = RequestMethod.GET)
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable("companyId") Integer companyId)  {
        CompanyDto foundExposedAlerte = null;
        try {
            foundExposedAlerte = companyService.findById(companyId);
        } catch (AkdemiaBusinessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(foundExposedAlerte);
    }

    @RequestMapping(value=AkdemiaConstantesURI.PATH_COMPANY_ID,method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCompanyById(@PathVariable("companyId") Integer companyId)  {
        try {
            companyService.deleteById(companyId);
        } catch (AkdemiaBusinessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCompany(@RequestBody CompanyDto company) {
        try {
        	// Modification
        	CompanyDto companydto = companyService.update((this.companyService.create(company)));
            //companydto = companyService.update(companydto);
            System.out.println("Company password"+companydto.getPassword());
            return ResponseEntity.ok(companydto);
            //return ResponseEntity.ok(this.companyService.create(company));
        } catch (AkdemiaBusinessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping(value = AkdemiaConstantesURI.PATH_COMPANY_ID,
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDto> updateCompany(@RequestBody CompanyDto company, @PathVariable("companyId") Integer companyId) throws AkdemiaBusinessException {
        
        CompanyDto companyOne = this.companyService.findById(companyId);
        if(companyOne == null) {
            return ResponseEntity.notFound().build(); 
        }
        company.setId(companyId);
      
        CompanyDto updateNewcompany =  this.companyService.update(company);
      
        return ResponseEntity.ok().body(updateNewcompany);
    }
    
}