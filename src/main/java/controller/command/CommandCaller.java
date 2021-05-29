package controller.command;

import java.util.HashMap;
import java.util.Map;

import controller.command.impl.SignUpCommand;

public class CommandCaller {
    private final Map<String, Command> commands = new HashMap();

    public CommandCaller() {
        commands.put("SIGNUP", new SignUpCommand());
    }

    public Command call(String commandName) {
        return commands.get(commandName.toUpperCase());
    }
}
