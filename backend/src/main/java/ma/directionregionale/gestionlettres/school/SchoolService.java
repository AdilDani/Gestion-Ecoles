package ma.directionregionale.gestionlettres.school;

import jakarta.transaction.Transactional;
import ma.directionregionale.gestionlettres.branch.Branch;
import ma.directionregionale.gestionlettres.branch.BranchRepository;
import ma.directionregionale.gestionlettres.config.GlobalException;
import ma.directionregionale.gestionlettres.dto.BranchRequest;
import ma.directionregionale.gestionlettres.dto.ProjectRequest;
import ma.directionregionale.gestionlettres.dto.SchoolRequest;
import ma.directionregionale.gestionlettres.dto.SchoolResponse;
import ma.directionregionale.gestionlettres.mapper.CommonMapper;
import ma.directionregionale.gestionlettres.project.Project;
import ma.directionregionale.gestionlettres.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ma.directionregionale.gestionlettres.utils.CommonUtils.isInt;

@Service
@Transactional
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private CommonMapper commonMapper;

    public SchoolResponse addNewSchool(SchoolRequest request){
        //this handles the creation of a school and its branches and projects
        //school creation
        School school = new School();

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

        School savedSchool = schoolRepository.save(school);

        if (request.getBranches() != null && !request.getBranches().isEmpty()){
            for(BranchRequest branchRequest: request.getBranches()){
                Branch branch = new Branch();
                branch.setBranchname(branchRequest.getBranchname());
                branch.setCodegrais(branchRequest.getCodegrais());
                branch.setNumberofstudents(branchRequest.getNumberofstudents());
                branch.setSchool(savedSchool);

                Branch savedBranch=branchRepository.save(branch);
                savedSchool.addBranch(savedBranch);
            }
            savedSchool = schoolRepository.save(savedSchool);
        }


        if (request.getProjects() != null && !request.getProjects().isEmpty()) {
            for (ProjectRequest projectRequest : request.getProjects()) {
                Project project = new Project();
                project.setProjectcode(projectRequest.getProjectcode());
                project.setProjecttype(projectRequest.getProjecttype());
                project.setProjectprogress(projectRequest.getProjectprogress());
                project.setAssignedtechnician(projectRequest.getAssignedtechnician());
                project.setStatus(projectRequest.getStatus());
                project.setSchool(savedSchool);

                //each project has an assigned establishment
                //it is sent from the frontend as either "main" or "1" "2",...
                //

                if(request.getBranches() ==null){
                    project.setAssignedestablishment(savedSchool.getId());
                }
                else if(projectRequest.getAssignedestablishment().equals("main")){
                    project.setAssignedestablishment(savedSchool.getId());
                }
                else {
                    int i=Integer.parseInt(projectRequest.getAssignedestablishment()); // this is the number of the branch to which the project is linked
                    String branchId = savedSchool.getBranches().get(i - 1).getId();
                    project.setAssignedestablishment(branchId);
                }


                Project savedProject = projectRepository.save(project);
                savedSchool.addProject(savedProject);
            }
            savedSchool = schoolRepository.save(savedSchool);
        }

         savedSchool = schoolRepository.save(savedSchool);
        return commonMapper.schoolToSchoolResponse(savedSchool);
    }

    public List<SchoolResponse> getAllSchools() {
        List<SchoolResponse> schoolResponseList=new ArrayList<>();
        for(School school:schoolRepository.findAll()) {
            schoolResponseList.add(commonMapper.schoolToSchoolResponse(school));
        }
        return schoolResponseList;
    }

    public SchoolResponse getSchoolById(String id){
        Optional<School> schoolOptional=schoolRepository.findById(id);
        if (schoolOptional.isEmpty()) {
            throw new RuntimeException("School does not exist!");
        }
        SchoolResponse schoolResponse = new SchoolResponse();

        return commonMapper.schoolToSchoolResponse(schoolOptional.get());
    }

    public SchoolResponse updateSchool(SchoolRequest request){
        Optional<School> existingSchoolOpt = schoolRepository.findById(request.getId());
        if (existingSchoolOpt.isEmpty()) {
            throw new GlobalException("SCHOOL_ID_NOT_FOUND", "School with ID " + request.getId() + " does not exist");
        }

        School school = existingSchoolOpt.get();
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


        School savedSchool= schoolRepository.save(school);
        
        List<BranchRequest> branchRequests = request.getBranches() != null ? 
                request.getBranches() : 
                new ArrayList<>();
        List<Branch> dbBranches = savedSchool.getBranches();
        
        List<String> dbBrancheIdsToDelete = new ArrayList<>();
        
        for(Branch dbBranch: dbBranches) {
            boolean notExists = branchRequests.stream()
                    .noneMatch(b -> b.getId().equals(dbBranch.getId()));
            
            if(notExists) {
                dbBrancheIdsToDelete.add(dbBranch.getId());
            }
        }

        for (String dbBranchIdToDelete : dbBrancheIdsToDelete) {
            branchRepository.deleteById(dbBranchIdToDelete);
            savedSchool.getBranches()
                    .removeIf(b -> b.getId().equals(dbBranchIdToDelete));
        }

        savedSchool = schoolRepository.save(savedSchool);
        
        for(BranchRequest branchRequest: branchRequests) {
            if(branchRequest.getId().equals("null")) {
                Branch branch=new Branch();
                branch.setNumberofstudents(branchRequest.getNumberofstudents());
                branch.setCodegrais(branchRequest.getCodegrais());
                branch.setBranchname(branchRequest.getBranchname());
                branch.setSchool(savedSchool);

                Branch savedBranch = branchRepository.save(branch);
                savedSchool.getBranches().add(savedBranch);
                savedSchool = schoolRepository.save(savedSchool);
            }
            else{
                Optional<Branch> branchOptional=
                        branchRepository.findById(branchRequest.getId());
                if(branchOptional.isEmpty()) {
                    throw new GlobalException("BRANCH_NOT_EXISTS", "Branch doesn't exist: " + branchRequest.getId());
                }
                
                Branch branch=branchOptional.get();

                branch.setNumberofstudents(branchRequest.getNumberofstudents());
                branch.setCodegrais(branchRequest.getCodegrais());
                branch.setBranchname(branchRequest.getBranchname());
                branch.setSchool(savedSchool);

                branchRepository.save(branch);
                savedSchool = schoolRepository.save(savedSchool);
            }
        }

        // -------------------------------------------------------------------
        if(request.getProjects() != null){
            for(ProjectRequest projectRequest: request.getProjects()){
                if(projectRequest.getId().equals("null")){
                    Project project = new Project();
                    project.setSchool(savedSchool);
                    project.setAssignedtechnician(projectRequest.getAssignedtechnician());
                    project.setStatus(projectRequest.getStatus());
                    project.setProjecttype(projectRequest.getProjecttype());
                    project.setProjectprogress(projectRequest.getProjectprogress());
                    project.setProjectcode(projectRequest.getProjectcode());

                    if(projectRequest.getAssignedestablishment().equals("main")){
                        project.setAssignedestablishment(savedSchool.getId());
                    } else if (isInt(projectRequest.getAssignedestablishment())) {
                        int i= Integer.parseInt(projectRequest.getAssignedestablishment());
                        project.setAssignedestablishment(savedSchool.getBranches().get(i-1).getId());
                    }
                    else{
                        project.setAssignedestablishment(projectRequest.getAssignedestablishment());
                    }
                    projectRepository.save(project);
                    savedSchool.getProjects().add(project);
                    savedSchool=schoolRepository.save(savedSchool);
                }
                else{
                    Optional<Project> projectOptional = projectRepository.findById(projectRequest.getId());
                    if(projectOptional.isEmpty()) {
                        throw new GlobalException("PROJECT", "Project doesn't exist: " + projectRequest.getId());
                    }
                    Project project=projectOptional.get();

                    project.setSchool(savedSchool);
                    project.setAssignedtechnician(projectRequest.getAssignedtechnician());
                    project.setStatus(projectRequest.getStatus());
                    project.setProjecttype(projectRequest.getProjecttype());
                    project.setProjectprogress(projectRequest.getProjectprogress());
                    project.setProjectcode(projectRequest.getProjectcode());

                    if(projectRequest.getAssignedestablishment().equals("main")){
                        project.setAssignedestablishment(savedSchool.getId());
                    } else if (isInt(projectRequest.getAssignedestablishment())) {
                        int i= Integer.parseInt(projectRequest.getAssignedestablishment());
                        project.setAssignedestablishment(savedSchool.getBranches().get(i-1).getId());
                    }
                    else{
                        project.setAssignedestablishment(projectRequest.getAssignedestablishment());
                    }
                    projectRepository.save(project);
                    savedSchool=schoolRepository.save(savedSchool);


                }
            }

        }


        // --------------------------------------------------------------------

        return commonMapper.schoolToSchoolResponse(savedSchool);

    }
}
