package com.recipe.beerrecipe.jdbcdao;

import java.util.List;

import com.recipe.beerrecipe.model.PostReply;

public interface PostReplyDAO {

	public List <PostReply> getReplysByPostId(long id);
	
	public void updatePostReply(PostReply reply);
	
	public void addPostReply(PostReply reply);
	
	public void deletePostReply(long id);
	
	public PostReply getReplyById(long id);
	
}
