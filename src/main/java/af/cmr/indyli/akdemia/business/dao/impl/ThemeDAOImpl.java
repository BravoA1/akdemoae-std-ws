package af.cmr.indyli.akdemia.business.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import af.cmr.indyli.akdemia.business.dao.IThemeDAO;
import af.cmr.indyli.akdemia.business.dto.ThemeDto;
public class ThemeDAOImpl extends AbstractEntityDAOImpl<ThemeDto> implements IThemeDAO{

	@Override
	public void initData() {

		entityInMemory =  new ArrayList<ThemeDto>();
		
		ThemeDto theme1 = new ThemeDto();
		ThemeDto theme2 = new ThemeDto();
		ThemeDto theme3 = new ThemeDto();
		ThemeDto theme4 = new ThemeDto();
		ThemeDto theme5 = new ThemeDto();
		
		theme1.setId(1);
		theme1.setThemeTitle("theme1");
		theme1.setDescription("description1");
		theme1.setCreationDate(new Date());
		entityInMemory.add(theme1);
		
		theme2.setId(2);
		theme2.setThemeTitle("theme2");
		theme2.setDescription("description2");
		theme2.setCreationDate(new Date());
		entityInMemory.add(theme2);
		
		theme3.setId(3);
		theme3.setThemeTitle("theme3");
		theme3.setDescription("description3");
		theme3.setCreationDate(new Date());
		entityInMemory.add(theme3);
		
		theme4.setId(4);
		theme4.setThemeTitle("theme4");
		theme4.setDescription("description4");
		theme4.setCreationDate(new Date());
		entityInMemory.add(theme4);
		
		theme5.setId(5);
		theme5.setThemeTitle("theme5");
		theme5.setDescription("description5");
		theme5.setCreationDate(new Date());
		entityInMemory.add(theme5);
		
	}

}
