package com.recipe.beerrecipe.jdbcdao;

import java.util.List;

import com.recipe.beerrecipe.model.Favorites;

public interface FavoriteDAO {

	public void favorite(int userId, int recipeId);
	
	public void unfavorite(int userId, int recipeId);
	
	public List<Favorites> getFavoriteByUserId(int userId);
	
}
