package org.mpardo.hotelsrus.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.mpardo.hotelsrus.dto.AvailabilityDTO;
import org.mpardo.hotelsrus.model.Availability;
import org.mpardo.hotelsrus.model.Hotel;
import org.mpardo.hotelsrus.service.IAvailabilityService;
import org.mpardo.hotelsrus.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/availabilities")
public class AvailabilityController {

	private IAvailabilityService availabilityService;
	private IHotelService hotelService;

	public AvailabilityController(IAvailabilityService availabilityService, IHotelService hotelService) {

		this.availabilityService = availabilityService;
		this.hotelService = hotelService;

	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Availability>> listAvailabilities() {
		try {
			List<Availability> allAvailabilities = availabilityService.getAll(); 
			return ResponseEntity.status(HttpStatus.OK).body(allAvailabilities);
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
