package org.shopping_list;

import org.shopping_list.api.IDatabaseService;
import org.shopping_list.commands.*;
import org.shopping_list.service.DatabaseService;

public class Main {
    public static void main(String[] args) {
        ConsoleMenu menu = new ConsoleMenu();
        menu.menuRun();
    }
}