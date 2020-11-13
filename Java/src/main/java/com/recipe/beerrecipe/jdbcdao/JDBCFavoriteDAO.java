package com.recipe.beerrecipe.jdbcdao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.recipe.beerrecipe.model.Favorites;

@Component
public class JDBCFavoriteDAO implements FavoriteDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void favorite(int userId, int recipeId) {
		jdbcTemplate.update("INSERT INTO favorites (user_id, recipe_id) VALUES (?, ?)", userId, recipeId);
	}

	@Override
	public void unfavorite(int userId, int recipeId) {
		jdbcTemplate.update("DELETE FROM favorites WHERE user_id = ? and recipe_id = ?", userId, recipeId);
	}

	@Override
	public List<Favorites> getFavoriteByUserId(int userId) {
		List<Favorites> output = new ArrayList<Favorites>();
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM favorites WHERE user_id = ?", userId);
		while (results.next()) {
			output.add(mapRowToFacorites(results));
		}
		return output;
	}
	
	public Favorites mapRowToFacorites(SqlRowSet results) {
		Favorites fav = new Favorites();
		fav.setUserId(results.getInt("user_id"));
		fav.setRecipeId(results.getInt("recipe_id"));
		return fav;
	}

}
