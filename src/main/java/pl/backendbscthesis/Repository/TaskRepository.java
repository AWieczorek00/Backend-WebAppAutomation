package pl.backendbscthesis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.backendbscthesis.Entity.Employee;
import pl.backendbscthesis.Entity.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<List<Task>> findAllByEmployeeIndividualId(Long individualId);


    @Override
    Optional<Task> findById(Long id);



}
