package com.example.releases.domain;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import org.bson.types.ObjectId;

@MongoRepository
interface ReleaseRepository extends CrudRepository<Release, ObjectId> {
}
