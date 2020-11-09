package com.recipe.beerrecipe.jdbcdao;

import java.util.List;

import com.recipe.beerrecipe.model.Post;

public interface PostDAO {

	public List <Post> getAllPost();
	
	public Post getPostById(long id);
	
	public void updatePost(Post post);
	
	public void addPost(Post post);
	
	public void deletePost(long id);
	
	public void sticky(long id);
	
	public void unsticky(long id);
	
}
