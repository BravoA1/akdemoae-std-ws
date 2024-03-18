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

import af.cmr.indyli.akdemia.business.dto.CompanyDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.ICompanyService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;
import af.cmr.indyli.akdemia.ws.boot.AkdemiaStdWsApplication;
import jakarta.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AkdemiaStdWsApplication.class)
public class CompanyServiceTest {

    @Resource(name = AkdemiaConstantesService.COMPANY_SERVICE_KEY)
    private ICompanyService companyService;

    private Integer companyIdForAllTest;
    private Integer companyIdCreateTest;

    @Before
    public void prepareAllEntityBefore() throws AkdemiaBusinessException {
        // Création de la company
        CompanyDto company = createcompany("PHP Langage", "Langage de programmation...");
        company = this.companyService.create(company);
        Assert.assertNotNull(company.getId());
        this.companyIdForAllTest = company.getId();
    }

    @Test
    public void testCreateCompanyWithSuccess() throws AkdemiaBusinessException {
        // Given
        CompanyDto company = createcompany("PHP Langage", "Langage de programmation...");
        // When
        company = this.companyService.create(company);
        Assert.assertNotNull(company.getId());
        this.companyIdCreateTest = company.getId();
    }

    @Test
    public void testFindAllcompanysWithSuccess() {
        // When
        List<CompanyDto> companies = this.companyService.findAll();
        // Then
        Assert.assertTrue(companies.size() > 0);
    }

    @Test
    public void testFindByIdWithSuccess() throws AkdemiaBusinessException {
        // Given
        Integer companyId = this.companyIdForAllTest;
        // When
        CompanyDto company = this.companyService.findById(companyId);
        // Then
        Assert.assertNotNull(company);
    }

    @Test
    public void testDeletecompanyWithSuccess() throws AccessDeniedException, AkdemiaBusinessException {
        // Given
        Integer companyId = this.companyIdForAllTest;
        // When
        this.companyService.deleteById(companyId);
        // Then
        CompanyDto company = this.companyService.findById(companyId);
        Assert.assertNull(company);
    }

    @Test
    public void testUpdatecompany() throws AccessDeniedException, AkdemiaBusinessException {
        // Given
        CompanyDto company = this.companyService.findById(this.companyIdForAllTest);
        company.setName("PHP Laravel Vol 2");
        // When
        this.companyService.update(company);
        CompanyDto companyUpdate = this.companyService.findById(this.companyIdForAllTest);
        // Then
        Assert.assertEquals("PHP Laravel Vol 2", companyUpdate.getName());
    }

    @After
    public void deleteAllEntityAfter() throws AkdemiaBusinessException, AccessDeniedException {
        if (!Objects.isNull(this.companyIdForAllTest)) {
            this.companyService.deleteById(this.companyIdForAllTest);
        }
        if (!Objects.isNull(this.companyIdCreateTest)) {
            this.companyService.deleteById(this.companyIdCreateTest);
        }
    }

    private CompanyDto createcompany(String name, String activity) throws AkdemiaBusinessException {
        CompanyDto company = new CompanyDto();
        company.setName(name);
        company.setActivity(activity);
        company.setCreationDate(new Date());
        return company;
    }
}