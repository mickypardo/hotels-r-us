package org.mpardo.hotelsrus.service;

import java.util.List;
import java.util.Optional;

public interface IGenericService<T, ID> {

	// CREATE
	void create(T t);

	// FIND ALL
	List<T> getAll();

	// FIND ONE
	Optional<T> getOne(ID id);

	// UPDATE
	void update(T t);

	// DELETE
	void delete(ID id);
}
