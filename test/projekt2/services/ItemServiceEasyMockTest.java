package projekt2.services;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projekt2.entities.Item;
import projekt2.entities.OrderItem;
import projekt2.repositories.ItemRepository;
import projekt2.repositories.OrderItemRepository;
import projekt2.validators.ItemValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

class ItemServiceEasyMockTest
{
    private OrderItemRepository orderItemRepo;
    private ItemRepository itemRepo;
    private ItemValidator itemValidator;
    private ItemService is;

    private Item itemApple;
    private Item itemOrange;
    private Item itemCherry;

    @BeforeEach
    void setup()
    {
        orderItemRepo = EasyMock.createMock(OrderItemRepository.class);
        itemRepo = EasyMock.createMock(ItemRepository.class);
        itemValidator = EasyMock.createMock(ItemValidator.class);
        is = new ItemService(itemRepo, orderItemRepo, itemValidator);

        itemApple = new Item(1, "Apple", 2.0);
        itemOrange = new Item(2, "Orange", 1.5);
        itemCherry = new Item(3, "Cherry", 0.3);
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
        List<Item> items = Arrays.asList(itemApple, itemOrange);
        expect(itemRepo.getAll()).andReturn(items);
        replay(itemRepo);

        expect(orderItemRepo.getByItemId(itemApple.getId()))
                .andReturn(new OrderItem(1, itemApple.getId()));
        expect(orderItemRepo.getByItemId(itemOrange.getId()))
                .andReturn(new OrderItem(1, itemOrange.getId()));
        replay(orderItemRepo);

        // Act
        List<Item> result = is.getAllNotOrderedItems();

        // Assert
        assertThat(result).isEmpty();
    }

    @Test
    void getAllNotOrderedItemsWithSomeOrderedReturnsExpectedItems()
    {
        // Arrange
        List<Item> items = Arrays.asList(itemApple, itemOrange, itemCherry);
        expect(itemRepo.getAll()).andReturn(items);
        replay(itemRepo);

        expect(orderItemRepo.getByItemId(itemApple.getId()))
                .andReturn(null);
        expect(orderItemRepo.getByItemId(itemOrange.getId()))
                .andReturn(new OrderItem(1, itemOrange.getId()));
        expect(orderItemRepo.getByItemId(itemCherry.getId()))
                .andReturn(null);
        replay(orderItemRepo);

        // Act
        List<Item> result = is.getAllNotOrderedItems();

        // Assert
        assertThat(result).containsExactlyInAnyOrder(itemApple, itemCherry);
    }

    @Test
    void addItemWithInvalidItemReturnsFalse()
    {
        // Arrange
        Item item = null;
        expect(itemValidator.isValid(anyObject())).andReturn(false);
        replay(itemValidator);

        // Act
        boolean result = is.addItem(item);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    void addItemWithValidItemReturnsTrue()
    {
        // Arrange
        expect(itemValidator.isValid(itemApple)).andReturn(true);
        replay(itemValidator);
        expect(itemRepo.add(itemApple)).andReturn(true);
        replay(itemRepo);

        // Act
        boolean result = is.addItem(itemApple);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    void deleteItemWhenItemIsAlreadyInSomeOrderReturnsFalse()
    {
        // Arrange
        OrderItem someOrderItem = new OrderItem(1, 1);
        expect(itemRepo.getAll()).andReturn(Arrays.asList(itemApple));
        replay(itemRepo);
        expect(orderItemRepo.getByItemId(itemApple.getId())).andReturn(someOrderItem);
        replay(orderItemRepo);

        // Act
        boolean result = is.deleteItem(itemApple);

        // Assert
        assertThat(result).isFalse();
    }
}
