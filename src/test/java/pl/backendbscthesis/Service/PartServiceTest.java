package pl.backendbscthesis.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.backendbscthesis.Entity.Part;
import pl.backendbscthesis.Repository.PartRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class PartServiceTest {

    @Mock
    private PartRepository partRepository;

    @InjectMocks
    private PartService partService;

    @Test
    void createAllParts() {
        //given
        List<Part> partList = new ArrayList<>();
        partList.add(new Part(1L, "Koło", 3.4f, 12f, 1));
        partList.add(new Part(1L, "gumka", 1.4f, 3f, 1));
        given(partRepository.saveAll(partList)).willReturn(partList);

        //when
        List<Part> allParts = partService.createAllParts(partList);

        //then
        Assertions.assertEquals(partList, allParts);

    }

    @Test
    void updatePartList() {
        List<Part> partList = new ArrayList<>();
        partList.add(new Part(1L, "Koło", 3.4f, 12f, 1));
        partList.add(new Part(1L, "gumka", 1.4f, 3f, 1));
        List<Part> newList = new ArrayList<>();
        newList.add(new Part(1L, "Chleb", 3.4f, 12f, 1));
        newList.add(new Part(1L, "Ołówek", 1.4f, 3f, 1));
        partRepository.deleteAll(partList);
        given(partRepository.saveAll(newList)).willReturn(newList);


        List<Part> result = partService.updatePartList(newList, partList);


        Assertions.assertEquals(newList, result);

    }
}
