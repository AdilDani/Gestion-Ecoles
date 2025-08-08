package ma.directionregionale.gestionlettres.school;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ma.directionregionale.gestionlettres.branch.Branch;
import ma.directionregionale.gestionlettres.dto.BranchRequest;
import ma.directionregionale.gestionlettres.project.Project;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();

    public void addProject(Project project){
        projects.add(project);
        project.setSchool(this);
    }

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    private List<Branch> branches = new ArrayList<>();

    public void addBranch(Branch branch){
        branches.add(branch);
        branch.setSchool(this);
    }



}
