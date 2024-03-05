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
import org.springframework.test.context.junit4.SpringRunner;

import af.cmr.indyli.akdemia.business.dto.RequirementDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IRequirementService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;
import af.cmr.indyli.akdemia.ws.boot.AkdemiaStdWsApplication;
import jakarta.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AkdemiaStdWsApplication.class)
public class RequirementServiceTest {

	@Resource(name = AkdemiaConstantesService.REQUIREMENT_SERVICE_KEY)
    IRequirementService requirementService;
	
	private Integer requirementIdForAllTest = null;
	private Integer requirementIdCreateTest = null;
	
	@Test
	public void testCreateRequirementWithSuccess() throws AkdemiaBusinessException {
		
		// Given
		RequirementDTO requirement = new RequirementDTO();
		
		requirement.setName("Prérequis Laravel");
		requirement.setDescription("Ceci est un test de prérequis enfin de savoir si vous avez certaines notions...");
		requirement.setLink("pasdelink");
		requirement.setCreationDate(new Date());
		
		requirement = this.requirementService.create(requirement);
		Assert.assertNotNull(requirement.getId());
		
		this.requirementIdCreateTest = requirement.getId();

	}
	
	@Test
	public void testFindAllRequirementsWithSuccess() {
		// Given
		// When
		List<RequirementDTO> requirements = this.requirementService.findAll();

		// Then
		Assert.assertTrue(requirements.size() > 0);
	}
	
	@Test
	public void testFindByIdWithSuccess() throws AkdemiaBusinessException {
		// Given
		Integer requirementId = this.requirementIdForAllTest;

		// When
		RequirementDTO requirement = this.requirementService.findById(requirementId);

		// Then
		Assert.assertNotNull(requirement);
	}
	
	@Test
	public void testDeleteRequirementWithSuccess() throws AccessDeniedException, AkdemiaBusinessException {
		// Given
		Integer requirementId = this.requirementIdForAllTest;
		requirementIdForAllTest = null;
		// When
		this.requirementService.deleteById(requirementId);

		// Then
		RequirementDTO requirement = this.requirementService.findById(requirementId);
		Assert.assertNull(requirement);
	}
	
	@Test
	public void testUpdateRequirement() throws AccessDeniedException, AkdemiaBusinessException {
		// Given
		RequirementDTO requirement = this.requirementService.findById(this.requirementIdForAllTest);
		requirement.setName("Prérequis Laravel Vol 2");
		// When
		this.requirementService.update(requirement);
		RequirementDTO requirementUpdate = this.requirementService.findById(this.requirementIdForAllTest);
		// Then

		Assert.assertTrue(requirementUpdate.getName() == "Prérequis Laravel Vol 2");
	}
	
	
	@Before
	public void prepareAllEntityBefore() throws AkdemiaBusinessException {
 
		// creation requirement
		RequirementDTO requirement = new RequirementDTO();
		
		requirement.setName("Prérequis Laravel");
		requirement.setDescription("Ceci est un test de prérequis enfin de savoir si vous avez certaines notions...");
		requirement.setLink("pasdelink");
		requirement.setCreationDate(new Date());
		
		requirement = this.requirementService.create(requirement);

		Assert.assertNotNull(requirement.getId());

		this.requirementIdForAllTest = requirement.getId();
	}
	
	@After
	public void deleteAllEntityAfter() throws AkdemiaBusinessException, AccessDeniedException {
		if (!Objects.isNull(this.requirementIdCreateTest)) {
			this.requirementService.deleteById(this.requirementIdForAllTest);
		}
		if (!Objects.isNull(this.requirementIdCreateTest)) {
			this.requirementService.deleteById(this.requirementIdCreateTest);
		}
	}
	
}
