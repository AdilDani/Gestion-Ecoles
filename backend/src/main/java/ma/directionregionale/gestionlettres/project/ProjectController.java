package ma.directionregionale.gestionlettres.project;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Transactional
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

}
