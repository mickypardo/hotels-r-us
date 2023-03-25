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
 * Entidad del objeto Booking.
 * 
 * @author micky pardo
 * 
 * @version 1.0
 *
 */
@Entity
@Table(name = "bookings", schema = "hotelsrus_database")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_booking")
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_hotel", nullable = false)
	private Hotel hotel;

	@Column(name = "date_from")
	private LocalDate dateFrom;

	@Column(name = "date_to")
	private LocalDate dateTo;

	@Column(name = "email", length = 50, nullable = false)
	private String email;

	/**
	 * Constructor sin parámetros.
	 */
	public Booking() {
	}

	/**
	 * Constructor con parámetros sin el 'id'.
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @param email
	 */
	public Booking(Hotel hotel, LocalDate dateFrom, LocalDate dateTo, String email) {
		super();
		this.hotel = hotel;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.email = email;
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
	 * Getter dateFrom
	 * 
	 * @return the dateFrom
	 */
	public LocalDate getDateFrom() {
		return dateFrom;
	}

	/**
	 * Setter dateFrom
	 * 
	 * @param dateFrom the dateFrom to set
	 */
	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * Getter dateTo
	 * 
	 * @return the dateTo
	 */
	public LocalDate getDateTo() {
		return dateTo;
	}

	/**
	 * Setter dateTo
	 * 
	 * @param dateTo the dateTo to set
	 */
	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}

	/**
	 * Getter email
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter email
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}