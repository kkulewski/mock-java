package projekt2.repositories;

import projekt2.entities.Item;
import java.util.List;

public interface ItemRepository
{
    Item getById(int itemId);
    List<Item> getAll();
    void add(Item item);
    void update(Item item);
    void delete(Item item);
}
