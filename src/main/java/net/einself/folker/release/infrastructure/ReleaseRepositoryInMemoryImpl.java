package net.einself.folker.release.infrastructure;

import jakarta.inject.Singleton;
import net.einself.folker.release.domain.Release;
import net.einself.folker.release.domain.ReleaseRepository;
import org.jmolecules.architecture.layered.InfrastructureLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
@InfrastructureLayer
public class ReleaseRepositoryInMemoryImpl implements ReleaseRepository {

    private final List<Release> releases = new ArrayList<>();

    @Override
    public List<Release> findAll() {
        return releases;
    }

    @Override
    public Optional<Release> findById(UUID id) {
        return releases.stream()
                .filter(release -> release.getId().equals(id))
                .findFirst();
    }

    @Override
    public Release save(Release release) {
        releases.add(release);
        return release;
    }

}
