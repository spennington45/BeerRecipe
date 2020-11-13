package com.recipe.beerrecipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.beerrecipe.jdbcdao.JDBCFavoriteDAO;
import com.recipe.beerrecipe.jdbcdao.JDBCRecipeDAO;
import com.recipe.beerrecipe.model.Favorites;
import com.recipe.beerrecipe.model.Recipe;

@CrossOrigin
@RestController 
public class RecipeController {

	@Autowired
	JDBCRecipeDAO recipeDao;
	@Autowired
	JDBCFavoriteDAO favoriteDao;
	
	@RequestMapping(path = "/recipe", method = RequestMethod.GET)
	public List <Recipe> getAll() {
		List <Recipe> output = recipeDao.getAll();
		return output;
	}
	
	@RequestMapping(path = "/recipe/{id}", method = RequestMethod.GET)
	public Recipe getRecipeById(@PathVariable long id) {
		Recipe output = recipeDao.getRecipe(id);
		return output;
	}
	
	@RequestMapping(path = "/save", method = RequestMethod.POST)
	public String save(@RequestBody Recipe recipe) {
		return recipeDao.save(recipe);
	}
	
	@RequestMapping(path = "/update/{id}", method = RequestMethod.PUT)
	public Recipe update(@RequestBody Recipe recipe, @PathVariable long id) {
		return recipeDao.update(id, recipe);
	}
	
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable long id) {
		return recipeDao.delete(id);
	}
	
	@RequestMapping(path = "/favorite", method = RequestMethod.POST)
	public void favorite(@RequestBody Favorites favorite) {
		favoriteDao.favorite(favorite.getUserId(), favorite.getRecipeId());
	}
	
	@RequestMapping(path = "/unfavorite", method = RequestMethod.PUT)
	public void unfavorite(@RequestBody Favorites favorite) {
		favoriteDao.unfavorite(favorite.getUserId(), favorite.getRecipeId());
	}
	
	@RequestMapping(path = "/favorite/{id}", method = RequestMethod.GET)
	public List<Favorites> getFavoriteByUserId(@PathVariable int id) {
		return favoriteDao.getFavoriteByUserId(id);
	}
	
}
