package pl.backendbscthesis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.backendbscthesis.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
