package ma.directionregionale.gestionlettres.dto;

import lombok.Getter;
import lombok.Setter;
import ma.directionregionale.gestionlettres.project.Project;

import java.util.List;

@Getter
@Setter
public class SchoolRequest {
    private String id;
    private String schoolname;
    private String codegrais;
    private String username;
    private String password;
    private String phonenumber;
    private String region;
    private String numberofstudents;
    private String numberofclassrooms;
    private String directorfirstname;
    private String directorlastname;
    private String educationlevel;

    private List<ProjectRequest> projects;
    private List<BranchRequest> branches;
}
