package af.cmr.indyli.akdemia.business.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import af.cmr.indyli.akdemia.business.dao.IEmployeeDAO;
import af.cmr.indyli.akdemia.business.dto.EmployeeDto;

public class EmployeeDAOImpl extends AbstractEntityDAOImpl<EmployeeDto> implements IEmployeeDAO {

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		entityInMemory =  new ArrayList<EmployeeDto>();
        EmployeeDto employee1 = new EmployeeDto();
        EmployeeDto employee2 = new EmployeeDto();
        EmployeeDto employee3 = new EmployeeDto();
        EmployeeDto employee4 = new EmployeeDto();
        EmployeeDto employee5 = new EmployeeDto();
        
        employee1.setFirstname("Malcolm");
        employee1.setLastname("Nolastname");
        employee1.setGender("H");
        employee1.setId(1);
        employee1.setLogin("malcolm");
        employee1.setPassword(this.getPasswordEncoder().encode("1234"));
        employee1.setEmail("malcolm@gmail.com");
        employee1.setAddress("Paris, France");
        employee1.setPhone("06974582");
        employee1.setHighestDiploma("Doctorat");
        employee1.setCreationDate(new Date());
        entityInMemory.add(employee1);
        
        employee2.setFirstname("Dewey");
        employee2.setLastname("Nolastname");
        employee2.setGender("H");
        employee2.setId(2);
        employee2.setLogin("Dewey");
        employee2.setPassword(this.getPasswordEncoder().encode("1234"));
        employee2.setEmail("dewey@gmail.com");
        employee2.setAddress("Lyon, France");
        employee2.setPhone("067854213");
        employee2.setCreationDate(new Date());
        employee2.setHighestDiploma("Doctorat");
        entityInMemory.add(employee2);
        
        employee3.setFirstname("Lois");
        employee3.setLastname("Nolastname");
        employee3.setGender("F");
        employee3.setId(3);
        employee3.setLogin("Lois");
        employee3.setPassword(this.getPasswordEncoder().encode("1234"));
        employee3.setEmail("lois@gmail.com");
        employee3.setAddress("Paris, France");
        employee3.setPhone("06974582");
        employee3.setCreationDate(new Date());
        employee3.setHighestDiploma("Bac");
        entityInMemory.add(employee3);	
        
        employee4.setFirstname("Reese");
        employee4.setLastname("Nolastname");
        employee4.setGender("H");
        employee4.setId(4);
        employee4.setLogin("Reese");
        employee4.setPassword(this.getPasswordEncoder().encode("1234"));
        employee4.setEmail("Reese@gmail.com");
        employee4.setAddress("Lille, France");
        employee4.setPhone("06974582");
        employee4.setCreationDate(new Date());
        employee4.setHighestDiploma("Brevet");
        entityInMemory.add(employee4);
        
        employee5.setFirstname("Francis");
        employee5.setLastname("Nolastname");
        employee5.setGender("H");
        employee5.setId(5);
        employee5.setLogin("Francis");
        employee5.setPassword(this.getPasswordEncoder().encode("1234"));
        employee5.setEmail("Francis@gmail.com");
        employee5.setAddress("Montpellier, France");
        employee5.setPhone("06548721");
        employee5.setCreationDate(new Date());
        employee5.setHighestDiploma("Brevet");
        entityInMemory.add(employee5);
	}

}
