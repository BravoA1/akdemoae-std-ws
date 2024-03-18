package af.cmr.indyli.akdemia.business.service;

import af.cmr.indyli.akdemia.business.dto.SubThemeDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;

public interface ISubThemeService extends IEntityService<SubThemeDto>{

	public SubThemeDto update(SubThemeDto manager) throws AkdemiaBusinessException;
}
