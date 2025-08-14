package ma.directionregionale.gestionlettres.letter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<Letter,String> {
}
