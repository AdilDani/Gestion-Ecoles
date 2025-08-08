package ma.directionregionale.gestionlettres.school;

import ma.directionregionale.gestionlettres.dto.SchoolRequest;
import ma.directionregionale.gestionlettres.dto.SchoolResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @PostMapping
    public SchoolResponse addNewSchool(@RequestBody SchoolRequest request) {
        return schoolService.addNewSchool(request);
    }

    @PutMapping
    public School updateSchool(@RequestBody SchoolRequest request){
        return schoolService.updateSchool(request);
    }



    @GetMapping("")
    public List<SchoolResponse> getAllSchools() {
        return schoolService.getAllSchools();
    }

    @GetMapping("/{id}")
        public SchoolResponse getSchoolById(@PathVariable String id){
        return schoolService.getSchoolById(id);
    }
}
