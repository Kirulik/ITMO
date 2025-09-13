package commands;

import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Класс команды для удаления элемента по id
 */
public class RemoveById extends Command {
    private final CollectionManager collectionManager;

    public RemoveById(Console console, CollectionManager collectionManager) {
        super("remove_by_id id", "удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length < 2 || arguments[1].isEmpty()) {
            return new ExecutionResponse(false, "Не указан id!\nИспользование: 'remove_by_id id'");
        }
        long id;
        try {
            id = Long.parseLong(arguments[1]);
        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "id должен быть числом!");
        }
        boolean removed = collectionManager.remove(id);
        if (removed) {
            return new ExecutionResponse("Элемент с id=" + id + " успешно удалён!");
        } else {
            return new ExecutionResponse(false, "Элемент с id=" + id + " не найден!");
        }
    }
} 