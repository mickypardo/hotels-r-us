package org.mpardo.hotelsrus.service;

import java.util.List;
import java.util.Optional;

import org.mpardo.hotelsrus.dto.BookingDTO;
import org.mpardo.hotelsrus.dto.HotelDTO;
import org.mpardo.hotelsrus.model.Booking;
import org.mpardo.hotelsrus.model.Hotel;

/**
 * @author micky pardo
 * 
 * @version 1.0
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

	boolean haveAvailableRooms(HotelDTO hotelDTO);

}
