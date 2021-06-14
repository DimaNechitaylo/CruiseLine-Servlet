package ua.training.controller.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import ua.training.controller.command.impl.ChangeLangCommand;
import ua.training.controller.command.impl.GetCruisesCommand;
import ua.training.controller.command.impl.GetFiltredCruisesCommand;
import ua.training.controller.command.impl.GetOrdersThatRequireProcessing;
import ua.training.controller.command.impl.LogOutCommand;
import ua.training.controller.command.impl.MainPageCommand;
import ua.training.controller.command.impl.OrderAdminOperationCommand;
import ua.training.controller.command.impl.OrderUserOperationCommand;
import ua.training.controller.command.impl.ProfileCommand;
import ua.training.controller.command.impl.SignInCommand;
import ua.training.controller.command.impl.SignUpCommand;
import ua.training.controller.command.impl.ViewCruiseCommand;

public class CommandCaller {
    private final Map<String, Command> commands = new HashMap<String, Command>();

    public CommandCaller() {
        commands.put("SIGNUP", new SignUpCommand());
        commands.put("SIGNIN", new SignInCommand());
        commands.put("LOGOUT", new LogOutCommand());
        commands.put("PROFILE", new ProfileCommand());
        commands.put("GET_CRUISES", new GetCruisesCommand());
        commands.put("VIEW_CRUISE", new ViewCruiseCommand());
        commands.put("FILTER", new GetFiltredCruisesCommand());
        commands.put("ORDER_USER_OPERATION", new OrderUserOperationCommand());
        commands.put("ORDER_ADMIN_OPERATION", new OrderAdminOperationCommand());
        commands.put("GET_ORDERS_THAT_REQUIRE_PROCESSING", new GetOrdersThatRequireProcessing());
        commands.put("CHANGE_LANG", new ChangeLangCommand());
        commands.put("MAIN", new MainPageCommand());
    }

	public Optional<Command> call(String commandName) {
		return Optional.ofNullable(commands.get(commandName.toUpperCase()));
    }
}
