package br.com.bus.ms.places.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.bus.ms.places.entity.PlaceEntity;
import br.com.bus.ms.places.service.impl.PlacesServiceImpl;

@Controller
public class PlacesController {

	@Autowired
	private PlacesServiceImpl service;
	
	private static final Logger logger = Logger.getLogger(PlacesController.class);
	
	@RequestMapping("/")
	public ResponseEntity<String> welcome() {
		return ResponseEntity.ok(new String("OK!"));
	}
	
	@RequestMapping(value="/create", method= RequestMethod.POST)
	@CacheEvict(value="listPLaces",allEntries=true)
	public ResponseEntity<PlaceEntity> createPlace(@RequestBody PlaceEntity place){
		try{
			logger.info("Fowarding save operation to service" + place);
			service.save(place);	
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return ResponseEntity.ok(place);
	}
	
	
	@RequestMapping(value="/getPlacesByName", method= RequestMethod.GET)
	@Cacheable(value="listPLaces")
	public ResponseEntity<List<PlaceEntity>> getPlaces(@RequestParam("name") String name){
		logger.info("Finding places by name");
		List<PlaceEntity> placeList = new ArrayList<PlaceEntity>();
		placeList = service.findByName(name);
		return ResponseEntity.ok(placeList);
	}

	@RequestMapping(value="/getPlace", method= RequestMethod.GET)
	public ResponseEntity<PlaceEntity> getPlace(@RequestParam("id") String id){
		PlaceEntity place = new PlaceEntity();
		logger.info("Finding places by id");
		if(id != null && !id.equals("")) {
			place = service.find(id);
		}else {
			return ResponseEntity.badRequest().body(null);	
		}
		return ResponseEntity.ok(place);
	}
	
	@RequestMapping(value="/editPlace", method= RequestMethod.PUT)
	@CacheEvict(value="listPLaces",allEntries=true)
	public ResponseEntity<PlaceEntity> editPlace(@RequestBody PlaceEntity place){
		try{
			logger.info("Fowarding update operation to service" + place);
			service.save(place);	
		}catch(Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return ResponseEntity.ok(place);
	}
	
}

