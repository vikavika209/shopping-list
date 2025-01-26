package org.shopping_list.commands;

import org.shopping_list.api.ICommand;

public class GetTotalAmountCommand extends BasicCommand implements ICommand {
    @Override
    public void execute() {
        ioService.printLine("Общая сумма списка:");
        ioService.printLine(String.valueOf(itemService.getTotalAmount()));
    }

    @Override
    public String getDescription() {
        return "Общая сумма";
    }
}
