package ma.directionregionale.gestionlettres.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SchoolResponse {
    private SchoolDetailsResponse schoolDetails;
    private List<ProjectResponse> projects;
    private List<BranchResponse> branches;


}
