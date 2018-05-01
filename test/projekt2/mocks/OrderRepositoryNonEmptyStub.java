package projekt2.mocks;

import projekt2.entities.Order;
import projekt2.repositories.InMemoryGenericRepository;
import projekt2.repositories.OrderRepository;

import java.util.Arrays;
import java.util.List;

public class OrderRepositoryNonEmptyStub extends InMemoryGenericRepository<Order> implements OrderRepository
{
    @Override
    public List<Order> getByClientId(int clientId)
    {
        return Arrays.asList(new Order(1, clientId), new Order(2, clientId));
    }
}
