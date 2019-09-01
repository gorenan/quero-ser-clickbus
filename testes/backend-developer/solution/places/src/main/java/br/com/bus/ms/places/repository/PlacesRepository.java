package br.com.bus.ms.places.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.bus.ms.places.entity.PlaceEntity;

public interface PlacesRepository extends MongoRepository<PlaceEntity, String> {

    @Query("{ 'name' : { '$regex' : ?0 , '$options': 'i'}}")
    public List<PlaceEntity> findPlacesByName(String name);
	
}
