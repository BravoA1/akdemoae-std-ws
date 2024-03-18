package af.cmr.indyli.akdemia.business.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import af.cmr.indyli.akdemia.business.dao.ICompanyDAO;
import af.cmr.indyli.akdemia.business.dto.CompanyDto;

public class CompanyDAOImpl extends AbstractEntityDAOImpl<CompanyDto> implements ICompanyDAO {

	@Override
    public void initData() {
        entityInMemory =  new ArrayList<CompanyDto>();
        CompanyDto company1 = new CompanyDto();
        CompanyDto company2 = new CompanyDto();
        CompanyDto company3 = new CompanyDto();
        CompanyDto company4 = new CompanyDto();
        CompanyDto company5 = new CompanyDto();
        
        company1.setName("M2i");
        company1.setActivity("Informatique");
        company1.setId(1);
        company1.setLogin("m2i");
        company1.setPassword(this.getPasswordEncoder().encode("1234"));
        company1.setEmail("m2i@gmail.com");
        company1.setAddress("Paris, France");
        company1.setPhone("06974582");
        company1.setCreationDate(new Date());
        entityInMemory.add(company1);
        
        company2.setName("Capgemini");
        company2.setActivity("Informatique");
        company2.setId(2);
        company2.setLogin("Capgemini");
        company2.setPassword(this.getPasswordEncoder().encode("1234"));
        company2.setEmail("capgemini@gmail.com");
        company2.setAddress("Lyon, France");
        company2.setPhone("067854213");
        company2.setCreationDate(new Date());
        entityInMemory.add(company2);
        
        company3.setName("Nintendo");
        company3.setActivity("Jeux vidéos");
        company3.setId(3);
        company3.setLogin("Nintendo");
        company3.setPassword(this.getPasswordEncoder().encode("1234"));
        company3.setEmail("nintendo@gmail.com");
        company3.setAddress("Paris, France");
        company3.setPhone("06974582");
        company3.setCreationDate(new Date());
        entityInMemory.add(company3);
        
        company4.setName("Casterman");
        company4.setActivity("BD");
        company4.setId(4);
        company4.setLogin("Casterman");
        company4.setPassword(this.getPasswordEncoder().encode("1234"));
        company4.setEmail("Casterman@gmail.com");
        company4.setAddress("Lille, France");
        company4.setPhone("06974582");
        company4.setCreationDate(new Date());
        entityInMemory.add(company4);
        
        company5.setName("AudioTechnica");
        company5.setActivity("Audio");
        company5.setId(5);
        company5.setLogin("AT");
        company5.setPassword(this.getPasswordEncoder().encode("1234"));
        company5.setEmail("AT@gmail.com");
        company5.setAddress("Montpellier, France");
        company5.setPhone("06548721");
        company5.setCreationDate(new Date());
        entityInMemory.add(company5);
    }
	


}
