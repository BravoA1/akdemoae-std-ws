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

import af.cmr.indyli.akdemia.business.dto.SubThemeDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.ISubThemeService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;
import af.cmr.indyli.akdemia.ws.boot.AkdemiaStdWsApplication;
import jakarta.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AkdemiaStdWsApplication.class)
public class SubThemeServiceTest {

    @Resource(name = AkdemiaConstantesService.SUBTHEME_SERVICE_KEY)
    private ISubThemeService subThemeService;

    private Integer subThemeIdForAllTest;
    private Integer subThemeIdCreateTest;

    @Before
    public void prepareAllEntityBefore() throws AkdemiaBusinessException {
        // Création du thème
        SubThemeDto subTheme = createSubTheme("PHP Langage", "Langage de programmation...");
        subTheme = this.subThemeService.create(subTheme);
        Assert.assertNotNull(subTheme.getId());
        this.subThemeIdForAllTest = subTheme.getId();
    }

    @Test
    public void testcreateSubThemeWithSuccess() throws AkdemiaBusinessException {
        // Given
        SubThemeDto subTheme = createSubTheme("PHP Langage", "Langage de programmation...");
        // When
        subTheme = this.subThemeService.create(subTheme);
        Assert.assertNotNull(subTheme.getId());
        this.subThemeIdCreateTest = subTheme.getId();
    }

    @Test
    public void testFindAllSubThemesWithSuccess() {
        // When
        List<SubThemeDto> subThemes = this.subThemeService.findAll();
        // Then
        Assert.assertTrue(subThemes.size() > 0);
    }

    @Test
    public void testFindByIdWithSuccess() throws AkdemiaBusinessException {
        // Given
        Integer subThemeId = this.subThemeIdForAllTest;
        // When
        SubThemeDto subTheme = this.subThemeService.findById(subThemeId);
        // Then
        Assert.assertNotNull(subTheme);
    }

    @Test
    public void testDeleteSubThemeWithSuccess() throws AccessDeniedException, AkdemiaBusinessException {
        // Given
        Integer subThemeId = this.subThemeIdForAllTest;
        // When
        this.subThemeService.deleteById(subThemeId);
        // Then
        SubThemeDto subTheme = this.subThemeService.findById(subThemeId);
        Assert.assertNull(subTheme);
    }

    @Test
    public void testUpdateSubTheme() throws AccessDeniedException, AkdemiaBusinessException {
        // Given
        SubThemeDto subTheme = this.subThemeService.findById(this.subThemeIdForAllTest);
        subTheme.setSubThemeTitle("PHP Laravel Vol 2");
        // When
        this.subThemeService.update(subTheme);
        SubThemeDto subThemeUpdate = this.subThemeService.findById(this.subThemeIdForAllTest);
        // Then
        Assert.assertEquals("PHP Laravel Vol 2", subThemeUpdate.getSubThemeTitle());
    }

    @After
    public void deleteAllEntityAfter() throws AkdemiaBusinessException, AccessDeniedException {
        if (!Objects.isNull(this.subThemeIdForAllTest)) {
            this.subThemeService.deleteById(this.subThemeIdForAllTest);
        }
        if (!Objects.isNull(this.subThemeIdCreateTest)) {
            this.subThemeService.deleteById(this.subThemeIdCreateTest);
        }
    }

    private SubThemeDto createSubTheme(String title, String description) throws AkdemiaBusinessException {
        SubThemeDto subTheme = new SubThemeDto();
        subTheme.setSubThemeTitle(title);
        subTheme.setDescription(description);
        subTheme.setCreationDate(new Date());
        return subTheme;
    }
}
