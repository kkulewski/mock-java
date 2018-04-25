package projekt2.repositories;

import projekt2.entities.OrderItem;
import java.util.List;

public interface OrderItemRepository extends GenericRepository<OrderItem>
{
    List<OrderItem> getByOrderId(int orderId);
    List<OrderItem> getByItemId(int itemId);
}
