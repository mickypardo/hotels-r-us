package org.mpardo.hotelsrus.service;

import java.util.Optional;

import org.mpardo.hotelsrus.model.Hotel;

/**
 * @author mapar
 *
 */
public interface IHotelService extends IGenericService<Hotel, Integer> {

	boolean isById(Integer id);

	boolean isByName(String name);

	Optional<Hotel> getByName(String name);
}
