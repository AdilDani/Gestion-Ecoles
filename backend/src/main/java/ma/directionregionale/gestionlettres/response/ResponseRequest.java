package ma.directionregionale.gestionlettres.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseRequest {

    private List<String> fieldResponses;
    private String sentAt;

}
