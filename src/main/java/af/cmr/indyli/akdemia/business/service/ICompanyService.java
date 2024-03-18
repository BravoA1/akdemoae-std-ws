package af.cmr.indyli.akdemia.business.service;

import af.cmr.indyli.akdemia.business.dto.CompanyDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;

public interface ICompanyService extends IEntityService<CompanyDto>{

	public CompanyDto update(CompanyDto company) throws AkdemiaBusinessException;
}
