package org.shopping_list.commands;

import org.shopping_list.api.ICommand;

public class ExitCommand extends BasicCommand implements ICommand {
    public ExitCommand() {
        super();
    }

    @Override
    public void execute() {

        ioService.printLine("До свидания!");
        ioService.close();
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "Выйти";
    }
}