package Projekt2.Repositories;

import Projekt2.Entities.OrderItem;
import java.util.List;

public interface OrderItemRepository
{
    List<OrderItem> getByOrderId(int orderId);
    List<OrderItem> getByItemId(int itemId);
    List<OrderItem> getAll();
    void add(OrderItem orderItem);
    void update(OrderItem orderItem);
    void delete(OrderItem orderItem);
}
