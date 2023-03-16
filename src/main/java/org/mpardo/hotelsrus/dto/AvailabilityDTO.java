package org.mpardo.hotelsrus.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Objeto de transferencia de datos de la Disponibilidad.
 *  
 * @author micky pardo
 * 
 * @version 1.0
 *
 */
public class AvailabilityDTO implements Serializable {
	
	private Integer id;
	
	private LocalDate date;
	
	private Integer rooms;

	/**
	 * Constructor de la DTO Availability.
	 * @param id es la identidad de la disponibilidad de tipo entero
	 * @param date es la fecha de la disponibilidad de tipo fecha
	 * @param rooms es el número de habitaciones libres de tipo entero
	 */
	public AvailabilityDTO(Integer id, LocalDate date, Integer rooms) {
		super();
		this.id = id;
		this.date = date;
		this.rooms = rooms;
	}

	/**
	 * Método que obtiene la identidad de la disponibilidad.
	 * @return la 'id' de tipo Integer
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Método que introduce la identidad de la disponibilidad.
	 * @param id la 'id' de tipo Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Método que obtiene la fecha de la disponibilidad.
	 * @return la 'date' de tipo LocalDate
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Método que introduce la fecha de la disponibilidad.
	 * @param date la 'date' de tipo LocalDate
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * Método que obtiene el número de habitaciones libres de la disponibilidad.
	 * @return las 'rooms' de tipo Integer
	 */
	public Integer getRooms() {
		return rooms;
	}

	/**
	 * Método que introduce el número de habitaciones libres a la disponibilidad.
	 * @param rooms las 'rooms' de tipo Integer
	 */
	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}

}
