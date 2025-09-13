package moduls;

import utility.Validatable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий фильм
 */
public class Movie implements Validatable, Comparable<Movie> {
    /** ID фильма (должен быть больше 0, уникальный, генерируется автоматически) */
    private long id;
    /** Название фильма (не может быть null или пустым) */
    private String name;
    /** Координаты фильма (не могут быть null) */
    private Coordinates coordinates;
    /** Дата создания записи (не может быть null, генерируется автоматически) */
    private java.time.LocalDate creationDate;
    /** Количество Оскаров (должно быть больше 0) */
    private long oscarsCount;
    /** Жанр фильма (не может быть null) */
    private MovieGenre genre;
    /** Рейтинг MPAA (не может быть null) */
    private MpaaRating mpaaRating;
    /** Сценарист фильма */
    private Person screenwriter;

    /**
     * Конструктор класса Movie
     * @param id ID фильма
     * @param name название фильма
     * @param coordinates координаты фильма
     * @param creationDate дата создания записи
     * @param oscarsCount количество Оскаров
     * @param genre жанр фильма
     * @param mpaaRating рейтинг MPAA
     * @param screenwriter сценарист фильма
     */
    public Movie(long id, String name, Coordinates coordinates, LocalDate creationDate, long oscarsCount, MovieGenre genre, MpaaRating mpaaRating, Person screenwriter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.oscarsCount = oscarsCount;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.screenwriter = screenwriter;
    }

    /**
     * Конструктор класса Movie с автоматической генерацией даты создания
     * @param id ID фильма
     * @param name название фильма
     * @param coordinates координаты фильма
     * @param oscarsCount количество Оскаров
     * @param genre жанр фильма
     * @param mpaaRating рейтинг MPAA
     * @param screenwriter сценарист фильма
     */
    public Movie(long id, String name, Coordinates coordinates, long oscarsCount, MovieGenre genre, MpaaRating mpaaRating, Person screenwriter) {
        this(id, name, coordinates, java.time.LocalDate.now(), oscarsCount, genre, mpaaRating, screenwriter);
    }

    /**
     * Преобразует фильм в строковое представление
     * @return строковое представление фильма
     */
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate.format(DateTimeFormatter.ISO_DATE) +
                ", oscarsCount=" + oscarsCount +
                ", genre=" + genre +
                ", mpaaRating=" + mpaaRating +
                ", screenwriter=" + screenwriter +
                '}';
    }

    /**
     * Проверяет корректность данных фильма
     * @return список ошибок валидации
     */
    @Override
    public List<String> validate() {
        List<String> errors = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            errors.add("Movie.name не может быть null и не должен быть пустым");
        }
        if (coordinates == null) {
            errors.add("Movie.coordinates не может быть null");
        }
        if (genre == null) {
            errors.add("Movie.genre не может быть null");
        }
        if (mpaaRating == null) {
            errors.add("Movie.mpaaRating не может быть null");
        }
        if (oscarsCount <= 0) {
            errors.add("Movie.oscarsCount должен быть больше 0");
        }
        return errors;
    }

    /**
     * Получает ID фильма
     * @return ID фильма
     */
    public long getId() {
        return id;
    }

    /**
     * Устанавливает ID фильма
     * @param id новый ID фильма
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Получает название фильма
     * @return название фильма
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название фильма
     * @param name новое название фильма
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получает координаты фильма
     * @return координаты фильма
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Устанавливает координаты фильма
     * @param coordinates новые координаты фильма
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Получает дату создания записи
     * @return дата создания записи
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Устанавливает дату создания записи
     * @param creationDate новая дата создания записи
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Получает количество Оскаров
     * @return количество Оскаров
     */
    public long getOscarsCount() {
        return oscarsCount;
    }

    /**
     * Устанавливает количество Оскаров
     * @param oscarsCount новое количество Оскаров
     */
    public void setOscarsCount(long oscarsCount) {
        this.oscarsCount = oscarsCount;
    }

    /**
     * Получает жанр фильма
     * @return жанр фильма
     */
    public MovieGenre getGenre() {
        return genre;
    }

    /**
     * Устанавливает жанр фильма
     * @param genre новый жанр фильма
     */
    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    /**
     * Получает рейтинг MPAA
     * @return рейтинг MPAA
     */
    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    /**
     * Устанавливает рейтинг MPAA
     * @param mpaaRating новый рейтинг MPAA
     */
    public void setMpaaRating(MpaaRating mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    /**
     * Получает сценариста фильма
     * @return сценарист фильма
     */
    public Person getScreenwriter() {
        return screenwriter;
    }

    /**
     * Устанавливает сценариста фильма
     * @param screenwriter новый сценарист фильма
     */
    public void setScreenwriter(Person screenwriter) {
        this.screenwriter = screenwriter;
    }

    /**
     * Сравнивает два фильма по количеству Оскаров, названию и ID
     * @param o фильм для сравнения
     * @return результат сравнения
     */
    @Override
    public int compareTo(Movie o) {
        int cmp = Long.compare(this.oscarsCount, o.oscarsCount);
        if (cmp != 0) return cmp;
        cmp = this.name.compareTo(o.name);
        if (cmp != 0) return cmp;
        return Long.compare(this.id, o.id);
    }
}