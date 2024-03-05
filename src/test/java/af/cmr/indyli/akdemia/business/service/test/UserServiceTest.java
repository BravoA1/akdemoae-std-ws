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

import af.cmr.indyli.akdemia.business.dto.UserDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IUserService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;
import af.cmr.indyli.akdemia.ws.boot.AkdemiaStdWsApplication;
import jakarta.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AkdemiaStdWsApplication.class)
public class UserServiceTest {
	
	@Resource(name = AkdemiaConstantesService.USER_SERVICE_KEY)
    IUserService userService;
	
	private Integer userIdForAllTest = null;
	private Integer createUserId = null;
	
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Test
	public void createUserId() throws AkdemiaBusinessException {
		UserDto user = new UserDto();
		bcryptEncoder = new BCryptPasswordEncoder();
		
		user.setAddress("Paris, France");
		user.setEmail("cedric.emana@indyli-services.com");
		user.setPhone("0398521647");
		user.setCreationDate(new Date());
		user.setLogin("cedric");
		String encryptPassword = this.bcryptEncoder.encode("1234");
		user.setPassword(encryptPassword);
		
		user = userService.create(user);
		this.createUserId = user.getId();
	}
	
	@Test
	public void testFindAllUserWithSuccess() {
		// Given
		// When
		List<UserDto> users = this.userService.findAll();
		// Then
		Assert.assertTrue(users.size() > 0);
	}
	
	@Test
	public void testFindByIdWithSuccess() throws AkdemiaBusinessException {
		// Given
		Integer userId = this.userIdForAllTest;
		// When
		UserDto user = this.userService.findById(userId);
		// Then
		Assert.assertTrue(user.getId() == userId);
	}
	
	@Test
	public void testUpdateUser() throws AccessDeniedException, AkdemiaBusinessException {
		// Given
		UserDto user = this.userService.findById(this.userIdForAllTest);
		user.setPhone("06854263985");
		// When
		this.userService.update(user);
		UserDto userUpdate = this.userService.findById(this.userIdForAllTest);
		// Then

		Assert.assertTrue(userUpdate.getPhone() == "06854263985");
	}
	
	@Test
	public void testDelete() throws AccessDeniedException, Exception {
		// Given
		Integer userId = this.userIdForAllTest;
		this.userIdForAllTest = null;
		// Whens
		this.userService.deleteById(userId);
		UserDto user = this.userService.findById(userId);

		// Then
		Assert.assertNull(user);

	}
	
	@Test
	public void testGetUserByEmail() throws AkdemiaBusinessException{
		 //Recuperation par email
		UserDto user = userService.findByEmail("Jacques@example.com");
        System.out.println("User authentifié :" + user);
        Assert.assertTrue(user != null);
	}
	
	@Before
	public void prepareAllEntityBefore() throws AkdemiaBusinessException {
		bcryptEncoder = new BCryptPasswordEncoder();
		UserDto user = new UserDto();
		user.setAddress("Paris, France");
		user.setEmail("zoyim.loti@indyli-services.com");
		user.setPhone("0398521647");
		user.setCreationDate(new Date());
		user.setLogin("aziz");
		String encryptPassword = bcryptEncoder.encode("1234");
		user.setPassword(encryptPassword);
		
		user = userService.create(user);
		this.userIdForAllTest = user.getId();
	}
	
	@After
	public void deleteAllEntityAfter() throws AccessDeniedException, AkdemiaBusinessException {
		if (this.userIdForAllTest != null) {
			this.userService.deleteById(this.userIdForAllTest);
		}

		if (!Objects.isNull(this.createUserId)) {
			this.userService.deleteById(this.createUserId);
		}
	}

}
