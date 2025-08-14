package ma.directionregionale.gestionlettres.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LetterDetailsResponse {
    private String urgency;
    private String deadline;
    private String id;
    private String sentAt;
}
