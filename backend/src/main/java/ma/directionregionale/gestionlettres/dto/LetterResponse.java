package ma.directionregionale.gestionlettres.dto;

import lombok.Getter;
import lombok.Setter;
import ma.directionregionale.gestionlettres.response.Response;
import ma.directionregionale.gestionlettres.response.ResponseResponse;
import ma.directionregionale.gestionlettres.school.School;
import ma.directionregionale.gestionlettres.template.Template;

import java.util.List;

@Getter
@Setter
public class LetterResponse {
    private LetterDetailsResponse letterDetailsResponse;
    private List<SchoolResponse> schools;
    private TemplateResponse template;
    private List<ResponseResponse> responses;
}
