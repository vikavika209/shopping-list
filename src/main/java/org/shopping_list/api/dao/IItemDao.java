package org.shopping_list.api.dao;

import org.shopping_list.model.Item;

import java.sql.SQLException;
import java.util.List;

public interface IItemDao {
    Item createItem(Item item) throws SQLException;
    boolean updateItem(Item item) throws SQLException;
    Item getItemById(int id) throws SQLException;
    List<Item> getAllItems();
    boolean deleteItem(int id);
    boolean deleteAllItems() throws SQLException;
}
