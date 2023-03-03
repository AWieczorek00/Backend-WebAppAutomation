package pl.backendbscthesis.Service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.backendbscthesis.Entity.template.PartsTemplate;
import pl.backendbscthesis.Repository.PartsTemplateRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class PartsTemplateServiceTest {

    @Mock
    private PartsTemplateRepository partsTemplateRepository;

    @InjectMocks
    private PartsTemplateService partsTemplateService;

    @Test
    public void shouldReturnAllPartsTemplates() {
        // given
        List<PartsTemplate> partsTemplates = new ArrayList<>();
        partsTemplates.add(new PartsTemplate(1l,"Rurka",2.2f,3.3f));
        partsTemplates.add(new PartsTemplate(1l,"Koło",4.2f,6.3f));
        when(partsTemplateRepository.findAll()).thenReturn(partsTemplates);

        // when
        List<PartsTemplate> result = partsTemplateService.findAll();

        // then
        assertThat(result).isEqualTo(partsTemplates);
    }

}
