package commands;

import managers.AskManager;
import managers.CollectionManager;
import moduls.Movie;
import utility.Console;
import utility.ExecutionResponse;

import java.util.Comparator;
import java.util.List;

/**
 * Класс команды для добавления элемента, если он меньше минимального
 */
public class AddIfMin extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public AddIfMin(Console console, CollectionManager collectionManager) {
        super("add_if_min {element}", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        try {
            console.println("******** Ввод нового элемента ********");
            Movie newMovie = AskManager.askMovie(console, collectionManager.getFreeId());
            if (newMovie == null) return new ExecutionResponse(false, "Отмена...");
            List<String> errList = newMovie.validate();
            if (!errList.isEmpty()) return new ExecutionResponse(false, String.join("\n", errList));
            Movie min = collectionManager.getCollection().stream().min(Comparator.naturalOrder()).orElse(null);
            if (min == null || newMovie.compareTo(min) < 0) {
                collectionManager.add(newMovie);
                return new ExecutionResponse("Элемент успешно добавлен!");
            } else {
                return new ExecutionResponse(false, "Элемент не меньше минимального в коллекции!");
            }
        } catch (AskManager.AskBreak e) {
            return new ExecutionResponse(false, "Отмена...");
        }
    }
} 