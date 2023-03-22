package org.mpardo.hotelsrus.controller;

import java.time.LocalDate;
import java.util.List;

import org.mpardo.hotelsrus.dto.BookingDTO;
import org.mpardo.hotelsrus.dto.HotelDTO;
import org.mpardo.hotelsrus.model.Booking;
import org.mpardo.hotelsrus.model.Hotel;
import org.mpardo.hotelsrus.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@Autowired
	public BookingController(IBookingService bookingService) {

		this.bookingService = bookingService;

	}

	// Hacer una reserva de un hotel en la BBDD
	@PostMapping("/res")
	public ResponseEntity<?> makeBooking(@RequestBody BookingDTO bookingDTO) {

//		if (bookingDTO.getHotelDTO() == null) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//		} else if (!bookingService.haveAvailableRooms(bookingDTO.getHotelDTO())) {
//			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//		}

		HotelDTO hotelDTO = bookingDTO.getHotelDTO();
		Hotel hotel = new Hotel(hotelDTO.getName(), hotelDTO.getCategory());
		LocalDate dateFrom = bookingDTO.getDateFrom();
		LocalDate dateTo = bookingDTO.getDateTo();
		String email = bookingDTO.getEmail();

		Booking booking = new Booking(hotel, dateFrom, dateTo, email);

		bookingService.create(booking);

		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	// Consultar todas las reservas de un hotel
	@GetMapping("/all")
	public ResponseEntity<List<Booking>> listBooking() {

		List<Booking> bookings = bookingService.getAll();

		return ResponseEntity.status(HttpStatus.OK).body(bookings);
	}

	// Obtener una reserva específica del hotel
	@GetMapping("/one/{id}")
	public ResponseEntity<Booking> findBookingById(@PathVariable("id") Integer id) {

		if (bookingService.isById(id)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		Booking booking = bookingService.getOne(id).get();
		return ResponseEntity.status(HttpStatus.OK).body(booking);

	}

	// Cancelar una reserva
	@DeleteMapping("/del/ {id}")
	public ResponseEntity<?> cancelBookingById(@PathVariable("id") Integer id) {

		if (!bookingService.isById(id)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		bookingService.delete(id);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
