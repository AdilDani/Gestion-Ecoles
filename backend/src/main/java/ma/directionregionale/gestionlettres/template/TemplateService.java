package ma.directionregionale.gestionlettres.template;

import jakarta.transaction.Transactional;
import ma.directionregionale.gestionlettres.config.GlobalException;
import ma.directionregionale.gestionlettres.dto.TemplateRequest;
import ma.directionregionale.gestionlettres.dto.TemplateResponse;
import ma.directionregionale.gestionlettres.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TemplateService {
    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private TemplateRepository templateRepository;



    public TemplateResponse addNewTemplate(TemplateRequest templateRequest) {
        Template template = new Template();
        template.setName(templateRequest.getName());
        template.setFields(templateRequest.getFields());
        template.setDescription(templateRequest.getDescription());

        Template savedTemplate=templateRepository.save(template);
        return commonMapper.templateToTemplateResponse(savedTemplate);
    }

    public TemplateResponse updateTemplate(TemplateRequest templateRequest) {
        Optional<Template> templateOpt=templateRepository.findById(templateRequest.getId());
        if(templateOpt.isEmpty()){
            throw new GlobalException("TEMPLATE_ID_NOT_FOUND","Template with ID " + templateRequest.getId() +" DOES_NOT_EXIST");
        }
        Template template = templateOpt.get();

        template.setDescription(templateRequest.getDescription());
        template.setName(templateRequest.getName());
        template.setFields(templateRequest.getFields());

        Template savedTemplate = templateRepository.save(template);
        return commonMapper.templateToTemplateResponse(savedTemplate);
    }

    public List<TemplateResponse> getAllTemplates() {
        List<TemplateResponse> templateResponseList = new ArrayList<>();
        for(Template template : templateRepository.findAll()){
            templateResponseList.add(commonMapper.templateToTemplateResponse(template));
        }
        return templateResponseList;
    }

    public TemplateResponse getTemplateById(String id) {
        Optional<Template> templateOpt=templateRepository.findById(id);
        if(templateOpt.isEmpty()){
            throw new GlobalException("TEMPLATE_ID_NOT_FOUND","Template with ID " + id+" DOES_NOT_EXIST");
        }
        return commonMapper.templateToTemplateResponse(templateOpt.get());
    }
}
