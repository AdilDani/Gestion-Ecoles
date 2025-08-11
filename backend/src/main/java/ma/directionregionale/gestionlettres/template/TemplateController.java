package ma.directionregionale.gestionlettres.template;

import ma.directionregionale.gestionlettres.dto.SchoolResponse;
import ma.directionregionale.gestionlettres.dto.TemplateRequest;
import ma.directionregionale.gestionlettres.dto.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/templates")
public class TemplateController {
    @Autowired
    public TemplateService templateService;

    @PostMapping
    public TemplateResponse addNewTemplate(@RequestBody TemplateRequest templateRequest){
        return templateService.addNewTemplate(templateRequest);
    }

    @PutMapping
    public TemplateResponse updateTemplate(@RequestBody TemplateRequest templateRequest){
        return templateService.updateTemplate(templateRequest);
    }

    @GetMapping("")
        public List<TemplateResponse> getAllTemplates(){
        return templateService.getAllTemplates();
    }

    @GetMapping("/{id}")
        public TemplateResponse getTemplateById(@PathVariable String id){
            return templateService.getTemplateById(id);
    }









}
