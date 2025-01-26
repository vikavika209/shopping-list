package org.shopping_list.api;

import java.sql.SQLException;

public interface ICommand {
    void execute() throws SQLException;
    String getDescription();
}
