package pl.backendbscthesis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.backendbscthesis.Entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
