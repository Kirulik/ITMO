package commands;

import utility.Console;
import utility.ExecutionResponse;

/**
 * Класс команды для исполнения скрипта из файла
 */
public class ExecuteScript extends Command {
    @SuppressWarnings("unused")
    private final Console console;

    public ExecuteScript(Console console) {
        super("execute_script file_name", "считать и исполнить скрипт из указанного файла");
        this.console = console;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length < 2 || arguments[1].isEmpty()) {
            return new ExecutionResponse(false, "Не указано имя файла!\nИспользование: 'execute_script file_name'");
        }
        return new ExecutionResponse("execute_script: аргумент принят, запускаю выполнение скрипта");
    }
}