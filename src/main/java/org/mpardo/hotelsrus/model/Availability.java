package org.mpardo.hotelsrus.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidad del objeto Availability.
 * 
 * @author micky pardo
 * 
 * @version 1.0
 *
 */
@Entity
@Table(name = "availabilities", schema = "hotelsrus_database")
public class Availability {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_availability", nullable = false)
	private Integer id;

	@Column(name = "date")
	private LocalDate date;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_hotel", nullable = false)
	private Hotel hotel;

	@Column(name = "rooms")
	private Integer rooms;

	/**
	 * Constructor sin parámetros.
	 */
	public Availability() {
	}

	/**
	 * Constructor con parámetros sin 'id'.
	 * 
	 * @param date
	 * @param hotel
	 * @param rooms
	 */
	public Availability(LocalDate date, Hotel hotel, Integer rooms) {
		super();
		this.date = date;
		this.hotel = hotel;
		this.rooms = rooms;
	}

	/**
	 * Getter id
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter id
	 * 
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter date
	 * 
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Setter date
	 * 
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * Getter hotel
	 * 
	 * @return the hotel
	 */
	public Hotel getHotel() {
		return hotel;
	}

	/**
	 * Setter hotel
	 * 
	 * @param hotel the hotel to set
	 */
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	/**
	 * Getter rooms
	 * 
	 * @return the rooms
	 */
	public Integer getRooms() {
		return rooms;
	}

	/**
	 * Setter rooms
	 * 
	 * @param rooms the rooms to set
	 */
	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}

}