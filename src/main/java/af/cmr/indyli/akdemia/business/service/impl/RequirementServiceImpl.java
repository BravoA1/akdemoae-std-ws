package af.cmr.indyli.akdemia.business.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import af.cmr.indyli.akdemia.business.dao.IEntityDAO;
import af.cmr.indyli.akdemia.business.dao.IRequirementDAO;
import af.cmr.indyli.akdemia.business.dao.impl.RequirementDAOImpl;
import af.cmr.indyli.akdemia.business.dto.RequirementDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IRequirementService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;

@Service(AkdemiaConstantesService.REQUIREMENT_SERVICE_KEY)
public class RequirementServiceImpl extends AbstractEntityServiceImpl<RequirementDTO> implements IRequirementService {

	private IRequirementDAO requirementDAO = new RequirementDAOImpl();
	
	@Override
	public IEntityDAO<RequirementDTO> getDAO() {
		return this.requirementDAO;
	}
	
	@Override
	public RequirementDTO update(RequirementDTO requirement) throws AkdemiaBusinessException {
		
		RequirementDTO existingRequirement  = this.requirementDAO.findById(requirement.getId());
		existingRequirement.setLink(requirement.getLink());
		existingRequirement.setName(requirement.getName());
		existingRequirement.setDescription(requirement.getDescription());
		existingRequirement.setUpdateDate(new Date());
		existingRequirement.setCreationDate(new Date());
		
	  	return this.requirementDAO.create(existingRequirement);
	}
	
}
