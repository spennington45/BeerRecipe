package com.recipe.beerrecipe.jdbcdao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.recipe.beerrecipe.exception.RecipeNotFoundException;
import com.recipe.beerrecipe.model.Recipe;

@Component
public class JDBCRecipeDAO implements RecipeDAO {

	@Autowired
	private JdbcTemplate template;

	@Override
	public List<Recipe> getAll() {
		List <Recipe> list = new ArrayList<> ();
		String sql = "SELECT * FROM beerRecipe";
		SqlRowSet results = template.queryForRowSet(sql);
		while (results.next()) {
			Recipe recipe = mapRowToRecipe(results);
			list.add(recipe);
		}
		return list;
	}

	@Override
	public Recipe getRecipe(long id) {
		Recipe recipe = new Recipe();
		String sql = "SELECT * FROM beerrecipe WHERE id = ?";
		SqlRowSet results = template.queryForRowSet(sql, id);
		if (results.next()) {
			recipe = mapRowToRecipe(results);
		} else {
			throw new RecipeNotFoundException();
		}
		return recipe;
	}

	@Override
	public Recipe update(long id, Recipe recipe) {
		String sql = "UPDATE beerrecipe SET name = ?, ingredients = ?, units = ?, volume = ?, abv = ?, directions = ?, author = ? WHERE id = ?"; 
		template.update(sql, recipe.getName(), recipe.getIngredients(), recipe.getUnits(), recipe.getVolume(), recipe.getAbv(), recipe.getDirections(), recipe.getAuthor(), id);
		Recipe output = getRecipe(id);
		return output;
	}

	@Override
	public String save(Recipe recipe) {
		String sql = "INSERT INTO beerrecipe (name, ingredients, volume, units, abv, directions, author, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		template.update(sql, recipe.getName(), recipe.getIngredients(), recipe.getVolume(), recipe.getUnits(), recipe.getAbv(), recipe.getDirections(), recipe.getAuthor(), recipe.getUserId());
		return "Recipe saved";
	}

	@Override
	public String delete(long id) {
		String sql = "DELETE FROM beerrecipe WHERE id = ?";
		template.update(sql, id);
		return "Deleted " + id;
	}
	
	private Recipe mapRowToRecipe(SqlRowSet sql) {
		Recipe recipe = new Recipe();
		recipe.setId(sql.getLong("id"));
		recipe.setName(sql.getString("name"));
		recipe.setIngredients(sql.getString("ingredients"));
		recipe.setVolume(sql.getInt("volume"));
		recipe.setAbv(sql.getDouble("abv"));
		recipe.setDirections(sql.getString("directions"));
		recipe.setAuthor(sql.getString("author"));
		recipe.setUnits(sql.getString("units"));
		return recipe;
	}

}
