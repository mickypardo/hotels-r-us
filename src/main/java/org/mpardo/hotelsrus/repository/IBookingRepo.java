package org.mpardo.hotelsrus.repository;

import org.mpardo.hotelsrus.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz Repositorio de Booking que extiende de JpaRepository
 * 
 * @author micky pardo
 * 
 * @version 0.5
 *
 */
@Repository
public interface IBookingRepo extends JpaRepository<Booking, Integer>{

}
