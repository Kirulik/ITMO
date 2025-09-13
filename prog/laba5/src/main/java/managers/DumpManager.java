package managers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import moduls.Movie;
import utility.Console;

import java.io.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class DumpManager {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    private final String fileName;
    private final Console console;

    public DumpManager(String fileName, Console console) {
        this.fileName = fileName;
        this.console = console;
    }

    /**
     * Записывает коллекцию в файл.
     * @param collection коллекция
     */
    public void writeCollection(Collection<Movie> collection) {
        try (PrintWriter collectionPrintWriter = new PrintWriter(new File(fileName))) {
            collectionPrintWriter.println(gson.toJson(collection));
            console.println("Коллекция успешна сохранена в файл!");
        } catch (IOException exception) {
            console.printError("Загрузочный файл не может быть открыт!");
        }
    }

    /**
     * Считывает коллекцию из файл.
     * @param collection коллекция для заполнения
     * @return Считанная коллекция
     */
    public Collection<Movie> readCollection(LinkedHashSet<Movie> collection) {
        if (fileName != null && !fileName.isEmpty()) {
            try (var fileReader = new FileReader(fileName)) {
                var collectionType = new TypeToken<LinkedHashSet<Movie>>() {}.getType();
                var reader = new BufferedReader(fileReader);

                var jsonString = new StringBuilder();

                String line;
                while((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.equals("")) {
                        jsonString.append(line);
                    }
                }

                if (jsonString.length() == 0) {
                    jsonString = new StringBuilder("[]");
                }

                LinkedHashSet<Movie> newCollection = gson.fromJson(jsonString.toString(), collectionType);
                if (newCollection != null) {
                    collection.clear();
                    collection.addAll(newCollection);
                }

                console.println("Коллекция успешна загружена!");
                return collection;

            } catch (FileNotFoundException exception) {
                console.printError("Загрузочный файл не найден: '" + fileName + "'");
            } catch (NoSuchElementException exception) {
                console.printError("Загрузочный файл пуст: '" + fileName + "'. Для пустой коллекции используйте []");
            } catch (JsonParseException exception) {
                console.printError("Ошибка разбора JSON в файле '" + fileName + "': " + exception.getMessage() +
                        "\nУбедитесь, что файл содержит корректный JSON. Пример пустой коллекции: []");
            } catch (IllegalStateException | IOException exception) {
                console.printError("Ошибка ввода-вывода при работе с файлом '" + fileName + "': " + exception.getMessage());
                System.exit(0);
            }
        } else {
            console.printError("Аргумент командной строки с загрузочным файлом не найден!");
        }
        return collection;
    }
}