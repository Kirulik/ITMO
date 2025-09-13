package org.langel;

import managers.CommandManager;
import utility.Console;
import utility.ExecutionResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс Runner отвечает за выполнение команд в интерактивном режиме и режиме скрипта.
 * 
 * Основные функции:
 * - Обработка пользовательского ввода в интерактивном режиме
 * - Выполнение команд из файлов скриптов
 * - Контроль рекурсии при выполнении скриптов
 * - Управление переключением между консолью и файловым вводом
 *
 */
public class Runner {
    /** Консоль для взаимодействия с пользователем */
    private final Console console;
    
    /** Менеджер команд для выполнения операций */
    private final CommandManager commandManager;
    
    /** Стек выполняемых скриптов для контроля рекурсии */
    private final List<String> scriptStack = new ArrayList<>();
    
    /** Максимальная глубина рекурсии при выполнении скриптов */
    private int lengthRecursion = -1;

    /**
     * Конструктор класса Runner.
     * 
     * @param console консоль для ввода/вывода
     * @param commandManager менеджер команд
     */
    public Runner(Console console, CommandManager commandManager) {
        this.console = console;
        this.commandManager = commandManager;
    }

    /**
     * Запускает интерактивный режим работы приложения.
     * 
     * В этом режиме:
     * - Приложение ожидает ввода команд от пользователя
     * - Каждая команда обрабатывается и выполняется
     * - Результат выполнения выводится на экран
     * - Работа продолжается до команды "exit"
     * 
     * Обрабатывает исключения:
     * - NoSuchElementException: отсутствие пользовательского ввода
     * - IllegalStateException: непредвиденные ошибки
     */
    public void interactiveMode() {
        try {
            ExecutionResponse commandStatus;
            String[] userCommand = {"", ""};
            
            // Основной цикл обработки команд
            while (true) {
                console.prompt();
                // Разбор введенной команды на название и аргументы
                userCommand = (console.readln().trim() + " ").split("\\s+", 2);
                userCommand[1] = userCommand[1].trim();
                
                // Выполнение команды
                commandStatus = launchCommand(userCommand);
                
                // Проверка на выход из приложения
                if (commandStatus.getMessage().equals("exit")) break;
                
                // Вывод результата выполнения
                console.println(commandStatus.getMessage());
            }
        } catch (NoSuchElementException exception) {
            console.printError("Пользовательский ввод не обнаружен!");
        } catch (IllegalStateException exception) {
            console.printError("Непредвиденная ошибка!");
        }
    }

    /**
     * Проверяет возможность выполнения рекурсивного скрипта.
     * 
     * Метод контролирует глубину рекурсии при выполнении скриптов:
     * - Отслеживает уже выполняемые скрипты
     * - Запрашивает максимальную глубину рекурсии у пользователя
     * - Проверяет, не превышена ли допустимая глубина
     * 
     * @param argument путь к скрипту для проверки
     * @param scriptScanner сканер для чтения скрипта
     * @return true если рекурсия допустима, false если превышен лимит
     */
    private boolean checkRecursion(String argument, Scanner scriptScanner) {
        var recStart = -1;
        var i = 0;
        
        // Проверка всех скриптов в стеке на наличие рекурсии
        for (String script : scriptStack) {
            i++;
            if (argument.equals(script)) {
                if (recStart < 0) recStart = i;
                
                // Запрос максимальной глубины рекурсии при первом обнаружении
                if (lengthRecursion < 0) {
                    console.selectConsoleScanner();
                    console.println("Была замечена рекурсия! Введите максимальную глубину рекурсии (0..500)");
                    
                    // Валидация введенного значения
                    while (lengthRecursion < 0 || lengthRecursion > 500) {
                        try {
                            console.print("> ");
                            lengthRecursion = Integer.parseInt(console.readln().trim());
                            if (lengthRecursion < 0 || lengthRecursion > 500) {
                                console.println("длина не распознана");
                            }
                        } catch (NumberFormatException e) {
                            console.println("длина не распознана");
                        }
                    }
                    console.selectFileScanner(scriptScanner);
                }
                
                // Проверка превышения лимита рекурсии
                if (i > recStart + lengthRecursion || i > 500)
                    return false;
            }
        }
        return true;
    }

