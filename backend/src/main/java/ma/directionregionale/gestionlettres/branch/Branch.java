package ma.directionregionale.gestionlettres.branch;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ma.directionregionale.gestionlettres.school.School;


@Entity
@Table(name = "branches")
@Getter
@Setter
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String branchname;
    private String codegrais;
    private String numberofstudents;
    @ManyToOne
    @JoinColumn(name= "school_id", nullable = false)
    private School school;


}
