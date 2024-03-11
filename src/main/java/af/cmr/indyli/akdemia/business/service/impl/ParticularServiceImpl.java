package af.cmr.indyli.akdemia.business.service.impl;


import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import af.cmr.indyli.akdemia.business.dao.IEntityDAO;
import af.cmr.indyli.akdemia.business.dao.IParticularDAO;
import af.cmr.indyli.akdemia.business.dao.impl.ParticularDAOImpl;
import af.cmr.indyli.akdemia.business.dto.ParticularDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IParticularService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;

@Service(AkdemiaConstantesService.PARTICULAR_SERVICE_KEY)
public class ParticularServiceImpl extends AbstractEntityServiceImpl<ParticularDto> implements IParticularService {

	private IParticularDAO particularDAO = new ParticularDAOImpl();
	
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Override
	public IEntityDAO<ParticularDto> getDAO() {
		// TODO Auto-generated method stub
		return this.particularDAO;
	}
	
	public ParticularServiceImpl(BCryptPasswordEncoder bcryptEncoder) {
		super();
		this.bcryptEncoder = bcryptEncoder;
	}
	
	@Override
	public ParticularDto update(ParticularDto particular) throws AkdemiaBusinessException {
		
		ParticularDto existingParticular  = this.particularDAO.findById(particular.getId());
		
		if(particular.getPassword() != null && !particular.getPassword().isEmpty()) {
			
			existingParticular.setAddress(particular.getAddress());
			existingParticular.setLogin(particular.getLogin());
			existingParticular.setEmail(particular.getEmail());
			existingParticular.setPhone(particular.getPhone());
			existingParticular.setPhoto(particular.getPhoto());
			existingParticular.setUpdateDate(new Date());
			existingParticular.setPassword(bcryptEncoder.encode(particular.getPassword()));
			existingParticular.setFirstname(particular.getFirstname());
			existingParticular.setLastname(particular.getLastname());
			existingParticular.setGender(particular.getGender());
			existingParticular.setActivity(particular.getActivity());
			existingParticular.setBirthDate(particular.getBirthDate());
			existingParticular.setHighestDiploma(particular.getHighestDiploma());
			
			return this.particularDAO.create(existingParticular);
		    
		}else {
			existingParticular.setAddress(particular.getAddress());
			existingParticular.setLogin(particular.getLogin());
			existingParticular.setEmail(particular.getEmail());
			existingParticular.setPhone(particular.getPhone());
			existingParticular.setPhoto(particular.getPhoto());
			existingParticular.setUpdateDate(new Date());
			existingParticular.setFirstname(particular.getFirstname());
			existingParticular.setLastname(particular.getLastname());
			existingParticular.setGender(particular.getGender());
			existingParticular.setActivity(particular.getActivity());
			existingParticular.setBirthDate(particular.getBirthDate());
			existingParticular.setHighestDiploma(particular.getHighestDiploma());
			
			return this.particularDAO.create(existingParticular);
		}
	}
	
}
