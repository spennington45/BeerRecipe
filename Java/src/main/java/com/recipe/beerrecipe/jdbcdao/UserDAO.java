package com.recipe.beerrecipe.jdbcdao;

import java.util.List;

import com.recipe.beerrecipe.model.User;
//interface for user
public interface UserDAO {

	List<User> findAll();

	User getUserById(Long userId);

	User findByUsername(String username);

	int findIdByUsername(String username);

	boolean create(String username, String password, String role);
	
	public void deleteUserById(long id);
	
}
