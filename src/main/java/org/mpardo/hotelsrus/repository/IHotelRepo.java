package org.mpardo.hotelsrus.repository;

import java.util.Optional;

import org.mpardo.hotelsrus.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz Repositorio de Hotel que extiende de JpaRepository
 * 
 * @author micky pardo
 * 
 * @version 1.0
 *
 */
@Repository
public interface IHotelRepo extends JpaRepository<Hotel, Integer> {

	public boolean existsById(Integer id);

	public boolean existsByName(String name);

	public Optional<Hotel> findByName(String name);

}
