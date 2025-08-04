package ma.directionregionale.gestionlettres.school;

import ma.directionregionale.gestionlettres.dto.SchoolSaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @PostMapping
    public School addNewSchool(@RequestBody SchoolSaveRequest request) {
        return schoolService.addNewSchool(request);
    }

    @PutMapping
    public School updateSchool(@RequestBody SchoolSaveRequest request){
        return schoolService.updateSchool(request);
    }



    @GetMapping("")
    public List<School> listSchools() {
        return schoolService.listSchools();
    }

    @GetMapping("/{id}")
        public School getSchoolById(@PathVariable String id){
        return schoolService.getSchoolById(id);
    }
}
