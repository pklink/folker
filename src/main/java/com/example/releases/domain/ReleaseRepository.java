package com.example.releases.domain;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;

@MongoRepository
interface ReleaseRepository extends CrudRepository<Release, String> {
}
