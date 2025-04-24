package net.einself.folker.artists.domain;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import org.bson.types.ObjectId;

@MongoRepository
public interface ArtistRepository extends CrudRepository<Artist, ObjectId> {
}
