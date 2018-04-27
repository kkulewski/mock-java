package projekt2.services;

import projekt2.entities.Item;
import projekt2.repositories.ItemRepository;
import projekt2.repositories.OrderItemRepository;

import java.util.ArrayList;
import java.util.List;

public class ItemService
{
    private ItemRepository itemRepo;
    private OrderItemRepository orderItemRepo;

    public ItemService(ItemRepository itemRepo, OrderItemRepository orderItemRepo)
    {
        this.itemRepo = itemRepo;
        this.orderItemRepo = orderItemRepo;
    }

    public List<Item> getAllItems()
    {
        return itemRepo.getAll();
    }

    public List<Item> getAllNotOrderedItems()
    {
        List<Item> notOrdered = new ArrayList<>();
        for (Item it : getAllItems())
        {
            if (orderItemRepo.getByItemId(it.getId()) == null)
            {
                notOrdered.add(it);
            }
        }

        return notOrdered;
    }
}
