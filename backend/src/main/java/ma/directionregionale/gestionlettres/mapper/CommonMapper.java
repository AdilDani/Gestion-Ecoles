package ma.directionregionale.gestionlettres.mapper;

import ma.directionregionale.gestionlettres.branch.Branch;
import ma.directionregionale.gestionlettres.dto.*;
import ma.directionregionale.gestionlettres.letter.Letter;
import ma.directionregionale.gestionlettres.project.Project;
import ma.directionregionale.gestionlettres.response.Response;
import ma.directionregionale.gestionlettres.response.ResponseResponse;
import ma.directionregionale.gestionlettres.school.School;
import ma.directionregionale.gestionlettres.template.Template;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonMapper {

    public SchoolResponse schoolToSchoolResponse(School school){
        SchoolResponse schoolResponse = new SchoolResponse();
        schoolResponse.setSchoolDetails(new SchoolDetailsResponse());
        schoolResponse.setProjects(new ArrayList<>());
        schoolResponse.setBranches(new ArrayList<>());

        schoolDetailsToSchoolResponse(school,schoolResponse.getSchoolDetails());

        schoolProjectsToSchoolResponse(school,schoolResponse.getProjects());

        schoolBranchesToSchoolResponse(school,schoolResponse.getBranches());





        return schoolResponse;
    }
    public void schoolDetailsToSchoolResponse(School school,SchoolDetailsResponse schoolDetailsResponse){
        schoolDetailsResponse.setId(school.getId());
        schoolDetailsResponse.setSchoolname(school.getSchoolname());
        schoolDetailsResponse.setCodegrais(school.getCodegrais());
        schoolDetailsResponse.setUsername(school.getUsername());
        schoolDetailsResponse.setPassword(school.getPassword());
        schoolDetailsResponse.setPhonenumber(school.getPhonenumber());
        schoolDetailsResponse.setRegion(school.getRegion());
        schoolDetailsResponse.setNumberofstudents(school.getNumberofstudents());
        schoolDetailsResponse.setNumberofclassrooms(school.getNumberofclassrooms());
        schoolDetailsResponse.setDirectorfirstname(school.getDirectorfirstname());
        schoolDetailsResponse.setDirectorlastname(school.getDirectorlastname());
        schoolDetailsResponse.setEducationlevel(school.getEducationlevel());
    }

    public void schoolProjectsToSchoolResponse(School school, List<ProjectResponse> projects){
        for(Project project: school.getProjects()){
            ProjectResponse projectResponse = new ProjectResponse();
            projectResponse.setId(project.getId());
            projectResponse.setProjectcode(project.getProjectcode());
            projectResponse.setProjectprogress(project.getProjectprogress());
            projectResponse.setAssignedtechnician(project.getAssignedtechnician());
            projectResponse.setProjecttype(project.getProjecttype());
            projectResponse.setAssignedestablishment(project.getAssignedestablishment());
            projectResponse.setStatus(project.getStatus());
            projects.add(projectResponse);
        }
    }


    public void schoolBranchesToSchoolResponse(School school, List<BranchResponse> branches){
        for(Branch branch:school.getBranches()){
            BranchResponse branchResponse = new BranchResponse();
            branchResponse.setId(branch.getId());
            branchResponse.setCodegrais(branch.getCodegrais());
            branchResponse.setBranchname(branch.getBranchname());
            branchResponse.setNumberofstudents(branch.getNumberofstudents());

            branches.add(branchResponse);
        }
    }



    public LetterResponse letterToLetterResponse(Letter letter){
        LetterResponse letterResponse = new LetterResponse();
        
        // Initialize the nested objects
        letterResponse.setLetterDetailsResponse(new LetterDetailsResponse());
        letterResponse.setSchools(new ArrayList<>());
        letterResponse.setResponses(new ArrayList<>());

        letterResponse.setTemplate(templateToTemplateResponse(letter.getTemplate()));

        letterDetailsToLetterResponse(letter, letterResponse.getLetterDetailsResponse());

        schoolsToLetterResponse(letter, letterResponse.getSchools());

        responsesToLetterResponse(letter, letterResponse.getResponses());
        return letterResponse;
    }

    public TemplateResponse templateToTemplateResponse (Template template){
        TemplateResponse templateResponse = new TemplateResponse();

        templateResponse.setId(template.getId());
        templateResponse.setName(template.getName());
        templateResponse.setDescription(template.getDescription());
        templateResponse.setFields(template.getFields());
         return templateResponse;
    }


    public void letterDetailsToLetterResponse (Letter letter, LetterDetailsResponse letterDetailsResponse){
        letterDetailsResponse.setId(letter.getId());
        letterDetailsResponse.setDeadline(letter.getDeadline());
        letterDetailsResponse.setUrgency(letter.getUrgency());
        letterDetailsResponse.setSentAt(letter.getSentAt());
    }

    public void responsesToLetterResponse(Letter letter, List<ResponseResponse> responseResponses){
        for (Response response : letter.getResponses()){
            ResponseResponse responseResponse = new ResponseResponse();
            responseResponse.setId(response.getId());
            responseResponse.setFieldResponses(response.getFieldResponses());
            responseResponse.setSentAt(response.getSentAt());

            responseResponses.add(responseResponse);
        }
    }

    public void schoolsToLetterResponse(Letter letter, List<SchoolResponse> schoolResponses){
        for(School school : letter.getSchools()){

            schoolResponses.add(schoolToSchoolResponse(school));
        }
    }

}

