package af.cmr.indyli.akdemia.business.service;

import af.cmr.indyli.akdemia.business.dto.ParticularDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;

public interface IParticularService extends IEntityService<ParticularDto> {

	public ParticularDto update(ParticularDto particular) throws AkdemiaBusinessException;
	
}
