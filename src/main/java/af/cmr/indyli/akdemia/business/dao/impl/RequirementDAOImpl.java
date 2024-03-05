package af.cmr.indyli.akdemia.business.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import af.cmr.indyli.akdemia.business.dao.IRequirementDAO;
import af.cmr.indyli.akdemia.business.dto.RequirementDTO;

public class RequirementDAOImpl extends AbstractEntityDAOImpl<RequirementDTO> implements IRequirementDAO {

	@Override
	public void initData() {
		entityInMemory =  new ArrayList<RequirementDTO>();
		
		RequirementDTO requirement1 = new RequirementDTO();
		RequirementDTO requirement2 = new RequirementDTO();
		RequirementDTO requirement3 = new RequirementDTO();
		RequirementDTO requirement4 = new RequirementDTO();
		RequirementDTO requirement5 = new RequirementDTO();
		
		requirement1.setId(1);
		requirement1.setName("requirement1");
		requirement1.setDescription("description1");
		requirement1.setLink("link1");
		requirement1.setCreationDate(new Date());
		entityInMemory.add(requirement1);
		
		requirement2.setId(2);
		requirement2.setName("requirement2");
		requirement2.setDescription("description2");
		requirement2.setLink("link2");
		requirement2.setCreationDate(new Date());
		entityInMemory.add(requirement2);
		
		requirement3.setId(3);
		requirement3.setName("requirement3");
		requirement3.setDescription("description3");
		requirement3.setLink("link3");
		requirement3.setCreationDate(new Date());
		entityInMemory.add(requirement3);
		
		requirement4.setId(4);
		requirement4.setName("requirement4");
		requirement4.setDescription("description4");
		requirement4.setLink("link4");
		requirement4.setCreationDate(new Date());
		entityInMemory.add(requirement4);
		
		requirement5.setId(5);
		requirement5.setName("requirement5");
		requirement5.setDescription("description5");
		requirement5.setLink("link5");
		requirement5.setCreationDate(new Date());
		entityInMemory.add(requirement5);
	}

}
