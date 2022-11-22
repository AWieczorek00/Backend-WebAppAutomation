package pl.backendbscthesis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.backendbscthesis.Entity.Part;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
}
