package org.shopping_list.commands;

import org.shopping_list.api.ICommand;

import java.sql.SQLException;

public class DeleteAllItemsCommand extends BasicCommand implements ICommand {
    @Override
    public void execute() throws SQLException {
        try {
            itemService.deleteAllItems();
            ioService.printLine("Список успешно очищен");
        } catch (SQLException e) {
            ioService.printLine("Ошибка при очистке списка: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Очистить список";
    }
}
