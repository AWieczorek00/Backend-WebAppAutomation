package pl.backendbscthesis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.backendbscthesis.Entity.Activities;
import pl.backendbscthesis.Entity.template.ActivitiesTemplate;

@Repository
public interface ActivitiesTemplateRepository extends JpaRepository<ActivitiesTemplate,Long> {
}
