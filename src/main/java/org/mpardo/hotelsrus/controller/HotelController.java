package org.mpardo.hotelsrus.controller;

import java.util.List;

import org.mpardo.hotelsrus.dto.HotelDTO;
import org.mpardo.hotelsrus.model.Hotel;
import org.mpardo.hotelsrus.service.IHotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de Hotel que realiza de intermediario entre el Frontend y el
 * Servicio.
 * 
 * @author micky pardo
 * 
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/hotels")
public class HotelController {

	private IHotelService hotelService;

	public HotelController(IHotelService hotelService) {

		this.hotelService = hotelService;

	}

	/**
	 * Crea un hotel nuevo.
	 * 
	 * @param hotelDTO
	 * @return la respuesta de la comunicación
	 */
	@PostMapping(value="/ins", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createHotel(@RequestBody HotelDTO hotelDTO) {

		try {
			String name = hotelDTO.getName();
			Integer category = hotelDTO.getCategory();
			Hotel hotel = new Hotel(name, category);
		
			hotelService.create(hotel);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	/**
	 * Actualiza los datos de un hotel.
	 * 
	 * @param id
	 * @param hotelDTO
	 * @return la respuesta de la comunicación
	 */
	@PutMapping(value = "/mod/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateHotel(@PathVariable("id") Integer id, @RequestBody HotelDTO hotelDTO) {

		if (!hotelService.isById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		try {
			Hotel hotel = hotelService.getOne(id).get();
	
			hotel.setName(hotelDTO.getName());
			hotel.setCategory(hotelDTO.getCategory());
			
			hotelService.update(hotel);
	
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	/**
	 * Obtiene un hotel según su 'id'
	 * 
	 * @param id
	 * @return La respuesta de la comunicación y el hotel en cuestión
	 */
	@GetMapping(value = "/one/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> findHotelById(@PathVariable("id") Integer id) {

		if (!hotelService.isById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		try {
			Hotel hotel = hotelService.getOne(id).get();
			return ResponseEntity.status(HttpStatus.OK).body(hotel);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	/**
	 * Obtiene una lista de todos los hoteles.
	 * 
	 * @return la respuesta de la comunicación y la lista de hoteles
	 */
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Hotel>> listHotels() {

		try {
			List<Hotel> allHotels = hotelService.getAll(); 
			return ResponseEntity.status(HttpStatus.OK).body(allHotels);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

}
