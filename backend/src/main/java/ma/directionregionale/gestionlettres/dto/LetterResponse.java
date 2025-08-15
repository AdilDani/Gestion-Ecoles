package ma.directionregionale.gestionlettres.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LetterResponse {
    private LetterDetailsResponse letterDetailsResponse;
    private List<SchoolResponse> schools;
    private TemplateResponse template;
    private List<ResponseResponse> responses;
}
