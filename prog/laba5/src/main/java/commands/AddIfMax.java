package commands;

import managers.AskManager;
import managers.CollectionManager;
import moduls.Movie;
import utility.Console;
import utility.ExecutionResponse;

import java.util.Comparator;
import java.util.List;

/**
 * Класс команды для добавления элемента, если он больше максимального
 */
public class AddIfMax extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public AddIfMax(Console console, CollectionManager collectionManager) {
        super("add_if_max {element}", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
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
            Movie max = collectionManager.getCollection().stream().max(Comparator.naturalOrder()).orElse(null);
            if (max == null || newMovie.compareTo(max) > 0) {
                collectionManager.add(newMovie);
                return new ExecutionResponse("Элемент успешно добавлен!");
            } else {
                return new ExecutionResponse(false, "Элемент не превышает максимальный в коллекции!");
            }
        } catch (AskManager.AskBreak e) {
            return new ExecutionResponse(false, "Отмена...");
        }
    }
} 