package ma.directionregionale.gestionlettres.dto;

import lombok.Getter;
import lombok.Setter;
import ma.directionregionale.gestionlettres.school.School;

import java.util.List;

@Getter
@Setter
public class RespondersNonRespondersList {
    private List<ResponseResponse> responses;
    private List<SchoolResponse> responders;
    private List<SchoolResponse> nonResponders;
}
