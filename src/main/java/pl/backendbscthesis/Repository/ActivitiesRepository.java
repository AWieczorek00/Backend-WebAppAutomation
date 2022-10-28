package pl.backendbscthesis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.backendbscthesis.Entity.Activities;

@Repository
public interface ActivitiesRepository extends JpaRepository<Activities,Long> {
}
