package af.cmr.indyli.akdemia.business.service;

import af.cmr.indyli.akdemia.business.dto.RequirementDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;

public interface IRequirementService extends IEntityService<RequirementDTO> {

	public RequirementDTO update(RequirementDTO requirement) throws AkdemiaBusinessException;
	
}
