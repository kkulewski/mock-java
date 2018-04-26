package projekt2.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import projekt2.entities.Client;
import projekt2.entities.Item;
import projekt2.entities.Order;
import projekt2.entities.OrderItem;
import projekt2.extensions.MockitoExtension;
import projekt2.repositories.OrderItemRepository;
import projekt2.repositories.OrderRepository;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class OrderServiceMockitoTest
{
    private OrderRepository orderRepo;
    private OrderItemRepository orderItemRepo;

    private OrderService os;

    @BeforeEach
    void setup()
    {
        orderRepo = Mockito.mock(OrderRepository.class);
        orderItemRepo = Mockito.mock(OrderItemRepository.class);
        os = new OrderService(orderRepo, orderItemRepo);
    }

    @Test
    void getClientOrdersReturnsExpectedOrders()
    {
        // Arrange
        Client client = new Client(1, "John", "Doe", "johndoe@email.com");
        List<Order> expectedOrders = Arrays.asList(new Order(1, client.getId()), new Order(2, client.getId()));
        doReturn(expectedOrders).when(orderRepo).getByClientId(client.getId());

        // Act
        List<Order> result = os.getClientOrders(client);

        // Assert
        assertThat(result).containsAll(expectedOrders);
    }
}
