package managers;

import moduls.Movie;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Класс менеджера коллекции
 *
 * @version 1.0
 */
public class CollectionManager {
    /**
     * переменная для выдачи следующего id
     */
    private long nextId = 1;
    /**
     * словарь для хранения элементов коллекции по id
     */
    private Map<Long, Movie> groups = new HashMap();
    /**
     * хранимая коллекция
     */
    private LinkedHashSet<Movie> collection = new LinkedHashSet<>();
    /**
     * Время последней инициализации менеджера
     */
    private LocalDateTime lastInitTime;
    /**
     * вреся последнего сохранения коллекции
     */
    private LocalDateTime lastSaveTime;
    /**
     * файловый менеджер
     */
    private final DumpManager dumpManager;

    /**
     * Конструктор
     *
     * @param dumpManager файловый менеджер
     */

    public CollectionManager(DumpManager dumpManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.dumpManager = dumpManager;
    }

    /**
     * @return возвращает время последней инициализации менеджера
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * @return возвращает время последнего сохранения коллекции
     */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     * Функция сохранения коллекции в файл
     */
    public void saveCollection() {
        dumpManager.writeCollection(collection);
        lastSaveTime = LocalDateTime.now();
    }

    /**
     * Функция загрузки коллекции из файла в менеджер
     *
     * @return true, если загрузка прошла успешно
     */
    public boolean loadCollection() {
        groups.clear();
        dumpManager.readCollection(collection);
        lastInitTime = LocalDateTime.now();
        for (Movie e : collection)
            if (getById(e.getId()) != null) {
                collection.clear();
                return false;
            } else {
                if (e.getId() > nextId) nextId = e.getId();
                groups.put(e.getId(), e);
            }
        sort();
        return true;
    }

    /**
     * Функция для проверки наличия объекта в коллекции
     *
     * @param e проверяемый объект
     * @return true, если объект присутствует в коллекции
     */
    public boolean isContain(Movie e) {
        return getById(e.getId()) != null;
    }

    /**
     * Функция получения коллекции из менеджера
     *
     * @return возвращает хранимую в менеджере коллекцию
     */
    public LinkedHashSet<Movie> getCollection() {
        return this.collection;
    }

    /**
     * Функция получения свободного id
     *
     * @return возвращает свобожный id
     */
    public Long getFreeId() {
        while (getById(nextId) != null) {
            if (++nextId < 0) nextId = 1;
        }
        return nextId;
    }

    /**
     * Функиця для получения объекта из коллекции по id
     *
     * @param id уникальный номер объекта
     * @return возвращает объект, хранимый в коллекции
     */
    public Movie getById(Long id) {
        return groups.get(id);
    }

    /**
     * Функция добавления элемента в коллекцию
     *
     * @param e добавляемый объект
     * @return true, если объект успешно добавлен
     */
    public boolean add(Movie e) {
        if (isContain(e)) return false;
        groups.put(e.getId(), e);
        collection.add(e);
        sort();
        return true;
    }

    /**
     * Функция для удаления элемента коллекции по id
     *
     * @param id уникальный номер элемента
     * @return true, если элемент успешно удален
     */
    public boolean remove(long id) {
        Movie element = getById(id);
        if (element == null) return false;
        groups.remove(element.getId());
        collection.remove(element);
        sort();
        return true;
    }

    /**
     * Функция для получения первого элемента в коллекции
     * @return возвращает первый элемент коллекци
     */
    public Movie getFirst() {
        Iterator<Movie> iterator = collection.iterator();
        if (iterator.hasNext()) return iterator.next();
        return null;
    }

    /**
     * Функция сортировки коллекции
     */
    public void sort() {
        int length = collection.size();
        Movie[] array = new Movie[length];
        Iterator<Movie> iterator = collection.iterator();
        for (int i = 0; i < length; i++) {
            array[i] = iterator.next();
        }
        Arrays.sort(array);
        collection = new LinkedHashSet<>(Arrays.asList(array));
    }

    /**
     * @return возвращает объект, переведенный в строковое представление
     */
    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";

        StringBuilder info = new StringBuilder();
        for (Movie element : collection) {
            info.append(element + "\n\n");
        }
        return info.toString().trim();
    }
}