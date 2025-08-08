package ma.directionregionale.gestionlettres.staff;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;




@Entity
@Table(name="technicians")
@Getter
@Setter
public class Technician {
    @Id
    private String id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
}