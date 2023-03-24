package org.mpardo.hotelsrus.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.mpardo.hotelsrus.model.Availability;
import org.mpardo.hotelsrus.repository.IAvailabilityRepo;
import org.mpardo.hotelsrus.service.IAvailabilityService;
import org.springframework.data.jpa.repository.Query;
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

	public boolean getById(Integer id) {
		return availabilityRepo.existsById(id);
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
	public void createAvailabilitiesByHotel(Integer idHotel, List<LocalDate> range, Integer rooms) {
		List<Availability> listAvailByHotel = availabilityRepo.findAllByHotel(idHotel);
		for (LocalDate date : range) {
			
			for (Availability avail : listAvailByHotel) {
				
				if(avail.getDate() == date) {
					avail.setRooms(avail.getRooms()+rooms);
				} else {
					availabilityRepo.create();
				}
			
		}
	}

}