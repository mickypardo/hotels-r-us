package org.mpardo.hotelsrus.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entidad del objeto Hotel.
 * 
 * @author micky pardo
 * 
 * @version 1.0
 *
 */
@Entity
@Table(name = "hotels", schema = "hotelsrus_database")
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_hotel", nullable = false)
	private Integer id;

	@Column(name = "name", length = 30, nullable = false, unique = true)
	private String name;

	@Column(name = "category")
	private Integer category;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
	private List<Availability> availabilities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
	private List<Booking> bookings;

	/**
	 * Constructor sin parametros.
	 */
	public Hotel() {
	}

	/**
	 * Constructor con parametros sin el 'id'.
	 * 
	 * @param name
	 * @param category
	 */
	public Hotel(String name, Integer category) {
		super();
		this.name = name;
		this.category = category;
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
	 * Getter name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter name
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter category
	 * 
	 * @return the category
	 */
	public Integer getCategory() {
		return category;
	}

	/**
	 * Setter category
	 * 
	 * @param category the category to set
	 */
	public void setCategory(Integer category) {
		this.category = category;
	}

}