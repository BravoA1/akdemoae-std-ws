package af.cmr.indyli.akdemia.ws.controller.test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import af.cmr.indyli.akdemia.business.dto.ThemeDto;

public class ThemeRestControllerTest {

public final static String AKDEMIA_URI_PREFIX = "http://localhost:8081/api";
	
	@Test
	public void testGetThemeById() throws Exception {
	  Integer id = 2;
	  ThemeDto foundTheme = new RestTemplate().getForObject(AKDEMIA_URI_PREFIX+"/themes/{themeId}", ThemeDto.class, id);
	  System.out.println(foundTheme);
	  Assert.assertNotNull(foundTheme);
	}
	
	@Test
	public void testGetAllThemes() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<ThemeDto>> response = restTemplate.exchange(
				AKDEMIA_URI_PREFIX+"/themes",
		  HttpMethod.GET,
		  null,
		  new ParameterizedTypeReference<List<ThemeDto>>(){});
		List<ThemeDto> themeList = response.getBody();
		Assert.assertTrue(themeList.size() >0);
	}
	
	@Test
	public void testCreateTheme() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		ThemeDto themeACreer = new ThemeDto();
		
		themeACreer.setThemeTitle("Langage de programmation");
		themeACreer.setDescription("description3");
		themeACreer.setCreationDate(new Date());
		
		ThemeDto newTheme = restTemplate.postForObject(AKDEMIA_URI_PREFIX + "/themes", themeACreer, ThemeDto.class);
		
		Assert.assertNotNull(newTheme);
	    Assert.assertNotNull(newTheme.getId());
	    Assert.assertEquals(themeACreer.getThemeTitle(), newTheme.getThemeTitle());
	}
	
	@Test
	public void testUpdateTheme() throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
		ThemeDto themeACreer = new ThemeDto();
		
		themeACreer.setThemeTitle("Langage de programmation Vol 2");
		themeACreer.setDescription("description3");
		themeACreer.setCreationDate(new Date());
	    
		ThemeDto createdTheme = restTemplate.postForObject(AKDEMIA_URI_PREFIX + "/themes", themeACreer, ThemeDto.class);
	    Assert.assertNotNull(createdTheme);
	    Assert.assertNotNull(createdTheme.getId());
	    
	    createdTheme.setThemeTitle("Langage de programmation et Web services");
	    
	    restTemplate.put(AKDEMIA_URI_PREFIX + "/themes/" + createdTheme.getId(), createdTheme);

		
	    ThemeDto updatedTheme = restTemplate.getForObject(AKDEMIA_URI_PREFIX + "/themes/" + createdTheme.getId(), ThemeDto.class);
	    Assert.assertNotNull(updatedTheme);
	    Assert.assertEquals("Langage de programmation et Web services", updatedTheme.getThemeTitle());
	    
	}
	
	@Test
	public void testDeleteTraining() throws Exception {
	    RestTemplate restTemplate = new RestTemplate();

	    Long themeIdToDelete = 1L;

	    restTemplate.delete(AKDEMIA_URI_PREFIX + "/themes/" + themeIdToDelete);

	    try {
	        restTemplate.getForObject(AKDEMIA_URI_PREFIX + "/themes/" + themeIdToDelete, ThemeDto.class);
	        
	    } catch (HttpClientErrorException.NotFound e) {
	        System.out.println("Le theme avec l'ID " + themeIdToDelete + " a été supprimé avec succès.");

	        ResponseEntity<ThemeDto> response = null;
	        try {
	            response = restTemplate.getForEntity(AKDEMIA_URI_PREFIX + "/themes/" + themeIdToDelete, ThemeDto.class);
	        } catch (HttpClientErrorException.NotFound ex) {
	            // Si la formation n'est pas trouvé, c'est un succès
	        }

	        Assert.assertNull(response.getBody());
	    }
	}
	
}
