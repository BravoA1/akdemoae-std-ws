package af.cmr.indyli.akdemia.business.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import af.cmr.indyli.akdemia.business.dao.IParticularDAO;
import af.cmr.indyli.akdemia.business.dto.ParticularDto;

public class ParticularDAOImpl extends AbstractEntityDAOImpl<ParticularDto> implements IParticularDAO {

	@Override
	public void initData() {
		entityInMemory =  new ArrayList<ParticularDto>();
		
		ParticularDto particular1 = new ParticularDto();
		ParticularDto particular2 = new ParticularDto();
		ParticularDto particular3 = new ParticularDto();
		ParticularDto particular4 = new ParticularDto();
		ParticularDto particular5 = new ParticularDto();
		
		particular1.setFirstname("Stan");
		particular1.setLastname("Smith");
		particular1.setGender("H");
		particular1.setActivity("architecte");
		particular1.setHighestDiploma("License");
		particular1.setBirthDate(new Date());
		particular1.setId(1);
		particular1.setLogin("smith");
		particular1.setPassword(this.getPasswordEncoder().encode("1234"));
		particular1.setEmail("stan@gmail.com");
		particular1.setAddress("Nantes, France");
		particular1.setPhone("069985785");
		particular1.setCreationDate(new Date());
		entityInMemory.add(particular1);
		
		particular2.setFirstname("Georges");
		particular2.setLastname("Orwell");
		particular2.setGender("H");
		particular2.setActivity("Ecrivain");
		particular2.setHighestDiploma("Master");
		particular2.setBirthDate(new Date());
		particular2.setId(2);
		particular2.setLogin("georges");
		particular2.setPassword(this.getPasswordEncoder().encode("1234"));
		particular2.setEmail("georges@gmail.com");
		particular2.setAddress("Paris, France");
		particular2.setPhone("06974582");
		particular2.setCreationDate(new Date());
		entityInMemory.add(particular2);
		
		particular3.setFirstname("Yann");
		particular3.setLastname("Barthès");
		particular3.setGender("H");
		particular3.setActivity("Journaliste");
		particular3.setHighestDiploma("Master");
		particular3.setBirthDate(new Date());
		particular3.setId(3);
		particular3.setLogin("yann");
		particular3.setPassword(this.getPasswordEncoder().encode("1234"));
		particular3.setEmail("yann@gmail.com");
		particular3.setAddress("Paris, France");
		particular3.setPhone("054879658");
		particular3.setCreationDate(new Date());
		entityInMemory.add(particular3);
		
		particular4.setFirstname("Sandrine");
		particular4.setLastname("Rousseau");
		particular4.setGender("F");
		particular4.setActivity("Député");
		particular4.setHighestDiploma("Master");
		particular4.setBirthDate(new Date());
		particular4.setId(4);
		particular4.setLogin("sandrine");
		particular4.setPassword(this.getPasswordEncoder().encode("1234"));
		particular4.setEmail("sandrine@gmail.com");
		particular4.setAddress("Paris, France");
		particular4.setPhone("06974582");
		particular4.setCreationDate(new Date());
		entityInMemory.add(particular4);
		
		particular5.setFirstname("Marlene");
		particular5.setLastname("Dietrich");
		particular5.setGender("F");
		particular5.setActivity("Actrice");
		particular5.setHighestDiploma("Master");
		particular5.setBirthDate(new Date());
		particular5.setId(5);
		particular5.setLogin("marlene");
		particular5.setPassword(this.getPasswordEncoder().encode("1234"));
		particular5.setEmail("marlene@gmail.com");
		particular5.setAddress("Marseille, France");
		particular5.setPhone("0698745236");
		particular5.setCreationDate(new Date());
		entityInMemory.add(particular5);
	}

}
