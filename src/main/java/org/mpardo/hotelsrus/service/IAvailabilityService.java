package org.mpardo.hotelsrus.service;

import java.util.List;
import java.util.Optional;

import org.mpardo.hotelsrus.model.Availability;
import org.mpardo.hotelsrus.model.Hotel;

/**
 * @author mapar
 *
 */
public interface IAvailabilityService {

	// CREATE
	void create(Availability availability);

	// FIND ALL
	List<Availability> getAll();

	// FIND ONE
	Optional<Availability> getOne(Integer id);

	// UPDATE
	void update(Availability availability);

	// DELETE
	void delete(Integer id);
	
}
