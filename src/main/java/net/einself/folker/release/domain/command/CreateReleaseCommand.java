package net.einself.folker.release.domain.command;

import java.util.UUID;

public record CreateReleaseCommand(
        UUID releaseId,
        String title
) {
}
