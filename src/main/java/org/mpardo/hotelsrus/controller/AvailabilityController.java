package org.mpardo.hotelsrus.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.mpardo.hotelsrus.dto.AvailabilityDTO;
import org.mpardo.hotelsrus.dto.HotelDTO;
import org.mpardo.hotelsrus.model.Availability;
import org.mpardo.hotelsrus.model.Hotel;
import org.mpardo.hotelsrus.service.IAvailabilityService;
import org.mpardo.hotelsrus.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de Availability que realiza de intermediario entre el Frontend y
 * el Servicio.
 * 
 * @author micky pardo
 * 
 * @version 1.0
 *
 */
@RestController
@RequestMapping("hotelsrus/availabilities")
public class AvailabilityController {

	private IAvailabilityService availabilityService;
	private IHotelService hotelService;

	public AvailabilityController(IAvailabilityService availabilityService, IHotelService hotelService) {

		this.availabilityService = availabilityService;
		this.hotelService = hotelService;

	}
	
	
	/**
	 * Crea un hotel nuevo.
	 * 
	 * @param hotelDTO
	 * @return la respuesta de la comunicación
	 */
	@PostMapping(value="/ins", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createHotel(@RequestBody AvailabilityDTO availabilityDTO) {

		try {
			LocalDate date = availabilityDTO.getDate();
			Integer rooms = availabilityDTO.getRooms();
			System.out.println(date + " , " + rooms);
			Hotel hotel = hotelService.getOne(25).get();
			Availability availability = new Availability(date, hotel, rooms);
		
			availabilityService.create(availability);
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
	@PutMapping("/mod/{id}")
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
	@GetMapping("/one/{id}")
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
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Availability>> listHotels() {

		try {
			List<Availability> allHotels = availabilityService.getAll(); 
			return ResponseEntity.status(HttpStatus.OK).body(allHotels);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	
	
	
	
	@PutMapping("/availability/{id_hotel}/{date_from}/{date_to}/{rooms}")
	public ResponseEntity<?> openAvailability(
			@RequestBody AvailabilityDTO availabilityDTO, 
			@PathVariable("id_hotel") Integer id_hotel, 
			@PathVariable("date_from") LocalDate dateFrom, 
			@PathVariable("date_to") LocalDate dateTo,
			@PathVariable("rooms") Integer rooms) {
		

		List<LocalDate> dates = dateFrom.datesUntil(dateTo).collect(Collectors.toList());
		
		List<Availability> availabilities = availabilityService.getAllByHotel(id_hotel);
		
		Hotel hotel = hotelService.getOne(id_hotel).get();
		
		for(LocalDate date : dates) {
			
			if(availabilities.contains(date)) {
				
				for(Availability availability : availabilities) {
					if(availability.equals(date)) {
						availability.setRooms(availabilityDTO.getRooms()+rooms);
						availabilityService.update(availability);
					}
				}
				
			} else {
				
				Availability availability = new Availability(date, hotel, rooms);
				
				availabilityService.create(availability);
			}
			
		}
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		
	}

}
