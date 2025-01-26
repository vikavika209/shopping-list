package org.shopping_list.commands;

import org.shopping_list.api.ICommand;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private final Map<Integer, ICommand> commands = new HashMap<>();

    // Регистрация команд в меню
    public void addCommand(int option, ICommand command) {
        commands.put(option, command);
    }

    // Отображение меню
    public void display() {
        System.out.println("Главное меню:");
        for (Map.Entry<Integer, ICommand> entry : commands.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getDescription());
        }
        System.out.print("Введите номер действия: ");
    }

    // Выполнение команды
    public void executeCommand(int option) {
        ICommand command = commands.get(option);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Ошибка: некорректный ввод. Попробуйте снова.");
        }
    }
}