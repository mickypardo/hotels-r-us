package org.mpardo.hotelsrus.filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.mpardo.hotelsrus.model.Hotel;
import org.springframework.data.jpa.domain.Specification;

/**
 * Especificaciones para el filtrado del objeto Hotel.
 * 
 * @author micky pardo
 *
 */
public class HotelSpecifications {

	/**
	 * Filtrado por nombre
	 * 
	 * @param name de tipo String
	 * @return Specification del atributo name
	 */
	public Specification<Hotel> filterName(String name) {
		Specification<Hotel> nameSpec = (
				Root<Hotel> root, 
				CriteriaQuery<?> query, 
				CriteriaBuilder criteriaBuilder
				) -> criteriaBuilder. equal(root.get("name"), name);
				
		return nameSpec;
	}
	
	/**
	 * Filtrado por category
	 * 
	 * @param category de tipo String
	 * @return Specification del atributo category
	 */
	public Specification<Hotel> filterCategory(String category) {
		Specification<Hotel> categorySpec = (
				Root<Hotel> root,
				CriteriaQuery<?> query,
				CriteriaBuilder criteriaBuilder				
				) -> criteriaBuilder.equal(root.get("category"), category);
				
		return categorySpec;
	}
	
}
