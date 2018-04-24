package Projekt2.Repositories;

import Projekt2.Entities.Order;
import java.util.List;

public interface OrderRepository
{
    Order getById(int orderId);
    List<Order> getByClientId(int clientId);
    List<Order> getAll();
    void add(Order order);
    void update(Order order);
    void delete(Order order);
}
