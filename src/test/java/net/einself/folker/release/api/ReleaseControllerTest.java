package net.einself.folker.release.api;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import net.einself.folker.release.command.ReleaseCommandHandler;
import net.einself.folker.release.domain.command.CreateReleaseCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MicronautTest
@ExtendWith(MockitoExtension.class)
class ReleaseControllerTest {

    @Mock
    private ReleaseCommandHandler releaseCommandHandler;

    private ReleaseController releaseController;

    @BeforeEach
    void setUp() {
        releaseController = new ReleaseController(releaseCommandHandler);
    }


    @Test
    @DisplayName("Should create release and return created response")
    void shouldCreateReleaseAndReturnCreatedResponse() {
        // arrange
        CreateReleaseRequestDto request = new CreateReleaseRequestDto("Test Release");
        when(releaseCommandHandler.handle(any(CreateReleaseCommand.class))).thenReturn(Mono.empty());

        // act
        HttpResponse<CreateReleaseResponseDto> response = releaseController.create(request).block();

        // assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatus());

        CreateReleaseResponseDto responseDto = response.body();
        assertNotNull(responseDto);
        assertNotNull(responseDto.id());
        assertEquals("Test Release", responseDto.title());

        // verify command handler was called with correct parameters
        ArgumentCaptor<CreateReleaseCommand> commandCaptor = ArgumentCaptor.forClass(CreateReleaseCommand.class);
        verify(releaseCommandHandler).handle(commandCaptor.capture());

        CreateReleaseCommand capturedCommand = commandCaptor.getValue();
        assertNotNull(capturedCommand.releaseId());
        assertEquals("Test Release", capturedCommand.title());
    }

    @Test
    @DisplayName("Should handle empty title")
    void shouldHandleEmptyTitle() {
        // Arrange
        CreateReleaseRequestDto requestDto = new CreateReleaseRequestDto("");

        when(releaseCommandHandler.handle(any(CreateReleaseCommand.class))).thenReturn(Mono.empty());

        // Act
        HttpResponse<CreateReleaseResponseDto> response = releaseController.create(requestDto).block();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatus());

        CreateReleaseResponseDto responseDto = response.body();
        assertNotNull(responseDto);
        assertNotNull(responseDto.id());
        assertEquals("", responseDto.title());

        // Verify command handler was called with correct parameters
        ArgumentCaptor<CreateReleaseCommand> commandCaptor = ArgumentCaptor.forClass(CreateReleaseCommand.class);
        verify(releaseCommandHandler).handle(commandCaptor.capture());

        CreateReleaseCommand capturedCommand = commandCaptor.getValue();
        assertEquals("", capturedCommand.title());
    }

    @Test
    @DisplayName("Should handle null title")
    void shouldHandleNullTitle() {
        // Arrange
        CreateReleaseRequestDto requestDto = new CreateReleaseRequestDto(null);

        // title is null by default

        when(releaseCommandHandler.handle(any(CreateReleaseCommand.class))).thenReturn(Mono.empty());

        // Act
        HttpResponse<CreateReleaseResponseDto> response = releaseController.create(requestDto).block();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatus());

        CreateReleaseResponseDto responseDto = response.body();
        assertNotNull(responseDto);
        assertNotNull(responseDto.id());
        assertNull(responseDto.title());

        // Verify command handler was called with correct parameters
        ArgumentCaptor<CreateReleaseCommand> commandCaptor = ArgumentCaptor.forClass(CreateReleaseCommand.class);
        verify(releaseCommandHandler).handle(commandCaptor.capture());

        CreateReleaseCommand capturedCommand = commandCaptor.getValue();
        assertNull(capturedCommand.title());
    }

    @Test
    @DisplayName("Should handle error from command handler")
    void shouldHandleErrorFromCommandHandler() {
        // Arrange
        CreateReleaseRequestDto requestDto = new CreateReleaseRequestDto("Test Release");

        RuntimeException testException = new RuntimeException("Test exception");
        when(releaseCommandHandler.handle(any(CreateReleaseCommand.class))).thenReturn(Mono.error(testException));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> releaseController.create(requestDto).block());

        // Verify that the exception contains our test exception
        // The exact exception type might be wrapped by Reactor
        assertTrue(exception.getMessage().contains("Test exception") ||
                        (exception.getCause() != null && exception.getCause().getMessage().contains("Test exception")),
                "Exception should contain the original error message");
    }
}
