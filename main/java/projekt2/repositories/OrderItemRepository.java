package projekt2.repositories;

import projekt2.entities.OrderItem;
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
