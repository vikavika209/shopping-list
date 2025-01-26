package org.shopping_list.commands;

import org.shopping_list.api.ICommand;
import java.sql.SQLException;

public class AddItemCommand extends BasicCommand implements ICommand {

    public AddItemCommand() {
        super();
    }

    @Override
    public void execute() {

        ioService.printLine("Введите наименование продукта:");
        String itemName = ioService.readLine();

        ioService.printLine("Введите необходимое количество:");
        try{
            long quantity = Long.parseLong(ioService.readLine());

        ioService.printLine("Введите цену продукта за еденицу:");

            long price = Long.parseLong(ioService.readLine());

            itemService.createItem(itemName, price, quantity);

        }catch (NumberFormatException e){
            ioService.printLine("Ошибка при попытке парсинга цены");

        } catch (SQLException e) {
            ioService.printLine("Ошибка при сохранении продукта" + e.getMessage());
        }
        ioService.printLine("Продукт успешно добавлен");
    }

    @Override
    public String getDescription() {
        return "Добавить позицию в лист";
    }
}
