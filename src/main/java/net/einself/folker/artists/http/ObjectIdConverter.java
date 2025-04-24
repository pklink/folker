package net.einself.folker.artists.http;

import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;
import jakarta.inject.Singleton;
import org.bson.types.ObjectId;

import java.util.Optional;

@Singleton
public class ObjectIdConverter implements TypeConverter<String, ObjectId> {

    @Override
    public Optional<ObjectId> convert(String source, Class<ObjectId> targetType, ConversionContext context) {
        if (source == null || source.isBlank()) {
            return Optional.empty();
        }

        try {
            return Optional.of(new ObjectId(source));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
