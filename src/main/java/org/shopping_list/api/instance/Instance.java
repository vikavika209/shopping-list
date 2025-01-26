package org.shopping_list.api.instance;

import org.shopping_list.api.IItemService;
import org.shopping_list.service.ItemService;

public class Instance {
    private IItemService itemService;

    public Instance() {
        this.itemService = new ItemService();
    }

    public IItemService getItemService() {
        if(itemService == null) {
            itemService = new ItemService();
            return itemService;
        } else {
            return itemService;
        }
    }
}
