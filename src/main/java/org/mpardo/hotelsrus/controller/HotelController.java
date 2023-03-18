package org.mpardo.hotelsrus.controller;

import java.util.Optional;

import org.mpardo.hotelsrus.dto.HotelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	private HotelService hotelService;
	
	@Autowired
	public HotelController(HotelService hotelService) {
		
		this.hotelService = hotelService;
		
	}
	
	// Crear un hotel en la BBDD
	@PostMapping("/ins")
	public ResponseEntity<?> createHotel(@RequestBody HotelDTO hotelDTO) {
		
		if (Optional.of(hotelDTO.getName()).isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else if (hotelService.existsByName(hotelDTO.getName())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		Integer id = hotelDTO.getId();
		String name = hotelDTO.getName();
		Integer category = hotelDTO.getCategory();
		Hotel hotel = new Hotel(id, name, category);
		
		hotelService.save(hotel);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
		
	}
	
	// Actualizar un hotel por el ID
	@PutMapping("/mod/{id}")
	public ResponseEntity<?> updateHotel(@PathVariable("id") Integer id, 
			@RequestBody HotelDTO hotelDTO) {
		
		if (!hotelService.existsById()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else if (Optional.of(hotelDTO.getName()).isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else if (hotelService.existsByName(hotelDTO.getName())
				&& hotelService.findByName(hotelDTO.getName()).get().getId != id) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		Hotel hotel = hotelService.getById(id).get();
		// TO-DO cargar los setters de Hotel con el los getters del HotelDTO
		
		hotelService.save(hotel);
		
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}
	
	// Consultar un hotel por el ID
	@GetMapping("/one/{id}")
	public ResponseEntity<Hotel> findHotelById(@PathVariable("id") Integer id) {
		
		if (hotelService.existsById(id)) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED);
		}
		
		Hotel hotel = hotelService.getById(id).get();
		return ResponseEntity.status(HttpStatus.OK).body(hotel);
		
	}
	
	// Obtener todos los hoteles existentes en la BBDD
	@GetMapping("/all")
	public ResponseEntity<List<Hotel>> listHotels() {
		
		List<Hotel> hotels = hotelService.list();
		
		return ResponseEntity.status(HttpStatus.OK).body(hotels);
		
	}
	
	
	
	
	
	
	
}
