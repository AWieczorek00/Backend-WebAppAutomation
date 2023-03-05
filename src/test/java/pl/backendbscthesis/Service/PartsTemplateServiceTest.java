package pl.backendbscthesis.Service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import pl.backendbscthesis.Entity.template.PartsTemplate;
import pl.backendbscthesis.Repository.PartsTemplateRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
class PartsTemplateServiceTest {

    @Mock
    private PartsTemplateRepository partsTemplateRepository;

    @InjectMocks
    private PartsTemplateService partsTemplateService;

    @Test
    public void shouldReturnAllPartsTemplates() {
        // given
        given(partsTemplateRepository.findAll()).willReturn(prepareTestDate());

        // when
        List<PartsTemplate> result = partsTemplateService.findAll();

        // then
        assertThat(result).isEqualTo(prepareTestDate());
    }

    private List<PartsTemplate> prepareTestDate(){
        List<PartsTemplate> partsTemplates = new ArrayList<>();
        partsTemplates.add(new PartsTemplate(1l,"Rurka",2.2f,3.3f));
        partsTemplates.add(new PartsTemplate(1l,"Koło",4.2f,6.3f));
        return partsTemplates;
    }

}