    /**
     * Выполняет команды из файла скрипта.
     * 
     * Процесс выполнения:
     * 1. Проверка существования и доступности файла
     * 2. Добавление скрипта в стек выполняемых
     * 3. Чтение и выполнение команд из файла
     * 4. Обработка рекурсивных вызовов
     * 5. Сбор результатов выполнения
     * 
     * @param argument путь к файлу скрипта
     * @return результат выполнения скрипта
     */
    private ExecutionResponse scriptMode(String argument) {
        String[] userCommand = {"", ""};
        StringBuilder executionOutput = new StringBuilder();
        
        // Проверка существования файла
        if (!new File(argument).exists()) 
            return new ExecutionResponse(false, "Файл не существует!");
        
        // Проверка прав на чтение
        if (!Files.isReadable(Paths.get(argument))) 
            return new ExecutionResponse(false, "Нет прав для чтения!");
        
        // Добавление скрипта в стек
        scriptStack.add(argument);
        
        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            ExecutionResponse commandStatus;
            
            // Проверка, что файл не пустой
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            
            // Переключение на чтение из файла
            console.selectFileScanner(scriptScanner);
            
            // Основной цикл выполнения команд из скрипта
            do {
                // Чтение команды из файла
                userCommand = (console.readln().trim() + " ").split("\\s+", 2);
                userCommand[1] = userCommand[1].trim();
                
                // Пропуск пустых строк
                while (console.isCanReadln() && userCommand[0].isEmpty()) {
                    userCommand = (console.readln().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                
                // Добавление команды в вывод
                executionOutput.append(console.getPrompt() + String.join(" ", userCommand) + "\n");
                
                var needLaunch = true;
                
                // Специальная обработка команды execute_script
                if (userCommand[0].equals("execute_script")) {
                    needLaunch = checkRecursion(userCommand[1], scriptScanner);
                }
                
                // Выполнение команды или возврат сообщения о превышении рекурсии
                commandStatus = needLaunch ? launchCommand(userCommand) : 
                               new ExecutionResponse("Превышена максимальная глубина рекурсии");
                
                // Восстановление файлового сканера после execute_script
                if (userCommand[0].equals("execute_script")) 
                    console.selectFileScanner(scriptScanner);
                
                // Добавление результата в вывод
                executionOutput.append(commandStatus.getMessage() + "\n");
                
            } while (commandStatus.getExitCode() && !commandStatus.getMessage().equals("exit") && console.isCanReadln());
            
            // Возврат к консольному вводу
            console.selectConsoleScanner();
            
            // Проверка корректности выполнения
            if (!commandStatus.getExitCode() && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty())) {
                executionOutput.append("Проверьте скрипт на корректность введенных данных!\n");
            }
            
            return new ExecutionResponse(commandStatus.getExitCode(), executionOutput.toString());
            
        } catch (FileNotFoundException exception) {
            return new ExecutionResponse(false, "Файл со скриптом не найден!");
        } catch (NoSuchElementException exception) {
            return new ExecutionResponse(false, "Файл со скриптом пуст!");
        } catch (IllegalStateException exception) {
            console.printError("Непредвиденная ошибка!");
            System.exit(0);
        } finally {
            // Удаление скрипта из стека
            scriptStack.remove(scriptStack.size() - 1);
        }
        return new ExecutionResponse("");
    }

    /**
     * Запускает выполнение команды.
     * 
     * Метод:
     * - Проверяет существование команды
     * - Обрабатывает специальную команду execute_script
     * - Выполняет обычные команды через CommandManager
     * 
     * @param userCommand массив с названием команды и её аргументами
     * @return результат выполнения команды
     */
    private ExecutionResponse launchCommand(String[] userCommand) {
        // Пропуск пустых команд
        if (userCommand[0].equals("")) return new ExecutionResponse("");
        
        // Получение команды из менеджера
        var command = commandManager.getCommands().get(userCommand[0]);
        
        // Проверка существования команды
        if (command == null)
            return new ExecutionResponse(false, "Команда '" + userCommand[0] + "' не найдена. Наберите 'help' для справки");
        
        // Специальная обработка команды execute_script
        switch (userCommand[0]) {
            case "execute_script" -> {
                // Сначала выполняем валидацию команды
                ExecutionResponse tmp = commandManager.getCommands().get("execute_script").apply(userCommand);
                if (!tmp.getExitCode()) return tmp;
                
                // Затем выполняем скрипт
                ExecutionResponse tmp2 = scriptMode(userCommand[1]);
                return new ExecutionResponse(tmp2.getExitCode(), tmp.getMessage() + "\n" + tmp2.getMessage().trim());
            }
            default -> {
                // Выполнение обычных команд
                return command.apply(userCommand);
            }
        }
    }
} 