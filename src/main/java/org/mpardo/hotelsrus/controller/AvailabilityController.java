package org.mpardo.hotelsrus.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.mpardo.hotelsrus.filter.HotelFilter;
import org.mpardo.hotelsrus.model.Availability;
import org.mpardo.hotelsrus.model.Hotel;
import org.mpardo.hotelsrus.service.IAvailabilityService;
import org.mpardo.hotelsrus.service.IHotelService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

	/**
	 * Obtiene una disponiblidad según su 'id'
	 * 
	 * @param id
	 * @return La respuesta de la comunicación y la disponibilidad en cuestión
	 */
	@GetMapping(value = "/one/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Availability> findAvailabilityById(@PathVariable("id") Integer id) {

		if (!hotelService.isById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		Availability availability = availabilityService.getOne(id).get();
		return ResponseEntity.status(HttpStatus.OK).body(availability);

	}

	/**
	 * Obtiene una lista de todos las disponibilidades.
	 * 
	 * @return la respuesta de la comunicación y la lista de hoteles
	 */
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Availability>> listAvailabilities() {

		List<Availability> allAvailabilities = availabilityService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(allAvailabilities);

	}

	/**
	 * Añade habitaciones disponibles en un hotel con un rango de fechas dadas
	 * 
	 * @param idHotel la id del hotel
	 * @param dateFrom la fecha de entrada
	 * @param dateTo la fecha de salida
	 * @param rooms el número de habitaciones a abrir
	 * @return la respuesta de la comunicación y si todo ha ido bien
	 */
	@PutMapping(value = "/availability/{id_hotel}/{date_from}/{date_to}/{rooms}")
	public ResponseEntity<?> openAvailability(@PathVariable("id_hotel") Integer idHotel, 
			@PathVariable("date_from") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dateFrom,
			@PathVariable("date_to") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dateTo, 
			@PathVariable("rooms") Integer rooms) {

		//Obtengo el rango de fechas
		long numberOfDays = ChronoUnit.DAYS.between(dateFrom, dateTo) + 1;
		List<LocalDate> range = Stream.iterate(dateFrom, date -> date.plusDays(1))
				.limit(numberOfDays).collect(Collectors.toList());
		
		//Obtengo listado de disponibles según hotel
		List<Availability> availForIdHotel = availabilityService.getAll().stream()
				.filter(p -> p.getHotel().getId() == idHotel).collect(Collectors.toList());
		
		for (LocalDate date : range) {

			Availability availability = availForIdHotel.stream().filter(avail -> avail.getDate().equals(date))
					.findFirst().orElse(null);
			
			if (availability != null) {
				//Modificamos la disponibilidad
				availability.setRooms(availability.getRooms() + rooms);
				availabilityService.update(availability);
			} else {
				//Crear una nueva disponibilidad										
				availabilityService.create(new Availability(date, hotelService.getOne(idHotel).get(), rooms));
			}
			
		}	
		
		return ResponseEntity.status(HttpStatus.OK).build();

	}
	
	/**
	 * Obtener la lista de hoteles que tenga disponibles habitaciones en un rango de fechas
	 * @param dateIn la fecha de entrada
	 * @param dateOut la fecha de salida
	 * @return la lista de hoteles, puede venir filtrada por un criterio en cuestión
	 */
	@GetMapping(value = "/criteria/{date_in}/{date_out}")
	public ResponseEntity<List<Hotel>> getAvailabilityByDateRange(
			@PathVariable("date_in") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateIn, 
			@PathVariable("date_out") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOut,
			HotelFilter hotelFilter) {
		
		List<Hotel> hotelsWithAvailability = new ArrayList<Hotel>();
		
		//Obtengo el rango de fechas
		long numberOfDays = ChronoUnit.DAYS.between(dateIn, dateOut) + 1;
		List<LocalDate> range = Stream.iterate(dateIn, date -> date.plusDays(1))
				.limit(numberOfDays).collect(Collectors.toList());
		
		//Obtengo listado de disponibles según hotel
//		List<Hotel> listHotels = hotelService.getAllByCriteria(hotelFilter);
		List<Hotel> listHotels = hotelService.getAll();
		for (Hotel hotel : listHotels) {
		
			List<Availability> availForIdHotel = availabilityService.getAll().stream()
					.filter(p -> p.getHotel().getId() == hotel.getId()).collect(Collectors.toList());
			
			boolean thereIsOneNull = false;
			
			for (LocalDate date : range) {
				
				Availability availability = availForIdHotel.stream().filter(avail -> avail.getDate().equals(date))
						.findFirst().orElse(null);
				
				if (availability == null) {
					// Con que haya uno nulo ya no me vale
					thereIsOneNull = true;
				}
				
			}
			
			if (!thereIsOneNull) {
				hotelsWithAvailability.add(hotel);
			}
			
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(hotelsWithAvailability);
		
	}

}
