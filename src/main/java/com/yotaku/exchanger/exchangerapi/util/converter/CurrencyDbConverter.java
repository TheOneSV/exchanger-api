package com.yotaku.exchanger.exchangerapi.util.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Currency;

@Converter(autoApply=true)
public class CurrencyDbConverter implements AttributeConverter<Currency, String> {

    @Override
    public String convertToDatabaseColumn(final Currency attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.getCurrencyCode();
    }

    @Override
    public Currency convertToEntityAttribute(final String dbData) {
        if (dbData == null) {
            return null;
        }

        return Currency.getInstance(dbData);
    }
}
