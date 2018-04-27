package projekt2.services;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projekt2.entities.Item;
import projekt2.entities.OrderItem;
import projekt2.repositories.ItemRepository;
import projekt2.repositories.OrderItemRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

class ItemServiceEasyMockTest
{
    private OrderItemRepository orderItemRepo;
    private ItemRepository itemRepo;
    private ItemService is;

    @BeforeEach
    void setup()
    {
        orderItemRepo = EasyMock.createMock(OrderItemRepository.class);
        itemRepo = EasyMock.createMock(ItemRepository.class);
        is = new ItemService(itemRepo, orderItemRepo);
    }

    @Test
    void getAllItemsWhenNoItemsAvailableReturnsEmptyList()
    {
        // Arrange
        expect(itemRepo.getAll()).andReturn(new ArrayList<>());
        replay(itemRepo);

        // Act
        List<Item> items = is.getAllItems();

        // Assert
        assertThat(items).isEmpty();
    }

    @Test
    void getAllNotOrderedItemsWithAllOrderedReturnsEmptyList()
    {
        // Arrange
        Item item1 = new Item(1, "Apple", 2.0);
        Item item2 = new Item(2, "Orange", 1.5);
        List<Item> items = Arrays.asList(item1, item2);
        expect(itemRepo.getAll()).andReturn(items);
        replay(itemRepo);

        expect(orderItemRepo.getByItemId(item1.getId())).andReturn(new OrderItem(1, item1.getId()));
        expect(orderItemRepo.getByItemId(item2.getId())).andReturn(new OrderItem(1, item2.getId()));
        replay(orderItemRepo);

        // Act
        List<Item> result = is.getAllNotOrderedItems();

        // Assert
        assertThat(result).isEmpty();
    }
}
