package projekt2.repositories;

import projekt2.entities.Order;
import java.util.List;

public interface OrderRepository extends GenericRepository<Order>
{
    List<Order> getByClientId(int clientId);
}
