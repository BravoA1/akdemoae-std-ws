package af.cmr.indyli.akdemia.business.service;

import af.cmr.indyli.akdemia.business.dto.TrainingDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;

public interface ITrainingService extends IEntityService<TrainingDto>{

	public TrainingDto update(TrainingDto training) throws AkdemiaBusinessException;
}
