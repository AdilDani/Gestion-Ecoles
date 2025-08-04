package ma.directionregionale.gestionlettres.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolAddRequest {
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

    private Boolean hasBranch;
    private Boolean hasProject;
}
