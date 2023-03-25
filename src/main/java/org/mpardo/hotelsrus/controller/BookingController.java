package org.mpardo.hotelsrus.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.mpardo.hotelsrus.model.Availability;
import org.mpardo.hotelsrus.model.Booking;
import org.mpardo.hotelsrus.model.Hotel;
import org.mpardo.hotelsrus.service.IAvailabilityService;
import org.mpardo.hotelsrus.service.IBookingService;
import org.mpardo.hotelsrus.service.IHotelService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de Booking que realiza de intermediario entre el Frontend y el
 * Servicio.
 * 
 * @author micky pardo
 * 
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/bookings")
public class BookingController {

	private IBookingService bookingService;

	private IHotelService hotelService;
	
	private IAvailabilityService availabilityService;

	public BookingController(IBookingService bookingService, IHotelService hotelService, IAvailabilityService availabilityService) {

		this.bookingService = bookingService;

		this.hotelService = hotelService;
		
		this.availabilityService = availabilityService;

	}

	// Hacer una reserva de un hotel en la BBDD
	@PostMapping(value = "/res/{id_hotel}/{date_in}/{date_out}/{email}")
	public ResponseEntity<?> makeBooking(@PathVariable("id_hotel") Integer idHotel,
			@PathVariable("date_in") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateIn,
			@PathVariable("date_out") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOut,
			@PathVariable("email") String email) {

		Hotel hotel = hotelService.getOne(idHotel).get();
		Booking booking = new Booking(hotel, dateIn, dateOut, email);

		bookingService.create(booking);

		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	// Consultar todas las reservas de un hotel
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Booking>> listBookings() {

		List<Booking> bookings = bookingService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(bookings);
	}

	// Obtener un grupo de reservas de un hotel en un rango de fechas dado
	@GetMapping("/group/{id}/{date_init}/{date_end}")
	public ResponseEntity<List<Booking>> listBookingsByIdAndDateRange(@PathVariable("id") Integer id,
			@PathVariable("date_init") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateInit,
			@PathVariable("date_end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateEnd) {

		List<Booking> listBookingsInRange = new ArrayList<>();

		List<Booking> listBookings = bookingService.getAllByHotel(id);

		// Obtengo el rango de fechas
		long numberOfDays = ChronoUnit.DAYS.between(dateInit, dateEnd) + 1;
		List<LocalDate> range = Stream.iterate(dateInit, date -> date.plusDays(1)).limit(numberOfDays)
				.collect(Collectors.toList());

		for (Booking booking : listBookings) {

			long numberOfDaysBooking = ChronoUnit.DAYS.between(booking.getDateFrom(), booking.getDateTo()) + 1;
			List<LocalDate> rangeBooking = Stream.iterate(booking.getDateFrom(), date -> date.plusDays(1))
					.limit(numberOfDaysBooking).collect(Collectors.toList());

			if (rangeBooking.containsAll(range)) {
				listBookingsInRange.add(booking);
			}

		}

		return ResponseEntity.status(HttpStatus.OK).body(listBookingsInRange);

	}

	@GetMapping(value = "/booking/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Booking> findBookingById(@PathVariable("id") Integer id) {

		if (!bookingService.isById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		Booking booking = bookingService.getOne(id).get();
		return ResponseEntity.status(HttpStatus.OK).body(booking);

	}

	// Cancelar una reserva
	@DeleteMapping("/del/{id}")
	public ResponseEntity<?> cancelBookingByIdAndUpdateAvailabilities(@PathVariable("id") Integer id) {

		if (!bookingService.isById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		Booking booking = bookingService.getOne(id).get();
		
		long numberOfDaysBooking = ChronoUnit.DAYS.between(booking.getDateFrom(), booking.getDateTo()) + 1;
		List<LocalDate> rangeBooking = Stream.iterate(booking.getDateFrom(), date -> date.plusDays(1))
				.limit(numberOfDaysBooking).collect(Collectors.toList());
		
		List<Availability> listAvail = availabilityService.getAllByIdHotel(booking.getHotel().getId());
		
		for (Availability avail : listAvail) {
			
			if (rangeBooking.contains(avail.getDate())) {
				availabilityService.updateRoom(avail);
			}
			
		}
		
		bookingService.delete(id);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
