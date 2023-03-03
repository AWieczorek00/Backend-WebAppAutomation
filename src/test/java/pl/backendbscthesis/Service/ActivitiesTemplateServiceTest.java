package pl.backendbscthesis.Service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.backendbscthesis.Entity.template.ActivitiesTemplate;
import pl.backendbscthesis.Repository.ActivitiesTemplateRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class ActivitiesTemplateServiceTest {

    @Mock
    private ActivitiesTemplateRepository activitiesTemplateRepository;

    @InjectMocks
    private ActivitiesTemplateService activitiesTemplateService;


    @Test
    void createActivitiesTemplate() {
        //given
        ActivitiesTemplate activitiesTemplate = new ActivitiesTemplate("Activity 1");
        when(activitiesTemplateRepository.save(activitiesTemplate)).thenReturn(activitiesTemplate);

        // when
        ActivitiesTemplate savedActivitiesTemplate = activitiesTemplateService.createActivitiesTemplate(activitiesTemplate);

        // then
        assertThat(savedActivitiesTemplate).isNotNull();
        assertThat(savedActivitiesTemplate.getName()).isEqualTo(activitiesTemplate.getName());
    }

    @Test
    public void shouldReturnAllActivitiesTemplates() {
        // given
        List<ActivitiesTemplate> activitiesTemplates = Arrays.asList(
                new ActivitiesTemplate(10L, "Activity 1"),
                new ActivitiesTemplate(20L, "Activity 2")
//                new ActivitiesTemplate(30L, "Activity 3")
        );
        when(activitiesTemplateRepository.findAll()).thenReturn(activitiesTemplates);

        // when
        List<ActivitiesTemplate> result = activitiesTemplateService.findAllActivitiesTemplate();

        // then
        assertEquals(activitiesTemplates.size(), result.size());
        assertEquals(activitiesTemplates.get(0).getName(), result.get(0).getName());
        assertEquals(activitiesTemplates.get(0).getId(), result.get(0).getId());
        assertEquals(activitiesTemplates.get(1).getName(), result.get(1).getName());
        assertEquals(activitiesTemplates.get(1).getId(), result.get(1).getId());

    }

    @Test
    public void shouldReturnEmptyListWhenNoActivitiesTemplatesFound() {
        // given
        when(activitiesTemplateRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        List<ActivitiesTemplate> result = activitiesTemplateService.findAllActivitiesTemplate();

        // then
        assertTrue(result.isEmpty());
    }
}
