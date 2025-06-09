package net.einself.folker.application;

import jakarta.inject.Singleton;
import net.einself.folker.domain.entity.Release;
import net.einself.folker.domain.repository.ReleaseRepository;
import org.jmolecules.architecture.layered.ApplicationLayer;

import java.util.List;

@Singleton
@ApplicationLayer
public class ReleaseService {

    private final ReleaseRepository releaseRepository;

    public ReleaseService(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    public Release create(Release release) {
        return releaseRepository.save(release);
    }

    public List<Release> findAll() {
        return releaseRepository.findAll();
    }

}
