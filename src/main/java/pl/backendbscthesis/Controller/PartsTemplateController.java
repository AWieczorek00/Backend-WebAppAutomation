package pl.backendbscthesis.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.backendbscthesis.Entity.template.PartsTemplate;
import pl.backendbscthesis.Service.PartsTemplateService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/partsTemplate")
public class PartsTemplateController {

    private final PartsTemplateService partsTemplateService;

    @Autowired
    public PartsTemplateController(PartsTemplateService partsTemplateService) {
        this.partsTemplateService = partsTemplateService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PartsTemplate>> getAllPartsTemplate() {
        List<PartsTemplate> partsTemplateList = partsTemplateService.findAll();
        return new ResponseEntity<>(partsTemplateList, HttpStatus.OK);
    }
}
