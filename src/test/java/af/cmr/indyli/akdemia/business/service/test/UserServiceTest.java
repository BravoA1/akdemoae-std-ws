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
    private IUserService userService;

    private Integer userIdForAllTest;
    private Integer createUserId;

    private BCryptPasswordEncoder bcryptEncoder;

    @Before
    public void setUp() throws AkdemiaBusinessException {
        // Création de l'utilisateur de test avant chaque test
        UserDto user = createUser("zoyim.loti@indyli-services.com", "Paris, France", "aziz", "0398521647", "1234");
        user = this.userService.create(user);
        Assert.assertNotNull(user.getId());
        this.userIdForAllTest = user.getId();
    }

    @Test
    public void testCreateUserId() throws AkdemiaBusinessException {
        // Given
        UserDto user = createUser("cedric.emana@indyli-services.com", "Paris, France", "cedric", "0398521647", "1234");
        // When
        user = this.userService.create(user);
        Assert.assertNotNull(user.getId());
        this.createUserId = user.getId();
    }

    @Test
    public void testFindAllUserWithSuccess() {
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
        Assert.assertEquals("06854263985", userUpdate.getPhone());
    }

    @Test
    public void testDelete() throws AccessDeniedException, AkdemiaBusinessException {
        // Given
        Integer userId = this.userIdForAllTest;
        this.userIdForAllTest = null;
        // When
        this.userService.deleteById(userId);
        UserDto user = this.userService.findById(userId);
        // Then
        Assert.assertNull(user);
    }

    @Test
    public void testGetUserByEmail() throws AkdemiaBusinessException {
        // Given
        String email = "Jacques@example.com";
        // When
        UserDto user = userService.findByEmail(email);
        // Then
        Assert.assertNotNull(user);
        Assert.assertEquals(email, user.getEmail());
    }

    @After
    public void tearDown() throws AkdemiaBusinessException, AccessDeniedException {
        // Supprimer les utilisateurs créés lors des tests
        if (!Objects.isNull(this.userIdForAllTest)) {
            this.userService.deleteById(this.userIdForAllTest);
        }

        if (!Objects.isNull(this.createUserId)) {
            this.userService.deleteById(this.createUserId);
        }
    }

    private UserDto createUser(String email, String address, String login, String phone, String password) throws AkdemiaBusinessException {
        bcryptEncoder = new BCryptPasswordEncoder();
        UserDto user = new UserDto();
        user.setAddress(address);
        user.setEmail(email);
        user.setPhone(phone);
        user.setCreationDate(new Date());
        user.setLogin(login);
        String encryptPassword = bcryptEncoder.encode(password);
        user.setPassword(encryptPassword);
        return user;
    }
}

