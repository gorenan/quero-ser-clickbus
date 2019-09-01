package br.com.bus.ms.places.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bus.ms.places.entity.PlaceEntity;
import br.com.bus.ms.places.repository.PlacesRepository;
import br.com.bus.ms.places.service.PlacesService;

@Service
public class PlacesServiceImpl implements PlacesService {
	
	@Autowired
	private PlacesRepository repository;

	private static final Logger logger = Logger.getLogger(PlacesServiceImpl.class);
	
	public PlaceEntity find(String id) {
		if(id != null) {
			try{
				logger.info("Searching place by id");
				Optional<PlaceEntity> placeFound = repository.findById(id);
				if(placeFound.get() != null) 
					return placeFound.get();  
			} catch (Exception e) {
				logger.error(e.getMessage());
				return new PlaceEntity();
			}
		}
		return null;
	}
	
	public void save(PlaceEntity place) throws Exception {
		try {
			logger.info("Trying to create new document");
			place.setDates();
			repository.save(place);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		
	}

	public List<PlaceEntity> findByName(String name) {
		List<PlaceEntity> list = new ArrayList<PlaceEntity>();
		if(name != null) {
			try {
				//name = ".*" + name + ".*"; 
				list = repository.findPlacesByName(name);
			}catch(Exception e) {
				logger.error(e.getMessage());
				return new ArrayList<PlaceEntity>();
			}	
		}
		return list;
	}
	
	

	
	
}
