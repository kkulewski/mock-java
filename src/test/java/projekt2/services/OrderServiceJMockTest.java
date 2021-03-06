package projekt2.services;

import org.jmock.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projekt2.entities.Client;
import projekt2.entities.Item;
import projekt2.entities.Order;
import projekt2.entities.OrderItem;
import projekt2.repositories.ItemRepository;
import projekt2.repositories.OrderItemRepository;
import projekt2.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceJMockTest
{
    private Mockery context = new Mockery();
    private OrderRepository orderRepo;
    private OrderItemRepository orderItemRepo;
    private ItemRepository itemRepo;
    private OrderService orderService;

    @BeforeEach
    void setup()
    {
        orderRepo = context.mock(OrderRepository.class);
        orderItemRepo = context.mock(OrderItemRepository.class);
        itemRepo = context.mock(ItemRepository.class);
        orderService = new OrderService(orderRepo, orderItemRepo, itemRepo);
    }

    @Test
    void getClientOrdersReturnsExpectedOrders()
    {
        // Arrange
        Client client = new Client(1, "John", "Doe", "johndoe@email.com");
        List<Order> expectedOrders = Arrays.asList(new Order(1, client.getId()), new Order(2, client.getId()));
        context.checking(new Expectations(){{
            oneOf(orderRepo).getByClientId(client.getId()); will(returnValue(expectedOrders));
        }});

        // Act
        List<Order> result = orderService.getClientOrders(client);

        // Assert
        assertThat(result).containsAll(expectedOrders);
        context.assertIsSatisfied();
    }

    @Test
    void addItemToOrderReturnsFalseWhenItemIsAlreadyInAnotherOrder()
    {
        // Arrange
        Order myOrder = new Order(2, 1);
        Order anotherOrder = new Order(1, 10);
        Item item = new Item(1, "uKeyboard", 100.0);
        OrderItem orderItem = new OrderItem(anotherOrder.getId(), item.getId());
        context.checking(new Expectations(){{
            oneOf(orderItemRepo).getByItemId(item.getId()); will(returnValue(orderItem));
            never(orderItemRepo).add(with(any(OrderItem.class)));
        }});

        // Act
        boolean wasAdded = orderService.addItemToOrder(item, myOrder);

        // Assert
        assertThat(wasAdded).isFalse();
        context.assertIsSatisfied();
    }

    @Test
    void addItemToOrderReturnsTrueWhenItemWasAdded()
    {
        // Arrange
        Order myOrder = new Order(2, 1);
        Item item = new Item(1, "Something", 15.0);
        context.checking(new Expectations(){{
            oneOf(orderItemRepo).getByItemId(item.getId()); will(returnValue(null));
            oneOf(orderItemRepo).add(with(any(OrderItem.class))); will(returnValue(true));
        }});

        // Act
        boolean wasAdded = orderService.addItemToOrder(item, myOrder);

        // Assert
        assertThat(wasAdded).isTrue();
        context.assertIsSatisfied();
    }

    @Test
    void getItemsForGivenOrderReturnsExpectedItemSet()
    {
        // Arrange
        Order order = new Order(1, 10);
        Item item1 = new Item(1, "SomeItem", 20.0);
        Item item2 = new Item(2, "OtherItem", 250.0);
        OrderItem orderItem1 = new OrderItem(order.getId(), item1.getId());
        OrderItem orderItem2 = new OrderItem(order.getId(), item2.getId());
        List<OrderItem> orderItems = Arrays.asList(orderItem1, orderItem2);
        context.checking(new Expectations(){{
            oneOf(orderItemRepo).getByOrderId(order.getId()); will(returnValue(orderItems));
            oneOf(itemRepo).getById(item1.getId()); will(returnValue(item1));
            oneOf(itemRepo).getById(item2.getId()); will(returnValue(item2));
        }});

        // Act
        List<Item> result = orderService.getItemsForGivenOrder(order);

        // Assert
        assertThat(result).containsExactlyInAnyOrder(item1, item2);
        context.assertIsSatisfied();
    }

    @Test
    void getItemsForGivenOrderWithEmptyOrderReturnsEmptyList()
    {
        // Arrange
        Order emptyOrder = new Order(1, 10);
        context.checking(new Expectations(){{
            oneOf(orderItemRepo).getByOrderId(emptyOrder.getId()); will(returnValue(new ArrayList<>()));
        }});

        // Act
        List<Item> result = orderService.getItemsForGivenOrder(emptyOrder);

        // Assert
        assertThat(result).isEmpty();
        context.assertIsSatisfied();
    }
}
