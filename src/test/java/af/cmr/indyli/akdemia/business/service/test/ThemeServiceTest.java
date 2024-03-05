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

import af.cmr.indyli.akdemia.business.dto.ThemeDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IThemeService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;
import af.cmr.indyli.akdemia.ws.boot.AkdemiaStdWsApplication;
import jakarta.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AkdemiaStdWsApplication.class)
public class ThemeServiceTest {
	
	@Resource(name = AkdemiaConstantesService.THEME_SERVICE_KEY)
    IThemeService themeService;
	
	private Integer themeIdForAllTest = null;
	private Integer themeIdCreateTest = null;
	
	@Test
	public void testCreateThemeWithSuccess() throws AkdemiaBusinessException {
		
		// Given
		ThemeDto theme = new ThemeDto();
		
		theme.setThemeTitle("PHP Langage");
		theme.setDescription("Langage de programmation...");
		theme.setCreationDate(new Date());
		
		theme = this.themeService.create(theme);
		Assert.assertNotNull(theme.getId());
		
		this.themeIdCreateTest = theme.getId();

	}
	
	@Test
	public void testFindAllThemetsWithSuccess() {
		// Given
		// When
		List<ThemeDto> themes = this.themeService.findAll();

		// Then
		Assert.assertTrue(themes.size() > 0);
	}
	
	@Test
	public void testFindByIdWithSuccess() throws AkdemiaBusinessException {
		// Given
		Integer themeId = this.themeIdForAllTest;

		// When
		ThemeDto theme = this.themeService.findById(themeId);

		// Then
		Assert.assertNotNull(theme);
	}
	
	@Test
	public void testDeleteThemeWithSuccess() throws AccessDeniedException, AkdemiaBusinessException {
		// Given
		Integer themeId = this.themeIdForAllTest;
		themeIdForAllTest = null;
		// When
		this.themeService.deleteById(themeId);

		// Then
		ThemeDto theme = this.themeService.findById(themeId);
		Assert.assertNull(theme);
	}
	
	@Test
	public void testUpdateTheme() throws AccessDeniedException, AkdemiaBusinessException {
		// Given
		ThemeDto theme = this.themeService.findById(this.themeIdForAllTest);
		theme.setThemeTitle("PHP Laravel Vol 2");
		// When
		this.themeService.update(theme);
		ThemeDto themeUpdate = this.themeService.findById(this.themeIdForAllTest);
		// Then

		Assert.assertTrue(themeUpdate.getThemeTitle() == "PHP Laravel Vol 2");
	}
	
	
	@Before
	public void prepareAllEntityBefore() throws AkdemiaBusinessException {
 
		// creation theme
		ThemeDto theme = new ThemeDto();
		
		theme.setThemeTitle("PHP Langage");
		theme.setDescription("Langage de programmation...");
		theme.setCreationDate(new Date());
		
		theme = this.themeService.create(theme);

		Assert.assertNotNull(theme.getId());

		this.themeIdForAllTest = theme.getId();
	}
	
	@After
	public void deleteAllEntityAfter() throws AkdemiaBusinessException, AccessDeniedException {
		if (!Objects.isNull(this.themeIdCreateTest)) {
			this.themeService.deleteById(this.themeIdForAllTest);
		}
		if (!Objects.isNull(this.themeIdCreateTest)) {
			this.themeService.deleteById(this.themeIdCreateTest);
		}
	}
}
