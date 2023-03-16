/**
 * 
 */
package org.mpardo.hotelsrus.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Objeto de transferencia de datos de la Reserva.
 * 
 * @author micky pardo
 * 
 * @version 1.0
 *
 */
public class BookingDTO implements Serializable {
	
	private Integer id;
	
	private HotelDTO hotelDTO;
	
	private LocalDate dateFrom;
	
	private LocalDate dateTo;
	
	private String email;

	/**
	 * Constructor de la DTO Booking.
	 * @param id es la identidad de la reserva de tipo entero
	 * @param hotelDTO es la instancia del objeto HotelDTO
	 * @param dateFrom es la fecha del inicio de reserva de tipo fecha
	 * @param dateTo es la fecha del fin de reserva de tipo fecha
	 * @param email es el correo electrónico del que reserva de tipo cadena
	 */
	public BookingDTO(Integer id, HotelDTO hotelDTO, LocalDate dateFrom, LocalDate dateTo, String email) {
		super();
		this.id = id;
		this.hotelDTO = hotelDTO;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.email = email;
	}

	/**
	 * Método que obtiene la identidad de la reserva.
	 * @return la 'id' de tipo Integer
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Método que introduce la identidad de la reserva.
	 * @param id la 'id' de tipo Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Método que obtiene la instancia del HotelDTO de la reserva.
	 * @return el 'hotelDTO' de tipo HotelDTO
	 */
	public HotelDTO getHotelDTO() {
		return hotelDTO;
	}

	/**
	 * Método que introduce la instancia del HotelDTO de la reserva.
	 * @param hotelDTO el 'hotelDTO' de tipo HotelDTO
	 */
	public void setHotelDTO(HotelDTO hotelDTO) {
		this.hotelDTO = hotelDTO;
	}

	/**
	 * Método que obtiene la fecha de inicio de la reserva.
	 * @return la 'dateFrom' de tipo LocalDate
	 */
	public LocalDate getDateFrom() {
		return dateFrom;
	}

	/**
	 * Método que introduce la fecha de inicio de la reserva.
	 * @param dateFrom la 'dateFrom' de tipo LocalDate
	 */
	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * Método que obtiene la fecha de finalización de la reserva.
	 * @return la 'dateTo' de tipo LocalDate
	 */
	public LocalDate getDateTo() {
		return dateTo;
	}

	/**
	 * Método que introduce la fecha de finalización de la reserva.
	 * @param dateTo la 'dateTo' de tipo LocalDate
	 */
	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}

	/**
	 * Método que obtiene el correo electrónico del cliente de la reserva.
	 * @return el 'email' de tipo String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Método que introduce el correo electrónico del cliente de la reserva.
	 * @param email el 'email' de tipo String
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
