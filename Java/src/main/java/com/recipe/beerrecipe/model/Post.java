package com.recipe.beerrecipe.model;

import java.util.Date;

public class Post {

	private long id;
	private String title;
	private String message;
	private String imageUrl;
	private Date date;
	private int userId;
	private boolean stickied;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public boolean isStickied() {
		return stickied;
	}
	public void setStickied(boolean stickied) {
		this.stickied = stickied;
	}
	
	
}
