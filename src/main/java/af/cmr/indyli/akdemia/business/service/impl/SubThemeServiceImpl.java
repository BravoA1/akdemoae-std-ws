package af.cmr.indyli.akdemia.business.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import af.cmr.indyli.akdemia.business.dao.IEntityDAO;
import af.cmr.indyli.akdemia.business.dto.SubThemeDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.ISubThemeService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;
import af.cmr.indyli.akdemia.business.dao.impl.SubThemeDAOImpl;
import af.cmr.indyli.akdemia.business.dao.ISubThemeDAO;

@Service(AkdemiaConstantesService.SUBTHEME_SERVICE_KEY)
public class SubThemeServiceImpl extends AbstractEntityServiceImpl<SubThemeDto> implements ISubThemeService {

	private ISubThemeDAO subThemeDAO = new SubThemeDAOImpl();

	@Override
	public IEntityDAO<SubThemeDto> getDAO() {
		return this.subThemeDAO;
	}

	@Override
	public SubThemeDto update(SubThemeDto subTheme) throws AkdemiaBusinessException {
		SubThemeDto existingSubTheme = this.subThemeDAO.findById(subTheme.getId());

		if (subTheme.getDescription() != null) {
			existingSubTheme.setDescription(subTheme.getDescription());
		}

		if (subTheme.getSubThemeTitle() != null) {
			existingSubTheme.setSubThemeTitle(subTheme.getSubThemeTitle());
		}

		if (existingSubTheme.getCreationDate() == null) {
			existingSubTheme.setCreationDate(new Date());
		} else {
			existingSubTheme.setUpdateDate(new Date());
		}
		// existingSubTheme.setCreationDate(new Date());

		return this.subThemeDAO.create(existingSubTheme);
	}
}
