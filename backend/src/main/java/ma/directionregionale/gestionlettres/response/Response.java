package ma.directionregionale.gestionlettres.response;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ma.directionregionale.gestionlettres.letter.Letter;
import ma.directionregionale.gestionlettres.school.School;

@Entity
@Table(name="responses")
@Getter
@Setter
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;


    @ManyToOne
    @JoinColumn(name = "letter_id", nullable = false)
    private Letter letter;
}
