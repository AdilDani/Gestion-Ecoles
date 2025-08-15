package ma.directionregionale.gestionlettres.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseResponse {
    private String id;
    private String schoolId;
    private String letterId;
    private List<String> fieldResponses;
    private String sentAt;
}
