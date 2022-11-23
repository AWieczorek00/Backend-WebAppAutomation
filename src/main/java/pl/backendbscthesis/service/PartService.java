package pl.backendbscthesis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.backendbscthesis.Entity.Part;
import pl.backendbscthesis.Repository.PartRepository;

import java.util.List;

@Service
public class PartService {

    private final PartRepository partRepository;

    @Autowired
    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public List<Part> createAllParts(List<Part> partList){
        return partRepository.saveAll(partList);
    }

    public void deletePart(Part part){
        partRepository.delete(part);
    }

    @Transactional
    public List<Part> updatePartList(List<Part> newPartList, List<Part> oldPartList) {
        partRepository.deleteAll(oldPartList);
        return partRepository.saveAll(newPartList);
    }
}
