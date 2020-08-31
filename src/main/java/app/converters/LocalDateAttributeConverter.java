package app.converters;

import javax.persistence.AttributeConverter;
import java.time.LocalDate;

public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, String> {

    @Override
    public String convertToDatabaseColumn(LocalDate localDate) {
        return (localDate == null ? null : localDate.toString());
    }

    @Override
    public LocalDate convertToEntityAttribute(String sqlStringDate) {
        return (sqlStringDate == null ? null : LocalDate.parse(sqlStringDate));
    }
}