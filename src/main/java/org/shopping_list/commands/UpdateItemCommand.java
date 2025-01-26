package org.shopping_list.commands;

import org.shopping_list.api.ICommand;

import java.sql.SQLException;

public class UpdateItemCommand extends BasicCommand implements ICommand {
    @Override
    public void execute() {

        ioService.printLine("Введите ID продукта, который хотите изменить:");

        try{
            int itemId = Integer.parseInt(ioService.readLine());

            ioService.printLine("Введите новое наименование продукта:");
            String itemName = ioService.readLine();

            ioService.printLine("Введите необходимое количество:");
            long quantity = Long.parseLong(ioService.readLine());

            ioService.printLine("Введите цену:");
            long price = Long.parseLong(ioService.readLine());

            itemService.updateItem(itemId, itemName, price, quantity);

            ioService.printLine("Продукт с ID " + itemId + " успешно обновлён.");
        }catch (NumberFormatException | SQLException e){
            ioService.printLine(e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Обновить позицию";
    }
}
