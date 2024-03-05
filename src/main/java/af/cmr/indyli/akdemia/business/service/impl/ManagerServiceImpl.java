package af.cmr.indyli.akdemia.business.service.impl;

import org.springframework.stereotype.Service;

import af.cmr.indyli.akdemia.business.dao.IEntityDAO;
import af.cmr.indyli.akdemia.business.dao.IManagerDAO;
import af.cmr.indyli.akdemia.business.dao.impl.ManagerDAOImpl;
import af.cmr.indyli.akdemia.business.dto.ManagerDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IManagerService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;

@Service(AkdemiaConstantesService.MANAGER_SERVICE_KEY)
public class ManagerServiceImpl extends AbstractEntityServiceImpl<ManagerDto> implements IManagerService {

private IManagerDAO managerDAO = new ManagerDAOImpl();
	
	@Override
	public IEntityDAO<ManagerDto> getDAO() {
		// TODO Auto-generated method stub
		return this.managerDAO;
	}
	
	
	@Override
	public ManagerDto update(ManagerDto manager) throws AkdemiaBusinessException {
		
		ManagerDto existingManager  = this.managerDAO.findById(manager.getId());
		existingManager.setFirstname(manager.getFirstname());
		existingManager.setLastname(manager.getLastname());
		existingManager.setGender(manager.getGender());
		
	  	return this.managerDAO.create(existingManager);
	}
	
}
