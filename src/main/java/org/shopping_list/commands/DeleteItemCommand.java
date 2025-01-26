package org.shopping_list.commands;

import org.shopping_list.api.ICommand;

public class DeleteItemCommand extends BasicCommand implements ICommand {

    public DeleteItemCommand() {
        super();
    }

    @Override
    public void execute() {

        ioService.printLine("Введите ID продукта, который хотите удалить из списка:");
        try{
            int itemId = Integer.parseInt(ioService.readLine());
            itemService.deleteItem(itemId);
        } catch (NumberFormatException e) {
            ioService.printLine("Ошибка при парсинге");
        }
        ioService.printLine("Продукт успешно удалён");
    }

    @Override
    public String getDescription() {
        return "Удалить позицию";
    }
}
