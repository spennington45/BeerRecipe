package com.recipe.beerrecipe.jdbcdao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.recipe.beerrecipe.model.Post;

@Component
public class JDBCPostDAO implements PostDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Post> getAllPost() {
		List<Post> output = new ArrayList<Post>();
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT post.*, users.username FROM post " + 
				"JOIN users ON post.user_id = users.id ORDER BY postdate DESC");
		while (results.next()) {
			output.add(mapRowToPost(results));
		}
		return output;
	}

	@Override
	public Post getPostById(long id) {
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT post.*, users.username FROM post " + 
				"JOIN users ON post.user_id = users.id WHERE post.id = ?", id);
		if (results.next()) {
			return mapRowToPost(results);			
		} else {
			return null;
		}
	}

	@Override
	public void updatePost(Post post) {
		jdbcTemplate.update("UPDATE post SET title = ?, message = ?, imgurl = ?, postdate = ? WHERE id = ?", 
				post.getTitle(), post.getMessage(), post.getImageUrl(), post.getDate(), post.getId());
	}

	@Override
	public void addPost(Post post) {
		jdbcTemplate.update("INSERT INTO post (title, message, imgurl, postdate, user_id) VALUES (?, ?, ?, ?, ?)",
				post.getTitle(), post.getMessage(), post.getImageUrl(), post.getDate(), post.getUserId());
	}

	@Override
	public void deletePost(long id) {
		jdbcTemplate.update("DELETE FROM reply_post WHERE post_id = ?", id);
		jdbcTemplate.update("DELETE FROM post WHERE id = ?", id);
	}

	@Override
	public void sticky(long id) {
		jdbcTemplate.update("UPDATE post SET stickied = true WHERE id = ?", id);
	}

	@Override
	public void unsticky(long id) {
		jdbcTemplate.update("UPDATE post SET stickied = false WHERE id = ?", id);
	}

	public Post mapRowToPost(SqlRowSet results) {
		Post post = new Post();
		post.setId(results.getLong("id"));
		post.setTitle(results.getString("title"));
		post.setMessage(results.getString("message"));
		post.setImageUrl(results.getString("imgUrl"));
		post.setDate(results.getDate("postDate"));
		post.setUserId(results.getInt("user_id"));
		post.setStickied(results.getBoolean("stickied"));
		post.setUsername(results.getString("username"));
		return post;
	}
	
}
