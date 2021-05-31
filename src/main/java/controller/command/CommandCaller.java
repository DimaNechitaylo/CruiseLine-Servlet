package controller.command;

import java.util.HashMap;
import java.util.Map;

import controller.command.impl.GetCruisesCommand;
import controller.command.impl.LogOutCommand;
import controller.command.impl.ProfileCommand;
import controller.command.impl.SignInCommand;
import controller.command.impl.SignUpCommand;

public class CommandCaller {
    private final Map<String, Command> commands = new HashMap<String, Command>();

    public CommandCaller() {
        commands.put("SIGNUP", new SignUpCommand());
        commands.put("SIGNIN", new SignInCommand());
        commands.put("LOGOUT", new LogOutCommand());
        commands.put("PROFILE", new ProfileCommand());
        commands.put("GET_CRUISES", new GetCruisesCommand());
    }

    public Command call(String commandName) {
        return commands.get(commandName.toUpperCase());
    }
}
