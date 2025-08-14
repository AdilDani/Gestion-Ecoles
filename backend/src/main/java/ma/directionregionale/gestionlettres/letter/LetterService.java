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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        letter.setSentAt(LocalDateTime.now().format(formatter)); // "2025-08-14 14:30:45"

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


    public List<LetterResponse> getLettersBySchoolId(String schoolId){
        List<LetterResponse> letterResponseList = new ArrayList<>();

        Optional<School> schoolOptional = schoolRepository.findById(schoolId);
        if (schoolOptional.isEmpty()){
            throw new GlobalException("SCHOOL_ID_NOT_FOUND_IN_LETTER_SERVICE","SCHOOL with ID " + schoolId +" DOES_NOT_EXIST");
        }

        School school = schoolOptional.get();

        for(Letter schoolLetter: school.getLetters()){
            letterResponseList.add(commonMapper.letterToLetterResponse(schoolLetter));
        }
        return letterResponseList;

    }

    public LetterResponse getLetterById(String id){
        Optional<Letter> letterOptional = letterRepository.findById(id);
        if (letterOptional.isEmpty()){
            throw new GlobalException("LETTER_ID_NOT_FOUND_IN_LETTER_SERVICE","LETTER with ID " + id +" DOES_NOT_EXIST");
        }
        Letter letter = letterOptional.get();

        return commonMapper.letterToLetterResponse(letter);
    }
}
