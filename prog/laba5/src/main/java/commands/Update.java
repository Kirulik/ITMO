package commands;

import managers.AskManager;
import managers.CollectionManager;
import moduls.Movie;
import utility.Console;
import utility.ExecutionResponse;

import java.util.List;

/**
 * Класс команды для обновления элемента коллекции по id
 */
public class Update extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Update(Console console, CollectionManager collectionManager) {
        super("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length < 2 || arguments[1].isEmpty()) {
            return new ExecutionResponse(false, "Не указан id!\nИспользование: 'update id {element}'");
        }
        long id;
        try {
            id = Long.parseLong(arguments[1]);
        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "id должен быть числом!");
        }
        Movie oldMovie = collectionManager.getById(id);
        if (oldMovie == null) {
            return new ExecutionResponse(false, "Элемент с id=" + id + " не найден!");
        }
        try {
            console.println("******** Обновление записи о фильме (id=" + id + ") ********");
            Movie newMovie = AskManager.askMovie(console, id);
            if (newMovie != null) {
                List<String> errList = newMovie.validate();
                if (errList.isEmpty()) {
                    collectionManager.remove(id);
                    collectionManager.add(newMovie);
                    return new ExecutionResponse("Элемент успешно обновлён!");
                } else {
                    return new ExecutionResponse(false, String.join("\n", errList));
                }
            }
        } catch (AskManager.AskBreak e) {
            return new ExecutionResponse(false, "Отмена...");
        }
        return null;
    }
} 