package utility;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class StandartConsole implements Console {
    private static final String P = "$ ";
    private static Scanner fileScanner = null;
    private static Scanner consoleScanner = new Scanner(System.in);

    /**
     * Выводит obj.toString() в консоль
     *
     * @param obj Объект для печати
     */
    public void print(Object obj) {
        System.out.print(obj);
        System.out.flush();
    }

    /**
     * Выводит obj.toString() + \n в консоль
     *
     * @param obj Объект для печати
     */
    public void println(Object obj) {
        System.out.println(obj);
        System.out.flush();
    }

    /**
     * Выводит ошибка: obj.toString() в консоль
     *
     * @param obj Ошибка для печати
     */
    public void printError(Object obj) {
        System.err.println("Error: " + obj);
        System.err.flush();
    }

    /**
     * Чтение из файла или консоли
     *
     * @return возвращает следующую строку
     * @throws NoSuchElementException если достигнут конец потока
     */
    public String readln() {
        try {
            if (fileScanner != null) {
                if (!fileScanner.hasNextLine()) {
                    throw new NoSuchElementException("Достигнут конец файла");
                }
                String line = fileScanner.nextLine();
                if (line != null && !line.isEmpty() && line.charAt(0) == '\uFEFF') {
                    line = line.substring(1);
                }
                return line;
            } else {
                if (!consoleScanner.hasNextLine()) {
                    throw new NoSuchElementException("Достигнут конец потока ввода");
                }
                String line = consoleScanner.nextLine();
                if (line == null) {
                    throw new NoSuchElementException("Достигнут конец потока ввода");
                }
                if (!line.isEmpty() && line.charAt(0) == '\uFEFF') {
                    line = line.substring(1);
                }
                return line;
            }
        } catch (Exception e) {
            throw new IllegalStateException("Ошибка чтения: " + e.getMessage());
        }
    }

    public boolean isCanReadln() throws IllegalStateException {
        try {
            if (fileScanner != null) {
                return fileScanner.hasNextLine();
            } else {
                return consoleScanner.hasNextLine();
            }
        } catch (Exception e) {
            throw new IllegalStateException("Ошибка проверки доступности ввода: " + e.getMessage());
        }
    }

    /**
     * Выводит таблицу из 2 колонок
     *
     * @param elementLeft  Левый элемент колонки.
     * @param elementRight Правый элемент колонки.
     */
    public void printTable(Object elementLeft, Object elementRight) {
        System.out.printf(" %-42s  %-1s%n", elementLeft, elementRight);
        System.out.flush();
    }

    /**
     * Выводит prompt1 текущей консоли
     */
    public void prompt() {
        print(P);
    }

    /**
     * @return prompt1 текущей консоли
     */
    public String getPrompt() {
        return P;
    }

    /**
     * заменяет сканер на введенный
     *
     * @param scanner новый сканер
     */
    public void selectFileScanner(Scanner scanner) {
        StandartConsole.fileScanner = scanner;
    }

    /**
     * Возврашение к сканеру из консоли
     */
    public void selectConsoleScanner() {
        StandartConsole.fileScanner = null;
    }
}