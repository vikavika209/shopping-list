package org.shopping_list.service;

import org.shopping_list.api.IItemService;
import org.shopping_list.dao.ItemDao;
import org.shopping_list.model.Item;

import java.sql.SQLException;
import java.util.List;

public class ItemService implements IItemService {
    private final ItemDao itemDao;

    public ItemService() {
        this.itemDao = new ItemDao();
    }

    @Override
    public Item createItem(String name, long price, long quantity) throws SQLException {
        Item item = new Item(name, quantity, price);
        itemDao.createItem(item);
        return item;
    }

    @Override
    public boolean updateItem(int id, String name, long price, long quantity) throws SQLException {
        Item item = itemDao.getItemById(id);
        item.setName(name);
        item.setPrice(price);
        item.setQuantity(quantity);

        return itemDao.updateItem(item);
    }

    @Override
    public Item getItemById(int id) throws SQLException {
        return itemDao.getItemById(id);
    }

    @Override
    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    @Override
    public boolean deleteItem(int id) {
        return itemDao.deleteItem(id);
    }

    @Override
    public long getTotalAmount() {
        return itemDao.getAllItems().stream()
                .mapToLong(Item::getTotalCost)
                .sum();
    }

    @Override
    public boolean deleteAllItems() throws SQLException {
        return itemDao.deleteAllItems();
    }
}
