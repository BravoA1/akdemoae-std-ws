package af.cmr.indyli.akdemia.business.service;

import af.cmr.indyli.akdemia.business.dto.EmployeeDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;

public interface IEmployeeService  extends IEntityService<EmployeeDto>{
	
	public EmployeeDto update(EmployeeDto employee) throws AkdemiaBusinessException;

}
