package ma.directionregionale.gestionlettres.response;


import ma.directionregionale.gestionlettres.dto.RespondersNonRespondersList;
import ma.directionregionale.gestionlettres.dto.ResponseRequest;
import ma.directionregionale.gestionlettres.dto.ResponseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/responses")
public class ResponseController {
    @Autowired
    private ResponseService responseService;


    @PostMapping
    public ResponseResponse createNewResponse(@RequestBody ResponseRequest responseRequest){
        return responseService.createNewResponse(responseRequest);
    }

    @GetMapping("/{id}")//the id mentioned is a letterId
    public RespondersNonRespondersList getRespondersNonRespondersListById(@PathVariable String id){
        return responseService.getRespondersNonRespondersListById(id);
    }

}
