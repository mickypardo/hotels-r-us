package org.mpardo.hotelsrus.service;

import java.util.List;
import java.util.Optional;

import org.mpardo.hotelsrus.filter.HotelFilter;
import org.mpardo.hotelsrus.model.Hotel;

/**
 * Interfaz del servicio Hotel
 * 
 * @author micky pardo
 * 
 */
public interface IHotelService {

	// CREATE
	void create(Hotel hotel);

	// FIND ALL
	List<Hotel> getAll();

	// FIND ONE
	Optional<Hotel> getOne(Integer id);

	// UPDATE
	void update(Hotel hotel);

	// DELETE
	void delete(Integer id);
	
	// OTHERS
	boolean isById(Integer id);

	boolean isByName(String name);

	List<Hotel> getAllByCriteria(HotelFilter hotelFilter);
	
}
