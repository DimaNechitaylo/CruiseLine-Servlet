package controller.command;

import java.util.HashMap;
import java.util.Map;

import controller.command.impl.GetCruisesCommand;
import controller.command.impl.GetFiltredCruisesCommand;
import controller.command.impl.GetOrdersThatRequireProcessing;
import controller.command.impl.GetUserOrdersCommand;
import controller.command.impl.LogOutCommand;
import controller.command.impl.OrderAdminOperationCommand;
import controller.command.impl.ProfileCommand;
import controller.command.impl.SignInCommand;
import controller.command.impl.SignUpCommand;
import controller.command.impl.ViewCruiseCommand;

public class CommandCaller {
    private final Map<String, Command> commands = new HashMap<String, Command>();

    public CommandCaller() {
        commands.put("SIGNUP", new SignUpCommand());
        commands.put("SIGNIN", new SignInCommand());
        commands.put("LOGOUT", new LogOutCommand());
        commands.put("PROFILE", new ProfileCommand());
        commands.put("GET_CRUISES", new GetCruisesCommand());
        commands.put("VIEW_CRUISE", new ViewCruiseCommand());
        commands.put("USER_ORDERS", new GetUserOrdersCommand());
        commands.put("FILTER", new GetFiltredCruisesCommand());
        commands.put("ORDER_ADMIN_OPERATION", new OrderAdminOperationCommand());
        commands.put("GET_ORDERS_THAT_REQUIRE_PROCESSING", new GetOrdersThatRequireProcessing());
    }

    public Command call(String commandName) {
        return commands.get(commandName.toUpperCase());
    }
}
