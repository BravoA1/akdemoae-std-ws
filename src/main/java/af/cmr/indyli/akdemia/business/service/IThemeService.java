package af.cmr.indyli.akdemia.business.service;

import af.cmr.indyli.akdemia.business.dto.ThemeDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;

public interface IThemeService extends IEntityService<ThemeDto> {

	public ThemeDto update(ThemeDto theme) throws AkdemiaBusinessException;
	
}
