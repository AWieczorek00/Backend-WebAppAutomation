package pl.backendbscthesis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.backendbscthesis.Entity.Activities;
import pl.backendbscthesis.Repository.ActivitiesRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class ActivitiesService {

    private final ActivitiesRepository activitiesRepository;


    @Autowired
    public ActivitiesService(ActivitiesRepository activitiesRepository) {
        this.activitiesRepository = activitiesRepository;
    }

    public List<Activities> createActivitiesList(List<Activities> activitiesList){
        return activitiesRepository.saveAll(activitiesList);

    }
}
