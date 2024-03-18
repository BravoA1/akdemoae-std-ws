package af.cmr.indyli.akdemia.business.service.test;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import af.cmr.indyli.akdemia.business.dto.TrainingDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.ITrainingService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;
import af.cmr.indyli.akdemia.ws.boot.AkdemiaStdWsApplication;
import jakarta.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AkdemiaStdWsApplication.class)
public class TrainingServiceTest {

    @Resource(name = AkdemiaConstantesService.TRAINING_SERVICE_KEY)
    private ITrainingService trainingService;

    private Integer trainingIdForAllTest;
    private Integer trainingIdCreateTest;

    @Before
    public void prepareAllEntityBefore() throws AkdemiaBusinessException {
        // Création du thème
        TrainingDto training = createtraining("NEW Training", "Voici la description.");
        training = this.trainingService.create(training);
        Assert.assertNotNull(training.getId());
        this.trainingIdForAllTest = training.getId();
    }

    @Test
    public void testCreatetrainingWithSuccess() throws AkdemiaBusinessException {
        // Given
        TrainingDto training = createtraining("NEW Training", "Voici la description.");
        // When
        training = this.trainingService.create(training);
        Assert.assertNotNull(training.getId());
        this.trainingIdCreateTest = training.getId();
    }

    @Test
    public void testFindAlltrainingsWithSuccess() {
        // When
        List<TrainingDto> trainings = this.trainingService.findAll();
        // Then
        Assert.assertTrue(trainings.size() > 0);
    }

    @Test
    public void testFindByIdWithSuccess() throws AkdemiaBusinessException {
        // Given
        Integer trainingId = this.trainingIdForAllTest;
        // When
        TrainingDto training = this.trainingService.findById(trainingId);
        // Then
        Assert.assertNotNull(training);
    }

    @Test
    public void testDeletetrainingWithSuccess() throws AccessDeniedException, AkdemiaBusinessException {
        // Given
        Integer trainingId = this.trainingIdForAllTest;
        // When
        this.trainingService.deleteById(trainingId);
        // Then
        TrainingDto training = this.trainingService.findById(trainingId);
        Assert.assertNull(training);
    }

    @Test
    public void testUpdatetraining() throws AccessDeniedException, AkdemiaBusinessException {
        // Given
        TrainingDto training = this.trainingService.findById(this.trainingIdForAllTest);
        training.setTitle("NEW Title 2");
        // When
        this.trainingService.update(training);
        TrainingDto trainingUpdate = this.trainingService.findById(this.trainingIdForAllTest);
        // Then
        Assert.assertEquals("NEW Title 2", trainingUpdate.getTitle());
    }

    @After
    public void deleteAllEntityAfter() throws AkdemiaBusinessException, AccessDeniedException {
        if (!Objects.isNull(this.trainingIdForAllTest)) {
            this.trainingService.deleteById(this.trainingIdForAllTest);
        }
        if (!Objects.isNull(this.trainingIdCreateTest)) {
            this.trainingService.deleteById(this.trainingIdCreateTest);
        }
    }

    private TrainingDto createtraining(String title, String description) throws AkdemiaBusinessException {
        TrainingDto training = new TrainingDto();
        training.setTitle(title);
        training.setDescription(description);
        training.setCreationDate(new Date());
        return training;
    }
}