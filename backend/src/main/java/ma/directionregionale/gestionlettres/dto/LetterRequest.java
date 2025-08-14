package ma.directionregionale.gestionlettres.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LetterRequest {
    private String templateId;
    private String urgency;
    private String deadline;
    private List<String> schoolIds;

}
