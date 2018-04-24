package Projekt2.Repositories;

import Projekt2.Entities.Item;
import java.util.List;

public interface ItemRepository
{
    Item getById(int itemId);
    List<Item> getAll();
    void add(Item item);
    void update(Item item);
    void delete(Item item);
}
