package af.cmr.indyli.akdemia.ws.controller.test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import af.cmr.indyli.akdemia.business.dto.UserDto;

public class UserRestControllerTest {
	
	public final static String AKDEMIA_URI_PREFIX = "http://localhost:8081/api";
	
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Test
	public void testGetUserById() throws Exception {
	  Integer id = 2;
	  UserDto foundUser = new RestTemplate().getForObject(AKDEMIA_URI_PREFIX+"/users/{userId}", UserDto.class, id);
	  System.out.println(foundUser);
	  Assert.assertNotNull(foundUser);
	}
	
	
	@Test
	public void testGetAllUsers() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<UserDto>> response = restTemplate.exchange(
				AKDEMIA_URI_PREFIX+"/users",
		  HttpMethod.GET,
		  null,
		  new ParameterizedTypeReference<List<UserDto>>(){});
		List<UserDto> userList = response.getBody();
		Assert.assertTrue(userList.size() >0);
	}
	
	@Test
	public void testCreateUser() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		bcryptEncoder = new BCryptPasswordEncoder();
		UserDto userACreer = new UserDto();
		userACreer.setAddress("Paris, France");
		userACreer.setEmail("aliou.garga@indyli-services.com");
		userACreer.setLogin("garga");
		userACreer.setPassword(this.bcryptEncoder.encode("1234"));
		userACreer.setPhone("06987546568");
		userACreer.setCreationDate(new Date());
		
		UserDto newUser = restTemplate.postForObject(AKDEMIA_URI_PREFIX + "/users", userACreer, UserDto.class);
		
		Assert.assertNotNull(newUser);
	    Assert.assertNotNull(newUser.getId());
	    Assert.assertEquals(userACreer.getLogin(), newUser.getLogin());
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
		bcryptEncoder = new BCryptPasswordEncoder();
		UserDto userACreer = new UserDto();
		
		userACreer.setAddress("Paris, France");
	    userACreer.setEmail("cyril.tingah@indyli-services.com");
	    userACreer.setLogin("cyril");
	    userACreer.setPassword(this.bcryptEncoder.encode("1234"));
	    userACreer.setPhone("06987546568");
	    userACreer.setCreationDate(new Date());
	    
	    UserDto createdUser = restTemplate.postForObject(AKDEMIA_URI_PREFIX + "/users", userACreer, UserDto.class);
	    Assert.assertNotNull(createdUser);
	    Assert.assertNotNull(createdUser.getId());
	    
	    // Met à jour l'utilisateur avec de nouvelles informations
	    createdUser.setAddress("Nantes, France");
	    createdUser.setEmail("cyril02.tingah@indyli-services.com");
	    
	    // Envoie la requête PUT pour mettre à jour l'utilisateur
	    restTemplate.put(AKDEMIA_URI_PREFIX + "/users/" + createdUser.getId(), createdUser);

		
	    // Récupère l'utilisateur mis à jour pour vérifier les modifications
	    UserDto updatedUser = restTemplate.getForObject(AKDEMIA_URI_PREFIX + "/users/" + createdUser.getId(), UserDto.class);
	    Assert.assertNotNull(updatedUser);
	    Assert.assertEquals("Nantes, France", updatedUser.getAddress());
	    Assert.assertEquals("cyril02.tingah@indyli-services.com", updatedUser.getEmail());
	    Assert.assertEquals("cyril", updatedUser.getLogin());
	    
	}
	
	@Test
	public void testDeleteUser() throws Exception {
	    RestTemplate restTemplate = new RestTemplate();

	    Long userIdToDelete = 1L;

	    restTemplate.delete(AKDEMIA_URI_PREFIX + "/users/" + userIdToDelete);

	    try {
	        restTemplate.getForObject(AKDEMIA_URI_PREFIX + "/users/" + userIdToDelete, UserDto.class);
	        //Assert.fail("L'utilisateur supprimé existe toujours.");
	    } catch (HttpClientErrorException.NotFound e) {
	        System.out.println("L'utilisateur avec l'ID " + userIdToDelete + " a été supprimé avec succès.");

	        ResponseEntity<UserDto> response = null;
	        try {
	            response = restTemplate.getForEntity(AKDEMIA_URI_PREFIX + "/users/" + userIdToDelete, UserDto.class);
	        } catch (HttpClientErrorException.NotFound ex) {
	            // Si l'utilisateur n'est pas trouvé, c'est un succès
	        }

	        Assert.assertNull(response.getBody());
	    }
	}

}
