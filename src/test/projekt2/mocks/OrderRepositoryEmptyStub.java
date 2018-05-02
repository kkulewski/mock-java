package projekt2.mocks;

import projekt2.entities.Order;
import projekt2.repositories.InMemoryGenericRepository;
import projekt2.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderRepositoryEmptyStub extends InMemoryGenericRepository<Order> implements OrderRepository
{
    @Override
    public List<Order> getByClientId(int clientId)
    {
        return new ArrayList<>();
    }
}
