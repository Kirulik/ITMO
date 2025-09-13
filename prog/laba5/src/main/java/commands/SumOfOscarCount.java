package commands;

import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Команда для вывода суммы значений поля {@code oscarsCount} для всех элементов коллекции.
 */
public class SumOfOscarCount extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    /**
     * Конструктор команды.
     *
     * @param console консоль для вывода сообщений
     * @param collectionManager менеджер коллекции фильмов
     */
    public SumOfOscarCount(Console console, CollectionManager collectionManager) {
        super("sum_of_oscar_count", "вывести сумму значений поля oscarsCount для всех элементов коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду суммирования значений {@code oscarsCount} по всем фильмам в коллекции.
     * Не принимает аргументов.
     *
     * @param arguments массив аргументов команды; {@code arguments[1]} должен быть пустым
     * @return результат выполнения с сообщением вида: {@code "Сумма oscarsCount: <число>"}
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length > 1 && !arguments[1].isEmpty()) {
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        }
        long sum = collectionManager.getCollection().stream().mapToLong(m -> m.getOscarsCount()).sum();
        return new ExecutionResponse("Сумма oscarsCount: " + sum);
    }
} 