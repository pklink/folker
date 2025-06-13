package net.einself.folker.release.application;

import jakarta.inject.Singleton;
import net.einself.folker.core.application.EventPublisher;
import net.einself.folker.release.domain.Release;
import net.einself.folker.release.domain.ReleaseCreatedEvent;
import net.einself.folker.release.domain.ReleaseRepository;
import org.jmolecules.architecture.layered.ApplicationLayer;

import java.util.List;

@Singleton
@ApplicationLayer
public class ReleaseService {

    private final ReleaseRepository releaseRepository;
    private final EventPublisher eventPublisher;

    public ReleaseService(ReleaseRepository releaseRepository, EventPublisher eventPublisher) {
        this.releaseRepository = releaseRepository;
        this.eventPublisher = eventPublisher;
    }

    public Release create(Release release) {
        Release savedRelease = releaseRepository.save(release);
        ReleaseCreatedEvent event = new ReleaseCreatedEvent(release);
        eventPublisher.publish(event);
        return savedRelease;
    }

    public List<Release> findAll() {
        return releaseRepository.findAll();
    }

}
