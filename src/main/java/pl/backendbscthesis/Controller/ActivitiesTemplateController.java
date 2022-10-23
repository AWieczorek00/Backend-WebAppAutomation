package pl.backendbscthesis.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.backendbscthesis.Entity.template.ActivitiesTemplate;
import pl.backendbscthesis.service.ActivitiesTemplateService;

import java.util.List;

@RestController
@RequestMapping("/activitiesTemplate")
public class ActivitiesTemplateController {

    private final ActivitiesTemplateService activitiesTemplateService;

    @Autowired
    public ActivitiesTemplateController(ActivitiesTemplateService activitiesTemplateService) {
        this.activitiesTemplateService = activitiesTemplateService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ActivitiesTemplate>> getAllActivities(){
        List<ActivitiesTemplate> activitiesList = activitiesTemplateService.findAllActivitiesTemplate();
        return new ResponseEntity<>(activitiesList, HttpStatus.OK);
    };

    @PostMapping("/add")
    public ResponseEntity<ActivitiesTemplate> postActivities(@RequestBody ActivitiesTemplate activitiesTemplateBody){
        ActivitiesTemplate activities = activitiesTemplateService.createActivitiesTemplate(activitiesTemplateBody);
        return new ResponseEntity<>(activities, HttpStatus.CREATED);
    }
}
