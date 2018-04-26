package projekt2.services;

import projekt2.entities.Client;
import projekt2.entities.Order;
import projekt2.repositories.OrderRepository;

import java.util.List;

public class OrderService
{
    private OrderRepository orderRepo;

    public OrderService(OrderRepository orderRepo)
    {
        this.orderRepo = orderRepo;
    }

    public List<Order> getClientOrders(Client client)
    {
        return orderRepo.getByClientId(client.getId());
    }
}
