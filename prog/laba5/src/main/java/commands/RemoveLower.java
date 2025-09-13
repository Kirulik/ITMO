package commands;

import managers.AskManager;
import managers.CollectionManager;
import moduls.Movie;
import utility.Console;
import utility.ExecutionResponse;

import java.util.Iterator;

/**
 * Класс команды для удаления всех элементов, меньших заданного
 */
public class RemoveLower extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public RemoveLower(Console console, CollectionManager collectionManager) {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        try {
            console.println("******** Ввод элемента для сравнения ********");
            Movie compareMovie = AskManager.askMovie(console, collectionManager.getFreeId());
            if (compareMovie == null) return new ExecutionResponse(false, "Отмена...");
            int removedCount = 0;
            Iterator<Movie> iterator = collectionManager.getCollection().iterator();
            while (iterator.hasNext()) {
                Movie movie = iterator.next();
                if (movie.compareTo(compareMovie) < 0) {
                    iterator.remove();
                    collectionManager.remove(movie.getId());
                    removedCount++;
                }
            }
            collectionManager.sort();
            return new ExecutionResponse("Удалено элементов: " + removedCount);
        } catch (AskManager.AskBreak e) {
            return new ExecutionResponse(false, "Отмена...");
        }
    }
} 