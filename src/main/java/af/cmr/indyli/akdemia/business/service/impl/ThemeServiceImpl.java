package af.cmr.indyli.akdemia.business.service.impl;

import java.util.Date;
import org.springframework.stereotype.Service;

import af.cmr.indyli.akdemia.business.dao.IEntityDAO;
import af.cmr.indyli.akdemia.business.dao.IThemeDAO;
import af.cmr.indyli.akdemia.business.dao.impl.ThemeDAOImpl;
import af.cmr.indyli.akdemia.business.dto.ThemeDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IThemeService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;

@Service(AkdemiaConstantesService.THEME_SERVICE_KEY)
public class ThemeServiceImpl extends AbstractEntityServiceImpl<ThemeDto> implements IThemeService {

	private IThemeDAO themeDAO = new ThemeDAOImpl();
	
	@Override
	public IEntityDAO<ThemeDto> getDAO() {
		return this.themeDAO;
	}
	
	
	@Override
	public ThemeDto update(ThemeDto theme) throws AkdemiaBusinessException {
		
		ThemeDto existingTheme  = this.themeDAO.findById(theme.getId());
		existingTheme.setThemeTitle(theme.getThemeTitle());
		existingTheme.setDescription(theme.getDescription());
		existingTheme.setUpdateDate(new Date());
		existingTheme.setCreationDate(new Date());
		
	  	return this.themeDAO.create(existingTheme);
	}
	
}
