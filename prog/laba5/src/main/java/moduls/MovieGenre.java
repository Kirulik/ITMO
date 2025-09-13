package moduls;

/**
 * Перечисление жанров фильмов
 */
public enum MovieGenre {
    /** Боевик */
    ACTION,
    /** Вестерн */
    WESTERN,
    /** Комедия */
    COMEDY,
    /** Триллер */
    THRILLER,
    /** Фэнтези */
    FANTASY;

    /**
     * Возвращает список всех доступных жанров через запятую
     * @return строка со списком жанров
     */
    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var colorType : values()) {
            nameList.append(colorType.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}