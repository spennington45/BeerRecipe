package com.recipe.beerrecipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.beerrecipe.jdbcdao.JDBCRecipeDAO;

@CrossOrigin
@RestController 
public class ForumController {

	@Autowired
	JDBCRecipeDAO dao;
	
}
