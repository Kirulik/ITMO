package moduls;

/**
 * Перечисление цветов глаз
 */
public enum Color {
    /** Зеленый */
    GREEN,
    /** Красный */
    RED,
    /** Синий */
    BLUE,
    /** Оранжевый */
    ORANGE;

    /**
     * Возвращает список всех доступных цветов через запятую
     * @return строка со списком цветов
     */
    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var colorType : values()) {
            nameList.append(colorType.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}
