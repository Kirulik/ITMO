package moduls;

import utility.Validatable;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий сценариста фильма
 */
public class Person implements Validatable, Comparable<Person> {
    /** Имя сценариста (не может быть null или пустым) */
    private String name;
    /** Рост сценариста (должен быть больше 0) */
    private float height;
    /** Цвет глаз сценариста (не может быть null) */
    private Color eyeColor;
    /** Местоположение сценариста (может быть null) */
    private Location location;

    /**
     * Конструктор класса Person
     * @param name имя сценариста
     * @param height рост сценариста
     * @param eyeColor цвет глаз сценариста
     * @param location местоположение сценариста
     */
    public Person(String name, float height, Color eyeColor, Location location) {
        this.name = name;
        this.height = height;
        this.eyeColor = eyeColor;
        this.location = location;
    }

    /**
     * Проверяет корректность данных сценариста
     * @return список ошибок валидации
     */
    @Override
    public List<String> validate() {
        List<String> errors = new ArrayList<>();
        if (name == null || name.isEmpty()){
            errors.add("Person.name не может быть null и не должен быть пустым");
        }
        if (height <= 0){
            errors.add("Person.height должен быть больше 0");
        }
        if (eyeColor == null){
            errors.add("Person.eyeColor не может быть null");
        }
        if (location == null){
            errors.add("Person.location не может быть null");
        }
        return errors;
    }

    /**
     * Сравнивает двух сценаристов по росту, имени и цвету глаз
     * @param o сценарист для сравнения
     * @return результат сравнения
     */
    @Override
    public int compareTo(Person o) {
        int cmp = Float.compare(this.height, o.height);
        if (cmp != 0) return cmp;
        cmp = this.name.compareTo(o.name);
        if (cmp != 0) return cmp;
        if (this.eyeColor != null && o.eyeColor != null) {
            cmp = this.eyeColor.compareTo(o.eyeColor);
            if (cmp != 0) return cmp;
        }
        return 0;
    }

    /**
     * Получает имя сценариста
     * @return имя сценариста
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает имя сценариста
     * @param name новое имя сценариста
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получает рост сценариста
     * @return рост сценариста
     */
    public float getHeight() {
        return height;
    }

    /**
     * Устанавливает рост сценариста
     * @param height новый рост сценариста
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Получает цвет глаз сценариста
     * @return цвет глаз сценариста
     */
    public Color getEyeColor() {
        return eyeColor;
    }

    /**
     * Устанавливает цвет глаз сценариста
     * @param eyeColor новый цвет глаз сценариста
     */
    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * Получает местоположение сценариста
     * @return местоположение сценариста
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Устанавливает местоположение сценариста
     * @param location новое местоположение сценариста
     */
    public void setLocation(Location location) {
        this.location = location;
    }
}