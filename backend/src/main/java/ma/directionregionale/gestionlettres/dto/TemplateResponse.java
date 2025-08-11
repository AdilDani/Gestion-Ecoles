package ma.directionregionale.gestionlettres.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TemplateResponse {
    private String id;
    private String name;
    private String description;
    private List<String> fields;
}
