package ma.directionregionale.gestionlettres.response;


import jakarta.transaction.Transactional;
import ma.directionregionale.gestionlettres.config.GlobalException;
import ma.directionregionale.gestionlettres.dto.RespondersNonRespondersList;
import ma.directionregionale.gestionlettres.dto.ResponseRequest;
import ma.directionregionale.gestionlettres.dto.ResponseResponse;
import ma.directionregionale.gestionlettres.letter.Letter;
import ma.directionregionale.gestionlettres.letter.LetterRepository;
import ma.directionregionale.gestionlettres.mapper.CommonMapper;
import ma.directionregionale.gestionlettres.school.School;
import ma.directionregionale.gestionlettres.school.SchoolRepository;
import ma.directionregionale.gestionlettres.template.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResponseService {
    @Autowired
    private LetterRepository letterRepository;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private ResponseRepository responseRepository;











    public ResponseResponse createNewResponse(ResponseRequest responseRequest){
        Response response = new Response();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        response.setSentAt(LocalDateTime.now().format(formatter)); // "2025-08-14 14:30:45"

        Optional<Letter> letterOptional = letterRepository.findById(responseRequest.getLetterId());
        if (letterOptional.isEmpty()){
            throw new GlobalException("LETTER_ID_NOT_FOUND_IN_RESPONSE_SERVICE","LETTER with ID " + responseRequest.getLetterId() +" DOES_NOT_EXIST");
        }
        Letter letter = letterOptional.get();
        response.setLetter(letter);

        Optional<School> schoolOptional = schoolRepository.findById(responseRequest.getSchoolId());
        if (schoolOptional.isEmpty()){
            throw new GlobalException("SCHOOL_ID_NOT_FOUND_IN_RESPONSE_SERVICE","SCHOOL with ID " + responseRequest.getSchoolId() +" DOES_NOT_EXIST");
        }

        School school = schoolOptional.get();
        response.setSchool(school);

        response.setFieldResponses(responseRequest.getFieldResponses());

        Response savedResponse=responseRepository.save(response);

        ResponseResponse responseResponse = new ResponseResponse();

        responseResponse.setId(savedResponse.getId());
        responseResponse.setLetterId(savedResponse.getLetter().getId());
        responseResponse.setSentAt(savedResponse.getSentAt());
        responseResponse.setSchoolId(savedResponse.getSchool().getId());
        responseResponse.setFieldResponses(savedResponse.getFieldResponses());

        return responseResponse;

    }

    public RespondersNonRespondersList getRespondersNonRespondersListById(String letterId) {
        RespondersNonRespondersList respondersNonRespondersList = new RespondersNonRespondersList();
        respondersNonRespondersList.setResponses(new ArrayList<>());
        respondersNonRespondersList.setNonResponders(new ArrayList<>());
        respondersNonRespondersList.setResponders(new ArrayList<>());

        List<String> listOfResponderIds=new ArrayList<>();



        Optional<Letter> letterOptional = letterRepository.findById(letterId);
        if (letterOptional.isEmpty()){
            throw new GlobalException("LETTER_ID_NOT_FOUND_IN_RESPONSE_SERVICE","LETTER with ID " + letterId +" DOES_NOT_EXIST");
        }
        Letter letter = letterOptional.get();

        for(Response currentResponse:letter.getResponses()){
            ResponseResponse responseResponse = new ResponseResponse();
            responseResponse.setId(currentResponse.getId());
            responseResponse.setLetterId(currentResponse.getLetter().getId());
            responseResponse.setSentAt(currentResponse.getSentAt());
            responseResponse.setSchoolId(currentResponse.getSchool().getId());
            responseResponse.setFieldResponses(currentResponse.getFieldResponses());

            respondersNonRespondersList.getResponses().add(responseResponse);

            Optional<School> schoolOptional = schoolRepository.findById(responseResponse.getSchoolId());
            if (schoolOptional.isEmpty()){
                throw new GlobalException("SCHOOL_ID_NOT_FOUND_IN_LETTER_SERVICE","SCHOOL with ID " + responseResponse.getSchoolId() +" DOES_NOT_EXIST");
            }

            School school = schoolOptional.get();
            respondersNonRespondersList.getResponders().add(commonMapper.schoolToSchoolResponse(school));

            listOfResponderIds.add(school.getId());
        }

        for (School currentSchool:letter.getSchools()){
            if(listOfResponderIds.stream().noneMatch(id->id.equals(currentSchool.getId()))){

                respondersNonRespondersList.getNonResponders().add(commonMapper.schoolToSchoolResponse(currentSchool));
            }
        }

        return respondersNonRespondersList;
    }
}
