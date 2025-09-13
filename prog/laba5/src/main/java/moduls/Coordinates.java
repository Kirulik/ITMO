package moduls;

import utility.Validatable;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий координаты фильма
 */
public class Coordinates implements Validatable {
    /** Координата X (максимальное значение: 516) */
    private int x;
    /** Координата Y */
    private int y;

    /**
     * Конструктор класса Coordinates
     * @param x координата X
     * @param y координата Y
     */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Проверяет корректность координат
     * @return список ошибок валидации
     */
    @Override
    public List<String> validate() {
        List<String> errors = new ArrayList<>();
        if (x>516){
            errors.add("координата x не должна быть больше 516");
        }
        return errors;
    }

    /**
     * Преобразует координаты в строковое представление
     * @return строковое представление координат
     */
    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Получает координату X
     * @return координата X
     */
    public int getX() {
        return x;
    }

    /**
     * Устанавливает координату X
     * @param x новая координата X
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Получает координату Y
     * @return координата Y
     */
    public int getY() {
        return y;
    }

    /**
     * Устанавливает координату Y
     * @param y новая координата Y
     */
    public void setY(int y) {
        this.y = y;
    }
}
