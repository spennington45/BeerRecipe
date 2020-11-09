package com.recipe.beerrecipe.jdbcdao;

public interface FavoriteDAO {

	public void favorite(long userId, long recipeId);
	
	public void unfavorite(long userId, long recipeId);
	
}
