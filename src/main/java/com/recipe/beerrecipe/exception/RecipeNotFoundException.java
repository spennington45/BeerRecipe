package com.recipe.beerrecipe.exception;

public class RecipeNotFoundException extends RuntimeException{

	public RecipeNotFoundException() {
		super("Recipe not found");
	}
	
}
