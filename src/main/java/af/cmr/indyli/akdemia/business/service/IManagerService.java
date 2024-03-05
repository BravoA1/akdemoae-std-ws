package af.cmr.indyli.akdemia.business.service;

import af.cmr.indyli.akdemia.business.dto.ManagerDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;

public interface IManagerService extends IEntityService<ManagerDto> {

	public ManagerDto update(ManagerDto manager) throws AkdemiaBusinessException;
	
}
