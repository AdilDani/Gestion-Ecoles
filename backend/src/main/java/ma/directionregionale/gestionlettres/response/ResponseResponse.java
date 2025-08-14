package ma.directionregionale.gestionlettres.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseResponse {
    private String id;
    private List<String> fieldResponses;
    private String sentAt;

}
