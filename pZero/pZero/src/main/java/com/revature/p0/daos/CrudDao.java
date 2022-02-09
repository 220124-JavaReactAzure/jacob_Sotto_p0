package com.revature.p0.daos;

import com.revature.p0.util.collections.List;

//created interface dao
public interface CrudDao<T> {
	
	T create(T newObj); //creates a create method with one parameter that accepts any type
	
	List<T> findAll(); //creates a list to find all of something
	T findById(String id); // creates a find by idea constructor
	
	boolean update(T updatedObj); //creates update
	
	boolean delete(String id); //creates delete

}
