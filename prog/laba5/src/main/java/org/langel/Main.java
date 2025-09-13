package org.langel;
import commands.*;
import managers.*;
import utility.StandartConsole;

/**
 * Главный класс приложения для управления коллекцией фильмов.
 * 
 * Этот класс является точкой входа в приложение и отвечает за:
 * - Инициализацию всех необходимых менеджеров
 * - Регистрацию доступных команд
 * - Запуск интерактивного режима работы
 * 
 * @author laba5
 * @version 1.0
 */
public class Main {
    
    /**
     * Главный метод приложения.
     * 
     * Процесс запуска:
     * 1. Проверка наличия аргумента командной строки (имя файла)
     * 2. Создание менеджеров для работы с данными и командами
     * 3. Загрузка коллекции из файла
     * 4. Регистрация всех доступных команд
     * 5. Запуск интерактивного режима
     * 
     * @param args аргументы командной строки (первый аргумент - имя файла с данными)
     */
    public static void main(String[] args) {
        // Создание консоли для взаимодействия с пользователем
        var console = new StandartConsole();
        
        // Проверка наличия аргумента командной строки
        if (args.length == 0) {
            console.println("Введите имя загружаемого файла как аргумент командной строки");
            System.exit(1);
        }
        
        // Инициализация менеджера для работы с файлами данных
        var dumpManager = new DumpManager(args[0], console);
        
        // Создание менеджера коллекции фильмов
        var collectionManager = new CollectionManager(dumpManager);
        
        // Загрузка коллекции из файла
        collectionManager.loadCollection();
        
        // Создание менеджера команд
        var commandManager = new CommandManager();
        
        // Регистрация всех доступных команд
        // Базовые команды для работы с коллекцией
        commandManager.register("help", new Help(console, commandManager));
        commandManager.register("info", new Info(console, collectionManager));
        commandManager.register("show", new Show(console, collectionManager));
        commandManager.register("add", new Add(console, collectionManager));
        commandManager.register("update", new Update(console, collectionManager));
        commandManager.register("remove_by_id", new RemoveById(console, collectionManager));
        commandManager.register("clear", new Clear(console, collectionManager));
        commandManager.register("save", new Save(console, collectionManager));
        
        // Команды для управления выполнением
        commandManager.register("execute_script", new ExecuteScript(console));
        commandManager.register("exit", new Exit(console));
        
        // Специальные команды для работы с коллекцией
        commandManager.register("add_if_max", new AddIfMax(console, collectionManager));
        commandManager.register("add_if_min", new AddIfMin(console, collectionManager));
        commandManager.register("remove_lower", new RemoveLower(console, collectionManager));
        commandManager.register("filter_less_than_screenwriter", new FilterLessThanScreenwriter(console, collectionManager));
        commandManager.register("print_descending", new PrintDescending(console, collectionManager));
        commandManager.register("sum_of_oscar_count", new SumOfOscarCount(console, collectionManager));
        
        // Создание и запуск интерактивного режима
        var runner = new Runner(console, commandManager);
        runner.interactiveMode();
    }
}