package com.recipe.beerrecipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.beerrecipe.jdbcdao.JDBCRecipeDAO;
import com.recipe.beerrecipe.model.Recipe;

@CrossOrigin
@RestController 
public class MainController {

	@Autowired
	JDBCRecipeDAO dao;
	
	@RequestMapping(path = "/recipe", method = RequestMethod.GET)
	public List <Recipe> getAll() {
		List <Recipe> output = dao.getAll();
		return output;
	}
	
	@RequestMapping(path = "/recipe/{id}", method = RequestMethod.GET)
	public Recipe getRecipeById(@PathVariable long id) {
		Recipe output = dao.getRecipe(id);
		return output;
	}
	
	@RequestMapping(path = "/save", method = RequestMethod.POST)
	public String save(@RequestBody Recipe recipe) {
		return dao.save(recipe);
	}
	
	@RequestMapping(path = "/update/{id}", method = RequestMethod.PUT)
	public Recipe update(@RequestBody Recipe recipe, @PathVariable long id) {
		return dao.update(id, recipe);
	}
	
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable long id) {
		return dao.delete(id);
	}
	
}
