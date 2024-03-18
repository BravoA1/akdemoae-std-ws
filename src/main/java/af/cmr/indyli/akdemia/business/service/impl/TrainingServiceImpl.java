package af.cmr.indyli.akdemia.business.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import af.cmr.indyli.akdemia.business.dao.IEntityDAO;
import af.cmr.indyli.akdemia.business.dao.ITrainingDAO;
import af.cmr.indyli.akdemia.business.dao.impl.TrainingDAOImpl;
import af.cmr.indyli.akdemia.business.dto.TrainingDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.ITrainingService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;

@Service(AkdemiaConstantesService.TRAINING_SERVICE_KEY)
public class TrainingServiceImpl extends AbstractEntityServiceImpl<TrainingDto> implements ITrainingService {
	private ITrainingDAO trainingDAO = new TrainingDAOImpl();

	@Override
	public IEntityDAO<TrainingDto> getDAO() {
		// TODO Auto-generated method stub
		return this.trainingDAO;
	}

	@Override
	public TrainingDto update(TrainingDto training) throws AkdemiaBusinessException {
		TrainingDto existingTraining = this.trainingDAO.findById(training.getId());

		if (training.getTitle() != null)
			existingTraining.setTitle(training.getTitle());
		if (training.getDescription() != null)
			existingTraining.setDescription(training.getDescription());
		if (training.getTrainingPrice() != null)
			existingTraining.setTrainingPrice(training.getTrainingPrice());
		if (training.getLogo() != null)
			existingTraining.setLogo(training.getLogo());
		existingTraining.setUpdateDate(new Date());
		if (training.getCreationDate() != null)
			existingTraining.setCreationDate(new Date());
		if (training.getRequirement() != null)
			existingTraining.setRequirement(training.getRequirement());

		return this.trainingDAO.create(existingTraining);
	}

}