package commands;

import managers.AskManager;
import managers.CollectionManager;
import moduls.Movie;
import utility.Console;
import utility.ExecutionResponse;

import java.util.List;

/**
 * Класс команды для добавления нового элемента в коллекцию
 *
 */
public class Add extends Command {
    /**
     * Консоль
     */
    private final Console console;
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор
     *
     * @param console           консоль
     * @param collectionManager менеджер коллекции
     */
    public Add(Console console, CollectionManager collectionManager) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнение команды
     *
     * @param arguments массив с аргументами команды
     * @return возвращает ответ о выполнении команды
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        try {
            if (!arguments[1].isEmpty()) {
                return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
            }
            console.println("******** Создание новой записи о фильме ********");
            Movie d = AskManager.askMovie(console, collectionManager.getFreeId());
            if (d != null) {
                List<String> errList = d.validate();
                if (errList.isEmpty()){
                    collectionManager.add(d);
                    return new ExecutionResponse("Новый фильм успешно добавлен!");
                } else return new ExecutionResponse(false,  String.join("\n", errList));
            }
        } catch (AskManager.AskBreak e) {
            return new ExecutionResponse(false, "Отмена...");
        }
        return null;
    }

}