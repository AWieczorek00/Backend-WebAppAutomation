package pl.backendbscthesis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.backendbscthesis.Entity.Activities;
import pl.backendbscthesis.Entity.template.ActivitiesTemplate;
import pl.backendbscthesis.Repository.ActivitiesTemplateRepository;

import java.util.List;

@Service
public class ActivitiesTemplateService {

   private final ActivitiesTemplateRepository activitiesTemplateRepository;

   @Autowired
    public ActivitiesTemplateService(ActivitiesTemplateRepository activitiesTemplateRepository) {
        this.activitiesTemplateRepository = activitiesTemplateRepository;
    }

    public List<ActivitiesTemplate> findAllActivitiesTemplate(){
        return activitiesTemplateRepository.findAll();
    }

    public ActivitiesTemplate createActivitiesTemplate(ActivitiesTemplate activitiesTemplate){
        return activitiesTemplateRepository.save(activitiesTemplate);
    }



}
