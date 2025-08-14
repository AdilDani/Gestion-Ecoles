package ma.directionregionale.gestionlettres.letter;

import ma.directionregionale.gestionlettres.dto.LetterRequest;
import ma.directionregionale.gestionlettres.dto.LetterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/letters")
public class LetterController {
    @Autowired
    public LetterService letterService;

    @PostMapping
    public LetterResponse addNewLetter(@RequestBody LetterRequest letterRequest){
        return letterService.addNewLetter(letterRequest);
    }

    @GetMapping("/school/{id}")
    public List<LetterResponse> getLetterBySchoolId(@PathVariable String id){
        return letterService.getLettersBySchoolId(id);
    }

    @GetMapping("/{id}")
    public LetterResponse getLetterById(@PathVariable String id){
        return letterService.getLetterById(id);
    }
}
