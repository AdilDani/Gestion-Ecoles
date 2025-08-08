package ma.directionregionale.gestionlettres.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRequest {
    private String id;
    private String projectcode;
    private String projectprogress;
    private String assignedtechnician;
    private String projecttype;
    private String assignedestablishment;
}
