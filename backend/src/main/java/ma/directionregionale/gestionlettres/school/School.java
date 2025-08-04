package ma.directionregionale.gestionlettres.school;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="schools")
@Getter
@Setter
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

    private Boolean hasBranch;
    private Boolean hasProject;

}
