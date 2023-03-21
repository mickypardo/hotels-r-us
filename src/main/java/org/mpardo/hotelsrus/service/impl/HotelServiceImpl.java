package org.mpardo.hotelsrus.service.impl;

import java.util.List;
import java.util.Optional;

import org.mpardo.hotelsrus.model.Hotel;
import org.mpardo.hotelsrus.repository.IHotelRepo;
import org.mpardo.hotelsrus.service.IHotelService;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio Hotel.
 * 
 * @author micky pardo
 * 
 * @version 1.0
 *
 */
@Service
public class HotelServiceImpl implements IHotelService {

	/********************************
	 *** INYECCIÓN DE DEPENDENCIA ***
	 ********************************/

	IHotelRepo hotelRepo;

	public HotelServiceImpl(IHotelRepo repo) {
		this.hotelRepo = repo;
	}

	/********************************
	 ************ CREATE ************
	 ********************************/

	@Override
	public void create(Hotel t) {
		hotelRepo.save(t);
	}

	/********************************
	 ************* READ *************
	 ********************************/

	@Override
	public List<Hotel> getAll() {
		return hotelRepo.findAll();
	}

	@Override
	public Optional<Hotel> getOne(Integer id) {
		return hotelRepo.findById(id);
	}

	@Override
	public boolean isById(Integer id) {
		return hotelRepo.existsById(id);
	}

	@Override
	public boolean isByName(String name) {
		return hotelRepo.existsByName(name);
	}

	@Override
	public Optional<Hotel> getByName(String name) {
		return hotelRepo.findByName(name);
	}

	/********************************
	 ************ UPDATE ************
	 ********************************/

	@Override
	public void update(Hotel t) {
		hotelRepo.save(t);
	}

	/********************************
	 ************ DELETE ************
	 ********************************/

	@Override
	public void delete(Integer id) {
		hotelRepo.deleteById(id);
	}

}
