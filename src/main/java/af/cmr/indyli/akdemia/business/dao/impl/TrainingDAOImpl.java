package af.cmr.indyli.akdemia.business.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import af.cmr.indyli.akdemia.business.dao.ITrainingDAO;
import af.cmr.indyli.akdemia.business.dto.RequirementDTO;
import af.cmr.indyli.akdemia.business.dto.TrainingDto;

public class TrainingDAOImpl extends AbstractEntityDAOImpl<TrainingDto> implements ITrainingDAO {

	@Override
	public void initData() {

		entityInMemory = new ArrayList<TrainingDto>();
		TrainingDto training1 = new TrainingDto();
		TrainingDto training2 = new TrainingDto();
		TrainingDto training3 = new TrainingDto();
		TrainingDto training4 = new TrainingDto();
		TrainingDto training5 = new TrainingDto();

		training1.setTitle("M2i");
		training1.setDescription("Informatique");
		training1.setId(1);
		training1.setTrainingPrice(100.0);
		training1.setLogo("logo");
		training1.setRequirement(new RequirementDTO());
		training1.setCreationDate(new Date());
		entityInMemory.add(training1);

		training2.setTitle("Capgemini");
		training2.setDescription("Informatique");
		training2.setId(2);
		training2.setTrainingPrice(1000.0);
		training2.setLogo("logo");
		training2.setRequirement(new RequirementDTO());
		training2.setCreationDate(new Date());
		entityInMemory.add(training2);

		training3.setTitle("Nintendo");
		training3.setDescription("Jeux vidéos");
		training3.setId(3);
		training3.setTrainingPrice(10.0);
		training3.setLogo("logo");
		training3.setRequirement(new RequirementDTO());
		training3.setCreationDate(new Date());
		entityInMemory.add(training3);

		training4.setTitle("Casterman");
		training4.setDescription("BD");
		training4.setId(4);
		training4.setTrainingPrice(1000.0);
		training4.setLogo("logo");
		training4.setRequirement(new RequirementDTO());
		training4.setCreationDate(new Date());
		entityInMemory.add(training4);

		training5.setTitle("AudioTechnica");
		training5.setDescription("Audio");
		training5.setId(5);
		training5.setTrainingPrice(100.0);
		training5.setLogo("logo");
		training4.setRequirement(new RequirementDTO());
		training5.setCreationDate(new Date());
		entityInMemory.add(training5);

	}

}
