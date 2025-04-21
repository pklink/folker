package com.example.releases.domain;

import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
class ReleaseRepositoryImpl implements ReleaseService {

    private final ReleaseRepository releaseRepository;

    public ReleaseRepositoryImpl(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    @Override
    public Release save(Release release) {
        return releaseRepository.save(release);
    }

    @Override
    public Optional<Release> findById(String id) {
        return releaseRepository.findById(id);
    }

    @Override
    public List<Release> findAll() {
        return releaseRepository.findAll();
    }
}
