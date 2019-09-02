package br.com.bus.ms.places.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bus.ms.places.entity.PlaceEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

	@Autowired
    private MockMvc mvc;
	
	public static String asJsonString(final Object obj) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        final String jsonContent = mapper.writeValueAsString(obj);
        return jsonContent;
	}
	
	@Test
	public void testCreate() throws Exception {
		//ok
		PlaceEntity newPlace = new PlaceEntity();
		newPlace.setName("Test Name one");
		newPlace.setSlug("Test Slug one");
		newPlace.setState("Test State one");
		
		mvc.perform(MockMvcRequestBuilders.post("/create")
				.content(asJsonString(newPlace))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
		// Slug is null
		newPlace.setName("Test Name two");
		newPlace.setSlug(null);
		newPlace.setState("Test State two");
		
		mvc.perform(MockMvcRequestBuilders.post("/create")
				.content(asJsonString(newPlace))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
		
		//Name is null
		newPlace.setName(null);
		newPlace.setSlug("Test Slug three");
		newPlace.setState("Test State three");
		
		mvc.perform(MockMvcRequestBuilders.post("/create")
				.content(asJsonString(newPlace))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
		
		//State is null
		newPlace.setName("Test name four");
		newPlace.setSlug("Test Slug four");
		newPlace.setState(null);
		
		mvc.perform(MockMvcRequestBuilders.post("/create")
				.content(asJsonString(newPlace))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testGetPlaces() throws Exception {
		//Finding by name
		mvc.perform(MockMvcRequestBuilders.get("/getPlacesByName").param("name", "Test"))
				.andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

		//Finding without name		
		mvc.perform(MockMvcRequestBuilders.get("/getPlacesByName"))
		.andExpect(status().isBadRequest());
		

	}
	
	@Test
	public void testGetPlacesById() throws Exception {
		//Finding by id
		mvc.perform(MockMvcRequestBuilders.get("/getPlace").param("id", "5d6bef466a274049bb13057a"))
				.andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

		//Trying to Find by id
		mvc.perform(MockMvcRequestBuilders.get("/getPlace"))
		.andExpect(status().isBadRequest());

		//Trying to Find by id when is null		
		mvc.perform(MockMvcRequestBuilders.get("/getPlace").param("id", ""))
		.andExpect(status().isBadRequest());
	}
	
	
}
