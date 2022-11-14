package pl.backendbscthesis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.backendbscthesis.Entity.template.PartsTemplate;

@Repository
public interface PartsTemplateRepository extends JpaRepository<PartsTemplate,Long> {
}
