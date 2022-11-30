package pl.backendbscthesis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.backendbscthesis.Entity.Order;
import pl.backendbscthesis.Repository.OrderRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final ActivitiesService activitiesService;

    private final PartService partService;


    @Autowired
    public OrderService(OrderRepository orderRepository, ActivitiesService activitiesService, PartService partService) {
        this.orderRepository = orderRepository;
        this.activitiesService = activitiesService;
        this.partService = partService;
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional()
    public Order add(Order orderBody) {
        activitiesService.createActivitiesList(orderBody.getActivitiesList());
        partService.createAllParts(orderBody.getPartList());
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
                            0l, order.getClient(), null, null, null, null, null,0,0, order.getPriority(), order.getStatus(), order.getPeriod(), null
                    );
                    return orderRepository.save(orderDuplicate);
                }).orElseThrow(() -> new ResourceNotFoundException("Nie zaleziono takiego zelecenia do powielenia"));

    }


    public Order findOrderById(Long id) {

        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono zlecenia o takim id: " + id));

    }

    @Transactional
    public Order updateOrder(Order orderBody) {
        System.out.println(orderBody);


        return orderRepository.findById(orderBody.getId()).map(orderUpdate->{
            orderUpdate.setClient(orderBody.getClient());
            orderUpdate.setActivitiesList(activitiesService.updateActivitiesList(orderBody.getActivitiesList(),orderUpdate.getActivitiesList()));
            orderUpdate.setEmployeeList(orderBody.getEmployeeList());
            orderUpdate.setPartList(partService.updatePartList(orderBody.getPartList(),orderUpdate.getPartList()));
            orderUpdate.setPeriod(orderBody.getPeriod());
            orderUpdate.setStatus(orderBody.getStatus());
            orderUpdate.setPriority(orderBody.getPriority());
            orderUpdate.setDateOfAdmission(orderBody.getDateOfAdmission());
            orderUpdate.setDateOfExecution(orderBody.getDateOfExecution());
            orderUpdate.setNote(orderBody.getNote());
            return orderRepository.save(orderUpdate);
        }).orElseThrow(()->new ResourceNotFoundException("nie znaleziono")) ;

    }
}

