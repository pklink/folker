package net.einself.folker.release.application;

import io.micronaut.context.event.ApplicationEventPublisher;
import jakarta.inject.Singleton;
import net.einself.folker.release.domain.Release;
import net.einself.folker.release.domain.ReleaseCreatedEvent;
import net.einself.folker.release.domain.ReleaseRepository;
import org.jmolecules.architecture.layered.ApplicationLayer;

import java.util.List;

@Singleton
@ApplicationLayer
public class ReleaseService {

    private final ReleaseRepository releaseRepository;
    private final ApplicationEventPublisher<ReleaseCreatedEvent> releaseCreatedEventPublisher;

    public ReleaseService(ReleaseRepository releaseRepository, ApplicationEventPublisher<ReleaseCreatedEvent> releaseCreatedEventPublisher) {
        this.releaseRepository = releaseRepository;
        this.releaseCreatedEventPublisher = releaseCreatedEventPublisher;
    }

    public Release create(Release release) {
        Release savedRelease = releaseRepository.save(release);
        ReleaseCreatedEvent event = new ReleaseCreatedEvent(savedRelease.getId(), savedRelease.getTitle());
        releaseCreatedEventPublisher.publishEvent(event);
        return savedRelease;
    }

    public List<Release> findAll() {
        return releaseRepository.findAll();
    }

}
