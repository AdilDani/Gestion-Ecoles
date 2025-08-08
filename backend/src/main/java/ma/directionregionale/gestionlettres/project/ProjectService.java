package ma.directionregionale.gestionlettres.project;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProjectService {
    @Autowired
    public ProjectRepository projectRepository;


}
