package pl.backendbscthesis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.backendbscthesis.Entity.Order;
import pl.backendbscthesis.Repository.ActivitiesRepository;
import pl.backendbscthesis.Repository.OrderRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final ActivitiesService activitiesService;

    private final ActivitiesRepository activitiesRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ActivitiesService activitiesService, ActivitiesRepository activitiesRepository) {
        this.orderRepository = orderRepository;
        this.activitiesService = activitiesService;
        this.activitiesRepository = activitiesRepository;
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional()
    public Order add(Order orderBody) {
        activitiesRepository.saveAll(orderBody.getActivitiesList());
        System.out.println("KUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUURWA");
        return orderRepository.saveAndFlush(orderBody);
    }


}

