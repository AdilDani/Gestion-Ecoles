package ma.directionregionale.gestionlettres.response;


import ma.directionregionale.gestionlettres.dto.RespondersNonRespondersList;
import ma.directionregionale.gestionlettres.dto.ResponseRequest;
import ma.directionregionale.gestionlettres.dto.ResponseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responses")
public class ResponseController {
    @Autowired
    private ResponseService responseService;


    @PostMapping
    public ResponseResponse createNewResponse(@RequestBody ResponseRequest responseRequest){
        return responseService.createNewResponse(responseRequest);
    }

    @GetMapping("/{letterId}")
    public RespondersNonRespondersList getARespondersNonResponders(@PathVariable String id){
        return responseService.getRespondersNonRespondersListById(id);
    }

}
