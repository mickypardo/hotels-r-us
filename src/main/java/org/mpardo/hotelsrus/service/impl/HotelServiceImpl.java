package org.mpardo.hotelsrus.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.mpardo.hotelsrus.filter.HotelFilter;
import org.mpardo.hotelsrus.model.Hotel;
import org.mpardo.hotelsrus.repository.IHotelRepo;
import org.mpardo.hotelsrus.service.IHotelService;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio Hotel.
 * 
 * @author micky pardo
 * 
 * @version 1.0
 */
@Service
public class HotelServiceImpl implements IHotelService {

	/********************************
	 *** INYECCIÓN DE DEPENDENCIA ***
	 ********************************/

	EntityManager em;
	
	IHotelRepo hotelRepo;

	public HotelServiceImpl(IHotelRepo repo) {
		this.hotelRepo = repo;
	}

	/********************************
	 ************ CREATE ************
	 ********************************/

	@Override
	public void create(Hotel t) {
		hotelRepo.save(t);
	}

	/********************************
	 ************* READ *************
	 ********************************/

	@Override
	public List<Hotel> getAll() {
		return hotelRepo.findAll();
	}

	@Override
	public Optional<Hotel> getOne(Integer id) {
		return hotelRepo.findById(id);
	}

	@Override
	public boolean isById(Integer id) {
		return hotelRepo.existsById(id);
	}

	@Override
	public boolean isByName(String name) {
		return hotelRepo.existsByName(name);
	}

	/********************************
	 ************ UPDATE ************
	 ********************************/

	@Override
	public void update(Hotel t) {
		hotelRepo.save(t);
	}

	/********************************
	 ************ DELETE ************
	 ********************************/

	@Override
	public void delete(Integer id) {
		hotelRepo.deleteById(id);
	}

	/********************************
	 ******* GET BY CRITERIA ********
	 ********************************/
	@Override
	public List<Hotel> getAllByCriteria(HotelFilter hotelFilter) {
		
		CriteriaBuilder criteriaBuilder =  em.getCriteriaBuilder();
		
		CriteriaQuery<Hotel> criteriaQuery = criteriaBuilder.createQuery(Hotel.class);
		
		// SELECT * FROM hotel
		Root<Hotel> root = criteriaQuery.from(Hotel.class);
		
		// Preparo clausula WHERE
		Predicate predicate = criteriaBuilder.conjunction();
		
		if (hotelFilter.getName() != null) {
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("name"), hotelFilter.getName()));
		}
		
		if (hotelFilter.getCategory() != null) {
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("category"), hotelFilter.getCategory()));
		}
		
		criteriaQuery.where(predicate);
		
		return em.createQuery(criteriaQuery).getResultList();
		
	}

}
