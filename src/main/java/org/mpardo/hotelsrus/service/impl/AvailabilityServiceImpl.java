package org.mpardo.hotelsrus.service.impl;

import java.util.List;
import java.util.Optional;

import org.mpardo.hotelsrus.model.Availability;
import org.mpardo.hotelsrus.repository.IAvailabilityRepo;
import org.mpardo.hotelsrus.service.IAvailabilityService;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio Availability.
 * 
 * @author micky pardo
 * 
 * @version 1.0
 *
 */
@Service
public class AvailabilityServiceImpl implements IAvailabilityService {

	/********************************
	 *** INYECCIÓN DE DEPENDENCIA ***
	 ********************************/

	IAvailabilityRepo availabilityRepo;

	public AvailabilityServiceImpl(IAvailabilityRepo repo) {
		this.availabilityRepo = repo;
	}

	/********************************
	 ************ CREATE ************
	 ********************************/

	@Override
	public void create(Availability t) {
		availabilityRepo.save(t);
	}

	/********************************
	 ************* READ *************
	 ********************************/

	@Override
	public List<Availability> getAll() {
		return availabilityRepo.findAll();
	}

	@Override
	public Optional<Availability> getOne(Integer id) {
		return availabilityRepo.findById(id);
	}
	
	/********************************
	 ************ UPDATE ************
	 ********************************/

	@Override
	public void update(Availability t) {
		availabilityRepo.save(t);
	}

	/********************************
	 ************ DELETE ************
	 ********************************/

	@Override
	public void delete(Integer id) {
		availabilityRepo.deleteById(id);
	}

	@Override
	public List<Availability> getAllByIdHotel(Integer id) {
		return availabilityRepo.findAllByIdHotel(id);
	}

	@Override
	public void updateRoom(Availability avail) {
		availabilityRepo.updateTheRooms(avail.getId());		
	}


}