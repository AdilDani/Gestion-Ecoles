package ma.directionregionale.gestionlettres.letter;

import jakarta.transaction.Transactional;
import ma.directionregionale.gestionlettres.config.GlobalException;
import ma.directionregionale.gestionlettres.dto.LetterRequest;
import ma.directionregionale.gestionlettres.dto.LetterResponse;
import ma.directionregionale.gestionlettres.dto.SchoolResponse;
import ma.directionregionale.gestionlettres.mapper.CommonMapper;
import ma.directionregionale.gestionlettres.school.School;
import ma.directionregionale.gestionlettres.school.SchoolRepository;
import ma.directionregionale.gestionlettres.template.TemplateRepository;
import ma.directionregionale.gestionlettres.template.Template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class LetterService {
    @Autowired
    private LetterRepository letterRepository;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private SchoolRepository schoolRepository;

    public LetterResponse addNewLetter(LetterRequest letterRequest) {
        Letter letter = new Letter();

        letter.setDeadline(letterRequest.getDeadline());
        letter.setUrgency(letterRequest.getUrgency());
        Optional<Template> templateOptional =
                templateRepository.findById(letterRequest.getTemplateId());
        if (templateOptional.isEmpty()){
            throw new GlobalException("TEMPLATE_ID_NOT_FOUND_IN_LETTER_SERVICE","Template with ID " + letterRequest.getTemplateId() +" DOES_NOT_EXIST");
        }
        letter.setTemplate(templateOptional.get());

        for(String currentSchoolId : letterRequest.getSchoolIds()){
            Optional<School> schoolOptional = schoolRepository.findById(currentSchoolId);
            if(schoolOptional.isEmpty()){
                throw new GlobalException("SCHOOL_ID_NOT_FOUND_IN_LETTER_SERVICE","SCHOOL with ID " + currentSchoolId +" DOES_NOT_EXIST");
            }
            letter.getSchools().add(schoolOptional.get());
        }

        Letter savedLetter = letterRepository.save(letter);


        LetterResponse letterResponse = new LetterResponse();
        return commonMapper.letterToLetterResponse(savedLetter);
    }
}
