package projekt2.services;

import projekt2.entities.Item;
import projekt2.repositories.ItemRepository;
import projekt2.repositories.OrderItemRepository;
import projekt2.validators.ItemValidator;

import java.util.ArrayList;
import java.util.List;

public class ItemService
{
    private ItemRepository itemRepo;
    private OrderItemRepository orderItemRepo;
    private ItemValidator itemValidator;

    public ItemService(ItemRepository itemRepo, OrderItemRepository orderItemRepo, ItemValidator itemValidator)
    {
        this.itemRepo = itemRepo;
        this.orderItemRepo = orderItemRepo;
        this.itemValidator = itemValidator;
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

    public boolean addItem(Item item)
    {
        if (!itemValidator.isValid(item))
        {
            return false;
        }

        return itemRepo.add(item);
    }

    public boolean deleteItem(Item item)
    {
        List<Item> notOrdered = getAllNotOrderedItems();
        if (!notOrdered.contains(item))
        {
            return false;
        }

        return itemRepo.delete(item);
    }
}
