package projekt2.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import projekt2.entities.*;
import projekt2.extensions.MockitoExtension;
import projekt2.repositories.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class OrderServiceMockitoTest
{
    private OrderRepository orderRepo;
    private OrderItemRepository orderItemRepo;
    private ItemRepository itemRepo;
    private OrderService orderService;

    @BeforeEach
    void setup()
    {
        orderRepo = Mockito.mock(OrderRepository.class);
        orderItemRepo = Mockito.mock(OrderItemRepository.class);
        itemRepo = Mockito.mock(ItemRepository.class);
        orderService = new OrderService(orderRepo, orderItemRepo, itemRepo);
    }

    @Test
    void getClientOrdersThrowsWhenClientIsNull()
    {
        assertThatThrownBy(() -> orderService.getClientOrders(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("client is null");
    }

    @Test
    void getClientOrdersReturnsExpectedOrders()
    {
        // Arrange
        Client client = new Client(1, "John", "Doe", "johndoe@email.com");
        List<Order> expectedOrders = Arrays.asList(new Order(1, client.getId()), new Order(2, client.getId()));
        doReturn(expectedOrders).when(orderRepo).getByClientId(client.getId());

        // Act
        List<Order> result = orderService.getClientOrders(client);

        // Assert
        assertThat(result).containsAll(expectedOrders);
    }

    @Test
    void addItemToOrderReturnsFalseWhenItemAndOrderAreNull()
    {
        assertThat(orderService.addItemToOrder(null, null)).isFalse();
    }

    @Test
    void addItemToOrderReturnsFalseWhenItemIsAlreadyInAnotherOrder()
    {
        // Arrange
        Order myOrder = new Order(2, 1);
        Order anotherOrder = new Order(1, 10);
        Item item = new Item(1, "Some Item", 20.0);
        OrderItem orderItem = new OrderItem(anotherOrder.getId(), item.getId());
        doReturn(orderItem).when(orderItemRepo).getByItemId(item.getId());

        // Act
        boolean wasAdded = orderService.addItemToOrder(item, myOrder);

        // Assert
        assertThat(wasAdded).isFalse();
    }

    @Test
    void addItemToOrderReturnsTrueWhenItemWasAdded()
    {
        // Arrange
        Order myOrder = new Order(2, 1);
        Item item = new Item(1, "Product", 50.0);
        doReturn(null).when(orderItemRepo).getByItemId(item.getId());
        doReturn(true).when(orderItemRepo).add(any());

        // Act
        boolean wasAdded = orderService.addItemToOrder(item, myOrder);

        // Assert
        assertThat(wasAdded).isTrue();
    }

    @Test
    void getItemsForGivenOrderThrowsWhenOrderIsNull()
    {
        assertThatThrownBy(() -> orderService.getItemsForGivenOrder(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("order is null");
    }

    @Test
    void getItemsForGivenOrderReturnsExpectedItemSet()
    {
        // Arrange
        Order order = new Order(1, 10);
        Item item1 = new Item(1, "uKeyboard", 100.0);
        Item item2 = new Item(2, "uSpeakers", 200.0);
        OrderItem orderItem1 = new OrderItem(order.getId(), item1.getId());
        OrderItem orderItem2 = new OrderItem(order.getId(), item2.getId());
        List<OrderItem> orderItems = Arrays.asList(orderItem1, orderItem2);
        doReturn(orderItems).when(orderItemRepo).getByOrderId(order.getId());
        doReturn(item1).when(itemRepo).getById(item1.getId());
        doReturn(item2).when(itemRepo).getById(item2.getId());

        // Act
        List<Item> result = orderService.getItemsForGivenOrder(order);

        // Assert
        assertThat(result).containsExactlyInAnyOrder(item1, item2);
    }

    @Test
    void getItemsForGivenOrderWithEmptyOrderReturnsEmptyList()
    {
        // Arrange
        Order emptyOrder = new Order(1, 10);
        doReturn(new ArrayList<>()).when(orderItemRepo).getByOrderId(emptyOrder.getId());

        // Act
        List<Item> result = orderService.getItemsForGivenOrder(emptyOrder);

        // Assert
        assertThat(result).isEmpty();
    }

    @Test
    void getOrderTotalValueThrowsWhenOrderIsNull()
    {
        assertThatThrownBy(() -> orderService.getItemsForGivenOrder(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("order is null");
    }

    @Test
    void getOrderTotalValueReturnsExpectedValue()
    {
        // Arrange
        Order order = new Order(1, 10);
        Item item1 = new Item(1, "First item", 10.0);
        Item item2 = new Item(2, "SecondItem", 30.0);
        OrderItem orderItem1 = new OrderItem(order.getId(), item1.getId());
        OrderItem orderItem2 = new OrderItem(order.getId(), item2.getId());
        List<OrderItem> orderItems = Arrays.asList(orderItem1, orderItem2);
        doReturn(orderItems).when(orderItemRepo).getByOrderId(order.getId());
        doReturn(item1).when(itemRepo).getById(item1.getId());
        doReturn(item2).when(itemRepo).getById(item2.getId());

        // Act
        double result = orderService.getOrderTotalValue(order);

        // Assert
        double expected = item1.getValue() + item2.getValue();
        assertThat(result).isCloseTo(expected, within(0.01));
    }

    @Test
    void getOrderTotalValueWithEmptyOrderReturnsZero()
    {
        // Arrange
        Order emptyOrder = new Order(1, 10);
        doReturn(new ArrayList<>()).when(orderItemRepo).getByOrderId(emptyOrder.getId());

        // Act
        double result = orderService.getOrderTotalValue(emptyOrder);

        // Assert
        assertThat(result).isZero();
    }
}
