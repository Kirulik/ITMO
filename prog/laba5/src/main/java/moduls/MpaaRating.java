package moduls;

/**
 * Перечисление рейтингов MPAA (Motion Picture Association of America)
 */
public enum MpaaRating {
    /** Рейтинг G - для всех возрастов */
    G,
    /** Рейтинг PG-13 - для детей старше 13 лет */
    PG_13,
    /** Рейтинг R - для лиц старше 17 лет */
    R;

    /**
     * Возвращает список всех доступных рейтингов через запятую
     * @return строка со списком рейтингов
     */
    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var colorType : values()) {
            nameList.append(colorType.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}