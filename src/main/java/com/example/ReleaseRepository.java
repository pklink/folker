package com.example;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;

@MongoRepository
public interface ReleaseRepository extends CrudRepository<Release, String> {
}
