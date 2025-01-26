package org.shopping_list.commands;

import org.shopping_list.api.ICommand;
import org.shopping_list.api.IItemService;
import org.shopping_list.api.IOService;
import org.shopping_list.api.instance.Instance;
import org.shopping_list.service.ConsoleIOService;

import java.util.HashMap;
import java.util.Map;

public abstract class BasicCommand {
    protected final IOService ioService;
    protected final Instance instance;
    protected final IItemService itemService;

    public BasicCommand() {
        this.instance = new Instance();
        this.itemService = instance.getItemService();
        this.ioService = new ConsoleIOService();
    }

}
