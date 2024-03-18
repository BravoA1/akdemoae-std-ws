package af.cmr.indyli.akdemia.business.dao.impl;


import java.util.ArrayList;
import java.util.Date;

import af.cmr.indyli.akdemia.business.dao.ISubThemeDAO;
import af.cmr.indyli.akdemia.business.dto.SubThemeDto;

public class SubThemeDAOImpl extends AbstractEntityDAOImpl<SubThemeDto> implements ISubThemeDAO {

	@Override
	public void initData() {
		// TODO Auto-generated method stub

		 entityInMemory =  new ArrayList<SubThemeDto>();
	        
	        SubThemeDto subTheme1 = new SubThemeDto();
	        SubThemeDto subTheme2 = new SubThemeDto();
	        SubThemeDto subTheme3 = new SubThemeDto();
	        SubThemeDto subTheme4 = new SubThemeDto();
	        SubThemeDto subTheme5 = new SubThemeDto();
	        
	        subTheme1.setId(1);
	        subTheme1.setSubThemeTitle("subTheme1");
	        subTheme1.setDescription("description1");
	        subTheme1.setCreationDate(new Date());
	        entityInMemory.add(subTheme1);
	        
	        subTheme2.setId(2);
	        subTheme2.setSubThemeTitle("subTheme2");
	        subTheme2.setDescription("description2");
	        subTheme2.setCreationDate(new Date());
	        entityInMemory.add(subTheme2);
	        
	        subTheme3.setId(3);
	        subTheme3.setSubThemeTitle("subTheme3");
	        subTheme3.setDescription("description3");
	        subTheme3.setCreationDate(new Date());
	        entityInMemory.add(subTheme3);
	        
	        subTheme4.setId(4);
	        subTheme4.setSubThemeTitle("subTheme4");
	        subTheme4.setDescription("description4");
	        subTheme4.setCreationDate(new Date());
	        entityInMemory.add(subTheme4);
	        
	        subTheme5.setId(5);
	        subTheme5.setSubThemeTitle("subTheme5");
	        subTheme5.setDescription("description5");
	        subTheme5.setCreationDate(new Date());
	        entityInMemory.add(subTheme5);
		
	}


}
