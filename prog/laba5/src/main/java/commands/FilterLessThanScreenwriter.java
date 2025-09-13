package commands;

import managers.AskManager;
import managers.CollectionManager;
import moduls.Movie;
import moduls.Person;
import utility.Console;
import utility.ExecutionResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Команда для фильтрации фильмов по сценаристу.
 * 
 * Эта команда позволяет найти все фильмы, у которых сценарист "меньше" 
 * заданного сценариста. Сравнение происходит на основе естественного 
 * порядка сортировки класса Person (обычно по имени, а затем по другим полям).
 * 
 * Алгоритм работы:
 * 1. Запрашивает у пользователя данные сценариста для сравнения
 * 2. Фильтрует коллекцию фильмов, оставляя только те, у которых:
 *    - Сценарист не равен null
 *    - Сценарист "меньше" заданного (согласно compareTo)
 * 3. Возвращает отфильтрованный список в виде строки
 * 
 * Использование: filter_less_than_screenwriter screenwriter
 * 
 * @author laba5
 * @version 1.0
 */
public class FilterLessThanScreenwriter extends Command {
    /** Консоль для взаимодействия с пользователем */
    private final Console console;
    
    /** Менеджер коллекции фильмов для выполнения операций */
    private final CollectionManager collectionManager;

    /**
     * Конструктор команды фильтрации по сценаристу.
     * 
     * @param console консоль для ввода/вывода данных
     * @param collectionManager менеджер коллекции фильмов
     */
    public FilterLessThanScreenwriter(Console console, CollectionManager collectionManager) {
        super("filter_less_than_screenwriter screenwriter", "вывести элементы, значение поля screenwriter которых меньше заданного");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет фильтрацию фильмов по сценаристу.
     * 
     * Процесс выполнения:
     * 1. Запрашивает данные сценариста для сравнения через AskManager
     * 2. Если пользователь отменил ввод, возвращает сообщение об отмене
     * 3. Фильтрует коллекцию фильмов, используя Stream API:
     *    - Исключает фильмы с null-сценаристом
     *    - Оставляет только фильмы, где сценарист "меньше" заданного
     * 4. Если подходящих фильмов нет, возвращает соответствующее сообщение
     * 5. Формирует результат в виде строки с описаниями найденных фильмов
     * 
     * Обрабатываемые исключения:
     * - AskManager.AskBreak: пользователь прервал ввод данных
     * 
     * @param arguments аргументы команды (в данной команде не используются)
     * @return результат выполнения команды с отфильтрованными фильмами
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        try {
            // Запрос данных сценариста для сравнения
            console.println("******** Ввод screenwriter для сравнения ********");
            Person comparePerson = AskManager.askScreenwriter(console);
            
            // Проверка на отмену ввода
            if (comparePerson == null) return new ExecutionResponse(false, "Отмена...");
            
            // Фильтрация коллекции фильмов
            // Используем Stream API для эффективной обработки
            List<Movie> filtered = collectionManager.getCollection().stream()
                // Фильтруем фильмы: исключаем null-сценаристов и оставляем "меньшие"
                .filter(m -> m.getScreenwriter() != null && m.getScreenwriter().compareTo(comparePerson) < 0)
                .collect(Collectors.toList());
            
            // Проверка наличия подходящих фильмов
            if (filtered.isEmpty()) return new ExecutionResponse("Нет подходящих элементов.");
            
            // Формирование результата: преобразование фильмов в строки и объединение
            String result = filtered.stream()
                .map(Movie::toString)
                .collect(Collectors.joining("\n\n"));
            
            return new ExecutionResponse(result);
            
        } catch (AskManager.AskBreak e) {
            // Обработка прерывания ввода пользователем
            return new ExecutionResponse(false, "Отмена...");
        }
    }
} 