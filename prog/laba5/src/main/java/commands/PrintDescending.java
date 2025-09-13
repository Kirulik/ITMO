package commands;

import managers.CollectionManager;
import moduls.Movie;
import utility.Console;
import utility.ExecutionResponse;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс команды для вывода элементов в порядке убывания ID
 */
public class PrintDescending extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public PrintDescending(Console console, CollectionManager collectionManager) {
        super("print_descending", "вывести элементы коллекции в порядке убывания ID");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        }
        
        List<Movie> sorted = collectionManager.getCollection().stream()
            .sorted(Comparator.comparingLong(Movie::getId).reversed())
            .collect(Collectors.toList());
            
        if (sorted.isEmpty()) return new ExecutionResponse("Коллекция пуста!");
        String result = sorted.stream().map(Movie::toString).collect(Collectors.joining("\n\n"));
        return new ExecutionResponse(result);
    }
} 