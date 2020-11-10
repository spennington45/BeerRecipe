package com.recipe.beerrecipe.jdbcdao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.recipe.beerrecipe.model.PostReply;

@Component
public class JDBCPostReplyDAO implements PostReplyDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<PostReply> getReplysByPostId(long id) {
		List<PostReply> output = new ArrayList<PostReply>();
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM reply_post WHERE post_id = ?", id);
		while (results.next()) {
			output.add(mapRowToReply(results));
		}
		return output;
	}

	@Override
	public void updatePostReply(PostReply reply) {
		jdbcTemplate.update("UPDATE reply_post SET title = ?, reply = ? WHERE id = ?",
				reply.getTitle(), reply.getReply(), reply.getId());
	}

	@Override
	public void addPostReply(PostReply reply) {
		jdbcTemplate.update("INSERT INTO reply_post (user_id, post_id, title, reply) VALUES (?, ?, ?, ?)", 
				reply.getUserId(), reply.getPostId(), reply.getTitle(), reply.getReply());
	}

	@Override
	public void deletePostReply(long id) {
		jdbcTemplate.update("DELETE FROM reply_post WHERE id = ?", id);
	}

	public PostReply mapRowToReply(SqlRowSet results) {
		PostReply reply = new PostReply();
		reply.setId(results.getLong("id"));
		reply.setUserId(results.getInt("user_id"));
		reply.setPostId(results.getInt("post_id"));
		reply.setTitle(results.getString("title"));
		reply.setReply(results.getString("reply"));
		return reply;
	}
}
