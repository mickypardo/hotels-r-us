package org.mpardo.hotelsrus.controller;

import java.util.List;
import java.util.Optional;

import org.mpardo.hotelsrus.dto.HotelDTO;
import org.mpardo.hotelsrus.model.Hotel;
import org.mpardo.hotelsrus.service.IHotelService;
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

	@Autowired
	public HotelController(IHotelService hotelService) {

		this.hotelService = hotelService;

	}

	// Crear un hotel en la BBDD
	@PostMapping("/ins")
	public ResponseEntity<?> createHotel(@RequestBody HotelDTO hotelDTO) {

//		if (Optional.of(hotelDTO.getName()).isEmpty()) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		} else if (hotelService.isByName(hotelDTO.getName())) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		}

		String name = hotelDTO.getName();
		Integer category = hotelDTO.getCategory();
		Hotel hotel = new Hotel(name, category);

		hotelService.create(hotel);

		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	// Actualizar un hotel por el ID
	@PutMapping("/mod/{id}")
	public ResponseEntity<?> updateHotel(@PathVariable("id") Integer id, @RequestBody HotelDTO hotelDTO) {

		if (!hotelService.isById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else if (Optional.of(hotelDTO.getName()).isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else if (hotelService.isByName(hotelDTO.getName())
				&& hotelService.getByName(hotelDTO.getName()).get().getId() != id) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		Hotel hotel = hotelService.getOne(id).get();
		// TO-DO cargar los setters de Hotel con el los getters del HotelDTO

		hotelService.update(hotel);

		return ResponseEntity.status(HttpStatus.OK).build();

	}

	// Consultar un hotel por el ID
	@GetMapping("/one/{id}")
	public ResponseEntity<Hotel> findHotelById(@PathVariable("id") Integer id) {

		if (!hotelService.isById(id)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		Hotel hotel = hotelService.getOne(id).get();
		return ResponseEntity.status(HttpStatus.OK).body(hotel);

	}

	// Obtener todos los hoteles existentes en la BBDD
	@GetMapping("/all")
	public ResponseEntity<List<Hotel>> listHotels() {

		List<Hotel> hotels = hotelService.getAll();

		return ResponseEntity.status(HttpStatus.OK).body(hotels);

	}

}
