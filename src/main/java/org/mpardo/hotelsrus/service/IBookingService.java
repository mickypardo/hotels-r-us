package org.mpardo.hotelsrus.service;

import java.util.List;
import java.util.Optional;

import org.mpardo.hotelsrus.model.Booking;

/**
 * Interfaz del servicio Booking
 * 
 * @author micky pardo
 * 
 */
public interface IBookingService {

	// CREATE
	void create(Booking booking);

	// FIND ALL
	List<Booking> getAll();

	// FIND ONE
	Optional<Booking> getOne(Integer id);

	// UPDATE
	void update(Booking booking);

	// DELETE
	void delete(Integer id);
	
	// OTHERS
	boolean isById(Integer id);

	List<Booking> getAllByHotel(Integer id);

}
