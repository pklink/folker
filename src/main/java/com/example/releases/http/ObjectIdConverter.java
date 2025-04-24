package com.example.releases.http;

import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;
import jakarta.inject.Singleton;
import org.bson.types.ObjectId;

import java.util.Optional;

@Singleton
public class ObjectIdConverter implements TypeConverter<String, ObjectId> {

    @Override
    public Optional<ObjectId> convert(String source, Class<ObjectId> targetType, ConversionContext context) {
        if (source == null || source.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(new ObjectId(source));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
