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
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT reply_post.*, users.username FROM reply_post " + 
				"JOIN users ON reply_post.user_id = users.id WHERE post_id = ?", id);
		while (results.next()) {
			output.add(mapRowToReply(results));
		}
		return output;
	}

	@Override
	public void updatePostReply(PostReply reply) {
		jdbcTemplate.update("UPDATE reply_post SET title = ?, reply = ? WHERE id = ? ORDER BY replyDate",
				reply.getTitle(), reply.getReply(), reply.getId());
	}

	@Override
	public void addPostReply(PostReply reply) {
		jdbcTemplate.update("INSERT INTO reply_post (user_id, post_id, title, reply, replyDate) VALUES (?, ?, ?, ?, ?)", 
				reply.getUserId(), reply.getPostId(), reply.getTitle(), reply.getReply(), reply.getDate());
	}

	@Override
	public void deletePostReply(long id) {
		jdbcTemplate.update("DELETE FROM reply_post WHERE id = ?", id);
	}

	@Override
	public PostReply getReplyById(long id) {
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT reply_post.*, users.username FROM reply_post " + 
				"JOIN users ON reply_post.user_id = users.id WHERE reply_post.id = ?", id);
		if (results.next()) {
			return (mapRowToReply(results));
		} else {
			return null;			
		}
	}
	
	public PostReply mapRowToReply(SqlRowSet results) {
		PostReply reply = new PostReply();
		reply.setId(results.getLong("id"));
		reply.setUserId(results.getInt("user_id"));
		reply.setPostId(results.getInt("post_id"));
		reply.setTitle(results.getString("title"));
		reply.setReply(results.getString("reply"));
		reply.setUsername(results.getString("username"));
		reply.setDate(results.getDate("replyDate"));
		return reply;
	}

}
