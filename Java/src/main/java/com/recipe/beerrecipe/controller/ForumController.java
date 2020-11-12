package com.recipe.beerrecipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.beerrecipe.jdbcdao.JDBCPostDAO;
import com.recipe.beerrecipe.jdbcdao.JDBCPostReplyDAO;
import com.recipe.beerrecipe.model.Post;
import com.recipe.beerrecipe.model.PostReply;

@CrossOrigin
@RestController 
public class ForumController {

	@Autowired
	JDBCPostDAO postDao;
	@Autowired
	JDBCPostReplyDAO replyDao;
	
	@RequestMapping(path = "/post", method = RequestMethod.GET)
	public List<Post> getAllPost() {
		return postDao.getAllPost();
	}
	
	@RequestMapping(path = "/post/{id}", method = RequestMethod.GET)
	public Post getPostById(@PathVariable long id) {
		return postDao.getPostById(id);
	}
	
	@RequestMapping(path = "/post", method = RequestMethod.PUT)
	public void updatePost(@RequestBody Post post) {
		postDao.updatePost(post);
	}
	
	@RequestMapping(path = "/post", method = RequestMethod.POST)
	public void savePost(@RequestBody Post post) {
		postDao.addPost(post);
	}
	
	@RequestMapping(path = "/post/{id}", method = RequestMethod.DELETE)
	public void deletepost(@PathVariable long id) {
		postDao.deletePost(id);
	}
	
	@RequestMapping(path = "/sticky/{id}", method = RequestMethod.POST)
	public void sticky(@PathVariable long id) {
		postDao.sticky(id);
	}
	
	@RequestMapping(path = "/sticky/{id}", method = RequestMethod.DELETE)
	public void unsticky(@PathVariable long id) {
		postDao.unsticky(id);
	}
	
	@RequestMapping(path = "/reply/post/{id}", method = RequestMethod.GET)
	public List<PostReply> getReplyByPostId(@PathVariable long id) {
		return replyDao.getReplysByPostId(id);
	}
	
	@RequestMapping(path = "/reply/{id}", method = RequestMethod.GET)
	public PostReply getReplyById(@PathVariable long id) {
		return replyDao.getReplyById(id);
	}
	
	@RequestMapping(path = "/reply", method = RequestMethod.POST)
	public void addReply(@RequestBody PostReply reply) {
		replyDao.addPostReply(reply);
	}
	
	@RequestMapping(path = "/reply", method = RequestMethod.PUT)
	public void updateReply(@RequestBody PostReply reply) {
		replyDao.updatePostReply(reply);
	}
	
	@RequestMapping(path = "reply/{id}", method = RequestMethod.DELETE)
	public void deleteReply(@PathVariable long id) {
		replyDao.deletePostReply(id);
	}
	
}
