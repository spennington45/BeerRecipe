package com.recipe.beerrecipe.jdcpdao;

import java.util.List;

import com.recipe.beerrecipe.model.Recipe;

public interface RecipeDAO {

	public List <Recipe> getAll();
	public Recipe getRecipe(long id);
	public Recipe update(long id, Recipe recipe);
	public String save(Recipe recipe);
	public String delete(long id);
	
}
