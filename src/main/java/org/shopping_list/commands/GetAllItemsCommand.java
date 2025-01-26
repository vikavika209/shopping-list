package org.shopping_list.commands;

import org.shopping_list.api.ICommand;
import org.shopping_list.model.Item;

import java.util.List;

public class GetAllItemsCommand extends BasicCommand implements ICommand {
    public GetAllItemsCommand() {
        super();
    }

    @Override
    public void execute() {

        List<Item> allItems = itemService.getAllItems();

        ioService.printLine("Список всех продуктов:");

        for (Item item : allItems) {
            ioService.printLine(item.toString());
        }
    }

    @Override
    public String getDescription() {
        return "Получить все позиции";
    }
}

