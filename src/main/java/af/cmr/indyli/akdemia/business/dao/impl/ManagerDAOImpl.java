package af.cmr.indyli.akdemia.business.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import af.cmr.indyli.akdemia.business.dao.IManagerDAO;
import af.cmr.indyli.akdemia.business.dto.ManagerDto;

public class ManagerDAOImpl extends AbstractEntityDAOImpl<ManagerDto> implements IManagerDAO {

	@Override
	public void initData() {
		entityInMemory =  new ArrayList<ManagerDto>();
		ManagerDto manager1 = new ManagerDto();
		ManagerDto manager2 = new ManagerDto();
		ManagerDto manager3 = new ManagerDto();
		ManagerDto manager4 = new ManagerDto();
		ManagerDto manager5 = new ManagerDto();
		
		manager1.setFirstname("Will");
		manager1.setLastname("Smith");
		manager1.setGender("H");
		manager1.setId(1);
		manager1.setLogin("will");
		manager1.setPassword(this.getPasswordEncoder().encode("1234"));
		manager1.setEmail("will@gmail.com");
		manager1.setAddress("Paris, France");
		manager1.setPhone("06974582");
		manager1.setCreationDate(new Date());
		entityInMemory.add(manager1);
		
		manager2.setFirstname("Lady");
		manager2.setLastname("Gaga");
		manager2.setGender("F");
		manager2.setId(2);
		manager2.setLogin("will");
		manager2.setPassword(this.getPasswordEncoder().encode("1234"));
		manager2.setEmail("lady@gmail.com");
		manager2.setAddress("Lyon, France");
		manager2.setPhone("067854213");
		manager2.setCreationDate(new Date());
		entityInMemory.add(manager2);
		
		manager3.setFirstname("Pablo");
		manager3.setLastname("Escobar");
		manager3.setGender("H");
		manager3.setId(3);
		manager3.setLogin("pablo");
		manager3.setPassword(this.getPasswordEncoder().encode("1234"));
		manager3.setEmail("pablo@gmail.com");
		manager3.setAddress("Paris, France");
		manager3.setPhone("06974582");
		manager3.setCreationDate(new Date());
		entityInMemory.add(manager3);
		
		manager4.setFirstname("Franklin");
		manager4.setLastname("Saint");
		manager4.setGender("H");
		manager4.setId(4);
		manager4.setLogin("franklin");
		manager4.setPassword(this.getPasswordEncoder().encode("1234"));
		manager4.setEmail("franklin@gmail.com");
		manager4.setAddress("Lille, France");
		manager4.setPhone("06974582");
		manager4.setCreationDate(new Date());
		entityInMemory.add(manager4);
		
		manager5.setFirstname("Marie");
		manager5.setLastname("Curie");
		manager5.setGender("F");
		manager5.setId(5);
		manager5.setLogin("marie");
		manager5.setPassword(this.getPasswordEncoder().encode("1234"));
		manager5.setEmail("marie@gmail.com");
		manager5.setAddress("Montpellier, France");
		manager5.setPhone("06548721");
		manager5.setCreationDate(new Date());
		entityInMemory.add(manager5);
	}

}
