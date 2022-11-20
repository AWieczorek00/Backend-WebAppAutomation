package pl.backendbscthesis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
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


    @Transactional
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Transactional
    public Order duplicate(Long id) {
        return orderRepository.findById(id)
                .map(order -> {
                    Order orderDuplicate = new Order(
                            0l, order.getClient(), null, null, null, null, order.getPriority(), order.getStatus(), order.getPeriod(), null
                    );
                    return orderRepository.save(orderDuplicate);
                }).orElseThrow(() -> new ResourceNotFoundException("Nie zaleziono takiego zelecenia do powielenia"));

    }

    public Order findOneOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nie zaleziono takiego zelecenia o id: "+id));
    }
}

