package ma.directionregionale.gestionlettres.letter;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ma.directionregionale.gestionlettres.response.Response;
import ma.directionregionale.gestionlettres.school.School;
import ma.directionregionale.gestionlettres.template.Template;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="letters")
@Getter
@Setter
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String urgency;
    private String deadline;
    private String sentAt;


    @OneToMany(mappedBy = "letter")
    private List<Response> responses = new ArrayList<>();



    @ManyToOne
    @JoinColumn(name = "template_id")
    private Template template;

    @ManyToMany
    @JoinTable(
            name = "letter_schools",
            joinColumns = @JoinColumn(name = "letter_id"),
            inverseJoinColumns = @JoinColumn(name = "school_id")
    )
    private List<School> schools = new ArrayList<>();



}
