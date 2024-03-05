package af.cmr.indyli.akdemia.business.service.test;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import af.cmr.indyli.akdemia.business.dto.ParticularDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IParticularService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;
import af.cmr.indyli.akdemia.ws.boot.AkdemiaStdWsApplication;
import jakarta.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AkdemiaStdWsApplication.class)
public class ParticularServiceTest {

	@Resource(name = AkdemiaConstantesService.PARTICULAR_SERVICE_KEY)
    IParticularService particularService;
	
	private BCryptPasswordEncoder bcryptEncoder;
	
	private Integer particularIdForAllTest = null;
	private Integer particularIdCreateTest = null;
	
	@Test
	public void testCreateParticularWithSuccess() throws AkdemiaBusinessException {
		
		// Given
		ParticularDto particular = new ParticularDto();
		bcryptEncoder = new BCryptPasswordEncoder();
		
		particular.setFirstname("Daria");
		particular.setLastname("Manuella");
		particular.setGender("F");
		particular.setActivity("Stagiaire");
		particular.setHighestDiploma("Master");
		particular.setBirthDate(new Date());
		particular.setLogin("daria");
		particular.setPassword(this.bcryptEncoder.encode("1234"));
		particular.setEmail("dariamanuella@gmail.com");
		particular.setAddress("Paris, France");
		particular.setPhone("06974582");
		particular.setCreationDate(new Date());
		
		particular = this.particularService.create(particular);
		Assert.assertNotNull(particular.getId());
		
		this.particularIdCreateTest = particular.getId();

	}
	
	@Test
	public void testFindAllParticularsWithSuccess() {
		// Given
		// When
		List<ParticularDto> particulars = this.particularService.findAll();

		// Then
		Assert.assertTrue(particulars.size() > 0);
	}
	
	@Test
	public void testFindByIdWithSuccess() throws AkdemiaBusinessException {
		// Given
		Integer particularId = this.particularIdForAllTest;
		// When
		ParticularDto particular = this.particularService.findById(particularId);
		// Then
		Assert.assertTrue(particular.getId() == particularId);
	}
	
	@Test
	public void testDeleteParticularWithSuccess() throws AccessDeniedException, AkdemiaBusinessException {
		// Given
		Integer particularId = this.particularIdForAllTest;
		particularIdForAllTest = null;
		// When
		this.particularService.deleteById(particularId);

		// Then
		ParticularDto particular = this.particularService.findById(particularId);
		Assert.assertNull(particular);
	}
	
	@Test
	public void testUpdateParticular() throws AccessDeniedException, AkdemiaBusinessException {
		// Given
		ParticularDto particular = this.particularService.findById(this.particularIdForAllTest);
		particular.setPhone("064785932");
		// When
		this.particularService.update(particular);
		ParticularDto particularUpdate = this.particularService.findById(this.particularIdForAllTest);
		// Then

		Assert.assertTrue(particularUpdate.getPhone() == "064785932");
	}
	
	
	@Before
	public void prepareAllEntityBefore() throws AkdemiaBusinessException {
 
		// creation particular
		ParticularDto particular = new ParticularDto();
		bcryptEncoder = new BCryptPasswordEncoder();
		
		particular.setFirstname("Lamine");
		particular.setLastname("Bafoil");
		particular.setGender("H");
		particular.setActivity("Stagiaire");
		particular.setHighestDiploma("Master");
		particular.setBirthDate(new Date());
		particular.setLogin("lamine");
		particular.setPassword(this.bcryptEncoder.encode("1234"));
		particular.setEmail("laminebafoil@gmail.com");
		particular.setAddress("Paris, France");
		particular.setPhone("06974582");
		particular.setCreationDate(new Date());
		
		particular = this.particularService.create(particular);

		Assert.assertNotNull(particular.getId());

		this.particularIdForAllTest = particular.getId();
	}
	
	@After
	public void deleteAllEntityAfter() throws AkdemiaBusinessException, AccessDeniedException {
		if (!Objects.isNull(this.particularIdCreateTest)) {
			this.particularService.deleteById(this.particularIdForAllTest);
		}
		if (!Objects.isNull(this.particularIdCreateTest)) {
			this.particularService.deleteById(this.particularIdCreateTest);
		}
	}
	
}
