package ma.directionregionale.gestionlettres.project;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ma.directionregionale.gestionlettres.school.School;


@Entity
@Table(name="projects")
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String status;
    private String projectcode;
    private String projectprogress;
    private String assignedtechnician;
    private String projecttype;
    private String assignedestablishment; //this is for the case of branches
    //a project can be assigned to either a branch or the main school

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;



}
