package projekt2.services;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projekt2.entities.Item;
import projekt2.repositories.ItemRepository;
import projekt2.repositories.OrderItemRepository;

import java.util.ArrayList;
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
}
