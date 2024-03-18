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

import af.cmr.indyli.akdemia.business.dto.EmployeeDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IEmployeeService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;
import af.cmr.indyli.akdemia.ws.boot.AkdemiaStdWsApplication;
import jakarta.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AkdemiaStdWsApplication.class)
public class EmployeeServiceTest {

    @Resource(name = AkdemiaConstantesService.EMPLOYEE_SERVICE_KEY)
    private IEmployeeService employeeService;

    private Integer employeeIdForAllTest;
    private Integer createEmployeeId;

    private BCryptPasswordEncoder bcryptEncoder;

    @Before
    public void setUp() throws AkdemiaBusinessException {
        // Création de l'utilisateur de test avant chaque test
        EmployeeDto employee = createEmployee("zoyim.loti@indyli-services.com", "Paris, France", "aziz", "0398521647", "1234");
        employee = this.employeeService.create(employee);
        Assert.assertNotNull(employee.getId());
        this.employeeIdForAllTest = employee.getId();
    }

    @Test
    public void testCreateEmployeeId() throws AkdemiaBusinessException {
        // Given
        EmployeeDto employee = createEmployee("cedric.emana@indyli-services.com", "Paris, France", "cedric", "0398521647", "1234");
        // When
        employee = this.employeeService.create(employee);
        Assert.assertNotNull(employee.getId());
        this.createEmployeeId = employee.getId();
    }
    
    @Test
    public void testFindAllEmployeeWithSuccess() {
        // When
        List<EmployeeDto> employees = this.employeeService.findAll();
        // Then
        Assert.assertTrue(employees.size() > 0);
    }
    
    @Test
    public void testFindByIdWithSuccess() throws AkdemiaBusinessException {
        // Given
        Integer employeeId = this.employeeIdForAllTest;
        // When
        EmployeeDto employee = this.employeeService.findById(employeeId);
        // Then
        Assert.assertTrue(employee.getId() == employeeId);
    }

    @Test
    public void testUpdateEmployee() throws AccessDeniedException, AkdemiaBusinessException {
        // Given
        EmployeeDto employee = this.employeeService.findById(this.employeeIdForAllTest);
        employee.setPhone("06854263985");
        // When
        this.employeeService.update(employee);
        EmployeeDto employeeUpdate = this.employeeService.findById(this.employeeIdForAllTest);
        // Then
        Assert.assertEquals("06854263985", employeeUpdate.getPhone());
    }

    @Test
    public void testDelete() throws AccessDeniedException, AkdemiaBusinessException {
        // Given
        Integer employeeId = this.employeeIdForAllTest;
        this.employeeIdForAllTest = null;
        // When
        this.employeeService.deleteById(employeeId);
        EmployeeDto employee = this.employeeService.findById(employeeId);
        // Then
        Assert.assertNull(employee);
    }

    @After
    public void tearDown() throws AkdemiaBusinessException, AccessDeniedException {
        // Supprimer les utilisateurs créés lors des tests
        if (!Objects.isNull(this.employeeIdForAllTest)) {
            this.employeeService.deleteById(this.employeeIdForAllTest);
        }

        if (!Objects.isNull(this.createEmployeeId)) {
            this.employeeService.deleteById(this.createEmployeeId);
        }
    }

    private EmployeeDto createEmployee(String email, String address, String login, String phone, String password) throws AkdemiaBusinessException {
        bcryptEncoder = new BCryptPasswordEncoder();
        EmployeeDto employee = new EmployeeDto();
        employee.setAddress(address);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setCreationDate(new Date());
        employee.setLogin(login);
        String encryptPassword = bcryptEncoder.encode(password);
        employee.setPassword(encryptPassword);
        return employee;
    }
}

