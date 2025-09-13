package managers;

import moduls.*;
import utility.Console;

import java.time.LocalDate;
import java.util.NoSuchElementException;

public class AskManager {
    public static class AskBreak extends Exception {}

    public static Movie askMovie(Console console, long id) throws AskBreak {
        try {
            console.print("\n=== Создание нового фильма ===\n");

            String name = askString(console, "Название фильма");
            long oscarsCount = askLong(console, "Количество оскаров", 1, Long.MAX_VALUE);
            var coordinates = askCoordinates(console);
            var genre = askGenre(console);
            var mpaaRating = askMpaaRating(console);
            var screenWriter = askScreenwriter(console);

            return new Movie(id, name, coordinates, LocalDate.now(), oscarsCount, genre, mpaaRating, screenWriter);

        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    public static Person askScreenwriter(Console console) throws AskBreak {
        try {
            console.println("\n---Информация о сценаристе---\n");
            String name = askString(console, "Имя сценариста");
            Float height = askFloat(console, "Рост сценариста", 0, Float.MAX_VALUE);
            Color eyeColor = askEnum(console, "Цвет глаз сценариста", Color.class);
            var location = askLocation(console);

            return new Person(name, height, eyeColor, location);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    private static Location askLocation(Console console) throws AskBreak {
        try {
            console.println("\n--- Информация о местоположении ---");

            double x = askDouble(console, "Координата X", -Double.MAX_VALUE, Double.MAX_VALUE);
            long y = askLong(console, "Координата Y", Long.MIN_VALUE, Long.MAX_VALUE);
            Long z = askLong(console, "Координата Z", Long.MIN_VALUE, Long.MAX_VALUE);
            String name = askString(console, "Название места");

            return new Location(x, y, z, name);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    private static MpaaRating askMpaaRating(Console console) throws AskBreak {
        return askEnum(console, "Рейтинг Mpaa", MpaaRating.class);
    }

    private static MovieGenre askGenre(Console console) throws AskBreak {
        return askEnum(console, "Жанр фильма", MovieGenre.class);
    }


    private static Coordinates askCoordinates(Console console) throws AskBreak {
        try {
            console.println("\n--- Координаты ---");

            int x = askInt(console, "Координата X", Integer.MIN_VALUE, 516);
            int y = askInt(console, "Координата Y", Integer.MIN_VALUE, Integer.MAX_VALUE);

            return new Coordinates(x, y);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    private static String askString(Console console, String prompt) throws AskBreak {
        while (true) {
            console.print(prompt + ": ");
            String input = console.readln().trim();
            if (input.equals("exit")) throw new AskBreak();
            if (!input.isEmpty()) return input;
            console.printError("Введите непустое значение");
        }
    }

    private static int askInt(Console console, String prompt, int min, int max) throws AskBreak {
        while (true) {
            console.print(prompt + ": ");
            String input = console.readln().trim();
            if (input.equals("exit")) throw new AskBreak();
            if (!input.isEmpty()) {
                try {
                    int value = Integer.parseInt(input);
                    if (value >= min && value <= max) return value;
                    console.printError("Значение должно быть в диапазоне [" + min + ", " + max + "]");
                } catch (NumberFormatException e) {
                    console.printError("Введите корректное целое число");
                }
            } else {
                console.printError("Введите непустое значение");
            }
        }
    }

    private static long askLong(Console console, String prompt, long min, long max) throws AskBreak {
        while (true) {
            console.print(prompt + ": ");
            String input = console.readln().trim();
            if (input.equals("exit")) throw new AskBreak();
            if (!input.isEmpty()) {
                try {
                    long value = Long.parseLong(input);
                    if (value >= min && value <= max) return value;
                    console.printError("Значение должно быть в диапазоне [" + min + ", " + max + "]");
                } catch (NumberFormatException e) {
                    console.printError("Введите корректное целое число");
                }
            } else {
                console.printError("Введите непустое значение");
            }
        }
    }

    private static float askFloat(Console console, String prompt, float min, float max) throws AskBreak {
        while (true) {
            console.print(prompt + ": ");
            String input = console.readln().trim();
            if (input.equals("exit")) throw new AskBreak();
            if (!input.isEmpty()) {
                try {
                    float value = Float.parseFloat(input);
                    if (value >= min && value <= max) return value;
                    console.printError("Значение должно быть в диапазоне [" + min + ", " + max + "]");
                } catch (NumberFormatException e) {
                    console.printError("Введите корректное число");
                }
            } else {
                console.printError("Введите непустое значение");
            }
        }
    }

    private static double askDouble(Console console, String prompt, double min, double max) throws AskBreak {
        while (true){
            console.print(prompt + ": ");
            String input = console.readln().trim();
            if (input.equals("exit")) throw new AskBreak();
            if (!input.isEmpty()) {
                try {
                    double value = Double.parseDouble(input);
                    if (value >= min && value <= max) return value;
                    console.printError("Значение должно быть в диапазоне [" + min + ", " + max + "]");
                } catch (NumberFormatException e) {
                    console.printError("Введите корректное число");
                }
            } else {
                console.printError("Введите непустое значение");
            }
        }
    }

    private static <T extends Enum<T>> T askEnum(Console console, String prompt, Class<T> enumClass) throws AskBreak {
        while (true) {
            console.print(prompt + " (" + String.join(", ", getEnumNames(enumClass)) + "): ");
            String input = console.readln().trim().toUpperCase();
            if (input.equals("EXIT")) throw new AskBreak();
            if (!input.isEmpty()) {
                try {
                    return Enum.valueOf(enumClass, input);
                } catch (IllegalArgumentException e) {
                    console.printError("Введите одно из допустимых значений");
                }
            } else {
                console.printError("Введите непустое значение");
            }
        }
    }

    private static <T extends Enum<T>> String[] getEnumNames(Class<T> enumClass) {
        T[] constants = enumClass.getEnumConstants();
        String[] names = new String[constants.length];
        for (int i = 0; i < constants.length; i++) {
            names[i] = constants[i].name();
        }
        return names;
    }

}