package br.com.bus.ms.places.service;

import java.util.List;

import br.com.bus.ms.places.entity.PlaceEntity;

public interface PlacesService {

	PlaceEntity find(String id);
	
	List<PlaceEntity> findByName(String name);
	
	void save(PlaceEntity place) throws Exception;
	
	
}
