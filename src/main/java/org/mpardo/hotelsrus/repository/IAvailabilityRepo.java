package org.mpardo.hotelsrus.repository;

import org.mpardo.hotelsrus.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz Repositorio de Availability que extiende de JpaRepository
 * 
 * @author micky pardo
 * 
 * @version 0.5
 *
 */
@Repository
public interface IAvailabilityRepo extends JpaRepository<Availability, Integer> {

}
