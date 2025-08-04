package ma.directionregionale.gestionlettres.school;

import jakarta.transaction.Transactional;
import ma.directionregionale.gestionlettres.config.GlobalException;
import ma.directionregionale.gestionlettres.dto.SchoolSaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;

    public School addNewSchool(SchoolSaveRequest request){
        School school=new School();

        school.setSchoolname(request.getSchoolname());
        school.setCodegrais(request.getCodegrais());
        school.setUsername(request.getUsername());
        school.setPassword(request.getPassword());
        school.setPhonenumber(request.getPhonenumber());
        school.setRegion(request.getRegion());
        school.setNumberofstudents(request.getNumberofstudents());
        school.setNumberofclassrooms(request.getNumberofclassrooms());
        school.setDirectorfirstname(request.getDirectorfirstname());
        school.setDirectorlastname(request.getDirectorlastname());
        school.setEducationlevel(request.getEducationlevel());
        school.setHasBranch(request.getHasBranch());
        school.setHasProject(request.getHasProject());


         return schoolRepository.save(school);
    }

    public List<School> listSchools() {
        return schoolRepository.findAll();
    }

    public School getSchoolById(String id){
        Optional<School> schoolOptional=schoolRepository.findById(id);
        if (schoolOptional.isEmpty()) {
            throw new RuntimeException("School does not exist!");
        }
        return schoolOptional.get();
    }

    public School updateSchool(SchoolSaveRequest request){
        School school = new School();
        if(schoolRepository.existsById(request.getId())) {
            school.setId(request.getId());
            school.setSchoolname(request.getSchoolname());
            school.setCodegrais(request.getCodegrais());
            school.setUsername(request.getUsername());
            school.setPassword(request.getPassword());
            school.setPhonenumber(request.getPhonenumber());
            school.setRegion(request.getRegion());
            school.setNumberofstudents(request.getNumberofstudents());
            school.setNumberofclassrooms(request.getNumberofclassrooms());
            school.setDirectorfirstname(request.getDirectorfirstname());
            school.setDirectorlastname(request.getDirectorlastname());
            school.setEducationlevel(request.getEducationlevel());
            school.setHasBranch(request.getHasBranch());
            school.setHasProject(request.getHasProject());

            return schoolRepository.save(school);
        }
        else{
            throw new GlobalException("SCHOOL_ID_NOT_FOUND","School with ID " + request.getId() + " does not exist");

        }

    }
}
