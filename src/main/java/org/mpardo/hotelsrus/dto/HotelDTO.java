package org.mpardo.hotelsrus.dto;

import java.io.Serializable;

/**
 * Objeto de transferencia de datos del Hotel.
 * 
 * @author micky pardo
 * 
 * @version 1.0
 *
 */
public class HotelDTO implements Serializable {

	private Integer id;

	private String name;

	private Integer category;

	/**
	 * Constructor de la DTO Hotel.
	 * 
	 * @param id       es la identidad del hotel de tipo entero
	 * @param name     es el nombre del hotel de tipo cadena
	 * @param category es la categoria del hotel de tipo entero que abarca de 1 a 5
	 *                 estrellas
	 */
	public HotelDTO(Integer id, String name, Integer category) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
	}

	/**
	 * Método que obtiene la identidad del hotel.
	 * 
	 * @return la 'id' de tipo Integer
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Método que introduce la identidad al hotel.
	 * 
	 * @param id la 'id' de tipo Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Método que obtiene el nombre del hotel.
	 * 
	 * @return el 'name' de tipo String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Método que introduce el nombre al hotel.
	 * 
	 * @param name el 'name' de tipo String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Método que obtiene la categoría del hotel.
	 * 
	 * @return la category de tipo Integer
	 */
	public Integer getCategory() {
		return category;
	}

	/**
	 * Método que introduce la categoría al hotel.
	 * 
	 * @param category la 'category' de tipo Integer
	 */
	public void setCategory(Integer category) {
		this.category = category;
	}

}
