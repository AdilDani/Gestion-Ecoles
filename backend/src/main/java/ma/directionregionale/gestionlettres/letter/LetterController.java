package ma.directionregionale.gestionlettres.letter;

import ma.directionregionale.gestionlettres.dto.LetterRequest;
import ma.directionregionale.gestionlettres.dto.LetterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/letters")
public class LetterController {
    @Autowired
    public LetterService letterService;

    @PostMapping
    public LetterResponse addNewLetter(@RequestBody LetterRequest letterRequest){
        return letterService.addNewLetter(letterRequest);
    }
}
