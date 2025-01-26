package org.shopping_list.commands;

import org.shopping_list.api.ICommand;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConsoleMenu extends BasicCommand implements ICommand {
    protected final Map<Integer, ICommand> commands;

    public ConsoleMenu() {
        this.commands = new HashMap<>();
    }

    private <T extends ICommand> void addCommand(Class<T> commandClass) {
        try{
            ICommand commandInstance = commandClass.getDeclaredConstructor().newInstance();

            int optionNumber = commands.size() + 1;

            commands.put(optionNumber, commandInstance);

        }catch (Exception e) {
            ioService.printLine("Не удалось создать экземпляр команды" + e);
    }
        }

    private void display() {
        System.out.println("Главное меню:");
        for (Map.Entry<Integer, ICommand> entry : commands.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getDescription());
        }
    }

    private void executeCommand(int option) throws SQLException {
        ICommand command = commands.get(option);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Ошибка: некорректный ввод. Попробуйте снова.");
        }
    }

    @Override
    public void execute() {
        try {
            int option = Integer.parseInt(ioService.readLine());
            executeCommand(option);

        } catch (NumberFormatException e) {
            ioService.printLine("Номер пункта меню введен некорректно.");
        } catch (SQLException e) {
            ioService.printLine(e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        display();
        return "Введите номер действия: ";
    }

    public void menuRun(){
        addCommand(AddItemCommand.class);
        addCommand(GetAllItemsCommand.class);
        addCommand(UpdateItemCommand.class);
        addCommand(DeleteItemCommand.class);
        addCommand(GetTotalAmountCommand.class);
        addCommand(DeleteAllItemsCommand.class);
        addCommand(ExitCommand.class);

        while (true) {
            getDescription();
            execute();
            ioService.printLine("");
        }
    }
}