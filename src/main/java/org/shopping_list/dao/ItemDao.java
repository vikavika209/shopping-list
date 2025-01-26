package org.shopping_list.dao;

import org.shopping_list.api.dao.IItemDao;
import org.shopping_list.model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class ItemDao extends Dao implements IItemDao {
    @Override
    public Item createItem(Item item) throws SQLException {
        String query = "INSERT INTO shopping_list (item_name, quantity, price, total_cost) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, item.getName());
            preparedStatement.setLong(2, item.getQuantity());
            preparedStatement.setDouble(3, item.getPrice());
            preparedStatement.setDouble(4, item.getTotalCost());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating item failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating item failed, no ID obtained.");
                }
            }
        }
        return item;
    }

    @Override
    public boolean updateItem(Item item) throws SQLException {
        String query = "UPDATE shopping_list SET item_name = ?, quantity = ?, price = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, item.getName());
            preparedStatement.setLong(2, item.getQuantity());
            preparedStatement.setDouble(3, item.getPrice());
            preparedStatement.setInt(4, item.getId());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Item getItemById(int id) throws SQLException {
        Item item = null;
        String query = "SELECT * FROM shopping_list WHERE id = ?";

        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setLong(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    item = new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("item_name"),
                            resultSet.getLong("quantity"),
                            resultSet.getLong("price")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return item;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<Item>();
        String query = "SELECT * FROM shopping_list";
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Item item = new Item(
                        resultSet.getInt("id"),
                        resultSet.getString("item_name"),
                        resultSet.getLong("quantity"),
                        resultSet.getLong("price")
                );
                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public boolean deleteItem(int id) {
        String query = "DELETE FROM shopping_list WHERE id = ?";
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAllItems() throws SQLException {
        String query = "DELETE FROM shopping_list";
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;
            }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
