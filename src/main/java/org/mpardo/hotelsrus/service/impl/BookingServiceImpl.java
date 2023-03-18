package org.mpardo.hotelsrus.service.impl;

import java.util.List;
import java.util.Optional;

import org.mpardo.hotelsrus.model.Booking;
import org.mpardo.hotelsrus.repository.IBookingRepo;
import org.mpardo.hotelsrus.service.IBookingService;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio Booking.
 * 
 * @author micky pardo
 * 
 * @version 1.0
 *
 */
@Service
public class BookingServiceImpl implements IBookingService {

	/********************************
	 *** INYECCIÓN DE DEPENDENCIA ***
	 ********************************/
	
	IBookingRepo bookingRepo;
	
	public BookingServiceImpl(IBookingRepo repo) {
		this.bookingRepo = repo;
	}
	
	/********************************
	 ************ CREATE ************
	 ********************************/
	
	@Override
	public void create(Booking t) {
		bookingRepo.save(t);
	}

	/********************************
	 ************* READ *************
	 ********************************/
	
	@Override
	public List<Booking> getAll() {
		return bookingRepo.findAll();
	}

	@Override
	public Optional<Booking> getOne(Integer id) {
		return bookingRepo.findById(id);
	}
	
	public boolean getById(Integer id) {
		return bookingRepo.existsById(id);
	}

	/********************************
	 ************ UPDATE ************
	 ********************************/
	
	@Override
	public void update(Booking t) {
		bookingRepo.save(t);
	}
	
	/********************************
	 ************ DELETE ************
	 ********************************/
	
	@Override
	public void delete(Integer id) {
		bookingRepo.deleteById(id);
	}

}
