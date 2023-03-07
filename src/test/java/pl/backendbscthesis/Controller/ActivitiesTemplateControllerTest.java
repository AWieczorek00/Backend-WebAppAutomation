package pl.backendbscthesis.Controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.backendbscthesis.Entity.template.ActivitiesTemplate;
import pl.backendbscthesis.Service.ActivitiesTemplateService;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class ActivitiesTemplateControllerTest {

    @Mock
    private ActivitiesTemplateService activitiesTemplateService;

    @InjectMocks
    private ActivitiesTemplateController activitiesTemplateController;

    @Test
    void getAllActivities() {
        //given
        List<ActivitiesTemplate> activitiesList = Arrays.asList(
                new ActivitiesTemplate(1L, "Szablon 1"),
                new ActivitiesTemplate(2L, "Szablon 2"));
        given(activitiesTemplateService.findAllActivitiesTemplate()).willReturn(activitiesList);

        // when
        ResponseEntity<List<ActivitiesTemplate>> responseEntity = activitiesTemplateController.getAllActivities();
        List<ActivitiesTemplate> returnedList = responseEntity.getBody();

        // then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(activitiesList.size(), returnedList.size());
        assertThat(activitiesList).isEqualTo(returnedList);
    }

    @Test
    void createActivitiesTemplate() {
        //given
        ActivitiesTemplate activitiesTemplate = new ActivitiesTemplate(1L, "Szablon 1");
        given(activitiesTemplateService.createActivitiesTemplate(activitiesTemplate)).willReturn(activitiesTemplate);

        // when
        ResponseEntity<ActivitiesTemplate> responseEntity = activitiesTemplateController.createActivitiesTemplate(activitiesTemplate);
        ActivitiesTemplate returned = responseEntity.getBody();

        // then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(activitiesTemplate, returned);
    }
}
