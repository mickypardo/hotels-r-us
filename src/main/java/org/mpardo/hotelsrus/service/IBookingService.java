package org.mpardo.hotelsrus.service;

import org.mpardo.hotelsrus.dto.HotelDTO;
import org.mpardo.hotelsrus.model.Booking;

/**
 * @author mapar
 *
 */
public interface IBookingService extends IGenericService<Booking, Integer> {

	boolean isById(Integer id);

	boolean haveAvailableRooms(HotelDTO hotelDTO);

}
