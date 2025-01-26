package org.shopping_list.api;

import org.shopping_list.model.Item;

import java.sql.SQLException;
import java.util.List;

public interface IItemService {
        Item createItem(String name, long price, long quantity) throws SQLException;
        boolean updateItem(int id, String name, long price, long quantity) throws SQLException;
        Item getItemById(int id) throws SQLException;
        List<Item> getAllItems();
        boolean deleteItem(int id);
        long getTotalAmount();
        boolean deleteAllItems() throws SQLException;
}
