package org.mpardo.hotelsrus.repository;

import java.util.List;

import org.mpardo.hotelsrus.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Interfaz Repositorio de Booking que extiende de JpaRepository
 * 
 * @author micky pardo
 * 
 * @version 1.0
 *
 */
@Repository
public interface IBookingRepo extends JpaRepository<Booking, Integer> {

	@Query(value = "SELECT * FROM hotelsrus_database.bookings WHERE id_hotel = ?1", nativeQuery = true)
	List<Booking> findAllByHotel(Integer id);

}
