package org.mpardo.hotelsrus.testService;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mpardo.hotelsrus.model.Hotel;
import org.mpardo.hotelsrus.repository.IHotelRepo;
import org.mpardo.hotelsrus.service.impl.HotelServiceImpl;

@ExtendWith(MockitoExtension.class)
public class HotelServiceImplTest {

	private HotelServiceImpl hotelServiceImpl;
	
	@Mock private IHotelRepo iHotelRepo;
	
	@BeforeEach
	public void setup() {
		this.hotelServiceImpl = new HotelServiceImpl(iHotelRepo);
	}
	
	@Test
	public void testListHotels() {
		Mockito.when(iHotelRepo.findAll()).thenReturn(List.of(new Hotel("Test", 1)));
		
		List<Hotel> listHotel = hotelServiceImpl.getAll();
		
		Assertions.assertNotNull(listHotel);
	}
	
	@Test
	public void testGetOneHotel() {
		Hotel hotel = new Hotel();
		hotel.setId(1);
		hotel.setName("Test");
		hotel.setCategory(1);
		
		Mockito.when(iHotelRepo.findById(1)).thenReturn(Optional.of(hotel));
		
		Hotel hotelResp = hotelServiceImpl.getOne(1).get();
		
		Assertions.assertNotNull(hotelResp);
	}
	
	@Test
	public void testCreateOneHotel() {
		Hotel hotel = new Hotel("Test", 1);
		
		Hotel hotelCreated = new Hotel();
		Mockito.when(iHotelRepo.save(hotel)).thenReturn(hotelCreated);
		
		hotelServiceImpl.create(hotel);
		
		Assertions.assertNotNull(hotelCreated);
	}
	
	@Test
	public void testUpdateOneHotel() {
		Hotel hotel = new Hotel();
		hotel.setId(1);
		hotel.setName("Not Test");
		hotel.setCategory(1);
		
		Hotel hotelUpdated = new Hotel();
		Mockito.when(iHotelRepo.save(hotel)).thenReturn(hotelUpdated);
		
		hotelServiceImpl.update(hotel);
		
		Assertions.assertNotNull(hotelUpdated);
	}
	
	@Test
	public void testDeleteOneHotel() {
		Hotel hotel = new Hotel();
		hotel.setId(1);
		hotel.setName("Test");
		hotel.setCategory(1);
		
		hotelServiceImpl.delete(hotel.getId());
		
		Assertions.assertNull(hotel);
	}
}
