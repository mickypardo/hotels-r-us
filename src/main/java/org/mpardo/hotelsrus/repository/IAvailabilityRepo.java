package org.mpardo.hotelsrus.repository;

import java.util.List;

import org.mpardo.hotelsrus.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Interfaz Repositorio de Availability que extiende de JpaRepository
 * 
 * @author micky pardo
 * 
 * @version 1.0
 *
 */
@Repository
public interface IAvailabilityRepo extends JpaRepository<Availability, Integer> {

	@Query(value = "SELECT * FROM hotelsrus_database.availabilities WHERE id_hotel = ?1", nativeQuery = true)
	List<Availability> findAllByIdHotel(Integer id);

	@Query(value = "UPDATE hotelsrus_database.availabilities SET rooms = rooms-1 WHERE id_availability = ?1 ", nativeQuery = true)
	void updateTheRooms(Integer id);

}
