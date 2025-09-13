package managers;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Адаптер для корректной сериализации и десериализации java.time.LocalDate.
 * Использует стандартный формат ISO (YYYY-MM-DD)   .
 */
public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    // Стандартный и самый распространенный формат для дат.
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        // Преобразуем объект LocalDate в строку формата "YYYY-MM-DD"
        return new JsonPrimitive(src.format(FORMATTER));
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        // Преобразуем строку формата "YYYY-MM-DD" обратно в объект LocalDate
        return LocalDate.parse(json.getAsString(), FORMATTER);
    }
}