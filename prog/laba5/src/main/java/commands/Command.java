package commands;


import utility.ExecutionResponse;

import java.util.Objects;

/**
 * Абстрактный класс команды.
 * 
 * Этот класс является базовым для всех команд в приложении.
 * Каждая команда имеет название и описание, а также может быть выполнена
 * через метод apply().
 * 
 * Команды используются для инкапсуляции различных операций над коллекцией фильмов,
 * таких как добавление, удаление, поиск и другие операции.
 * 
 * @author laba5
 * @version 1.0
 */
public abstract class Command implements Executable{
    /**
     * Название команды.
     * Используется для идентификации команды в командной строке.
     */
    private final String name;
    
    /**
     * Описание команды.
     * Содержит краткое описание функциональности команды для справки пользователя.
     */
    private final String description;

    /**
     * Конструктор команды.
     *
     * @param name название команды (должно быть уникальным)
     * @param description описание функциональности команды
     */
    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Получает название команды.
     *
     * @return название команды
     */
    public String getName() {
        return name;
    }

    /**
     * Получает описание команды.
     *
     * @return описание функциональности команды
     */
    public String getDescription() {
        return description;
    }

    /**
     * Проверяет эквивалентность двух команд.
     * 
     * Команды считаются эквивалентными, если у них одинаковые
     * названия и описания.
     *
     * @param obj объект для сравнения
     * @return true, если команды эквивалентны, false в противном случае
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Command command = (Command) obj;
        return name.equals(command.name) && description.equals(command.description);
    }

    /**
     * Вычисляет хэш-код команды.
     * 
     * Хэш-код основан на названии и описании команды.
     *
     * @return хэш-код команды
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    /**
     * Возвращает строковое представление команды.
     * 
     * Формат: "Command{name='название', description='описание'}"
     *
     * @return строковое представление команды
     */
    @Override
    public String toString() {
        return "Command{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}