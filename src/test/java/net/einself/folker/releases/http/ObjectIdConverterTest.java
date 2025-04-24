package net.einself.folker.releases.http;

import io.micronaut.core.convert.ConversionContext;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObjectIdConverterTest {

    private ObjectIdConverter converter;
    private ConversionContext context;

    @BeforeEach
    void setUp() {
        converter = new ObjectIdConverter();
        context = ConversionContext.DEFAULT;
    }

    @Test
    void shouldConvertValidObjectIdString() {
        // arrange
        String validObjectId = "507f1f77bcf86cd799439011";

        // act
        Optional<ObjectId> result = converter.convert(validObjectId, ObjectId.class, context);
        
        // assert
        assertTrue(result.isPresent());
        assertEquals(validObjectId, result.get().toHexString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldReturnEmptyOptionalForNullOrEmptyInput(String input) {
        // arrange
        // Input is provided by the parameterized test

        // act
        Optional<ObjectId> result = converter.convert(input, ObjectId.class, context);

        // assert
        assertTrue(result.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "invalid",            // too short
            "XYZ1234567890abcdef123456", // contains invalid characters
            "507f1f77bcf86cd7994390112" // too long (25 chars)
    })
    void shouldReturnEmptyOptionalForInvalidObjectIdFormat(String invalidInput) {
        // arrange
        // Invalid input is provided by the parameterized test

        // act
        Optional<ObjectId> result = converter.convert(invalidInput, ObjectId.class, context);

        // assert
        assertTrue(result.isEmpty());
    }
}
