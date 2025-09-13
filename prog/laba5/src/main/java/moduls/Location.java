package moduls;

import utility.Validatable;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий местоположение
 */
public class Location implements Validatable {
    /** Координата X */
    private double x;
    /** Координата Y */
    private long y;
    /** Координата Z (не может быть null) */
    private Long z;
    /** Название места (не может быть null) */
    private String name;

    /**
     * Конструктор класса Location
     * @param x координата X
     * @param y координата Y
     * @param z координата Z
     * @param name название места
     */
    public Location(double x, long y, Long z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    /**
     * Проверяет корректность данных местоположения
     * @return список ошибок валидации
     */
    @Override
    public List<String> validate() {
        List<String> errors = new ArrayList<>();
        if (z == null) {
            errors.add("Location.z не может быть null");
        }
        if (name == null) {
            errors.add("Location.name не может быть null");
        }
        return errors;
    }

    /**
     * Получает координату X
     * @return координата X
     */
    public double getX() {
        return x;
    }

    /**
     * Устанавливает координату X
     * @param x новая координата X
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Получает координату Y
     * @return координата Y
     */
    public long getY() {
        return y;
    }

    /**
     * Устанавливает координату Y
     * @param y новая координата Y
     */
    public void setY(long y) {
        this.y = y;
    }

    /**
     * Получает координату Z
     * @return координата Z
     */
    public Long getZ() {
        return z;
    }

    /**
     * Устанавливает координату Z
     * @param z новая координата Z
     */
    public void setZ(Long z) {
        this.z = z;
    }

    /**
     * Получает название места
     * @return название места
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название места
     * @param name новое название места
     */
    public void setName(String name) {
        this.name = name;
    }
}
