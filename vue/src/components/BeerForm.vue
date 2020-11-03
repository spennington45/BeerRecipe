<template>
  <div>
    <form>
      <div class="flex-grow-1">
        <label for="name">Name</label>
        <input
          type="text"
          class="form-control"
          id="name"
          placeholder="Enter recipe name"
          v-model="recipe.name"
        />
      </div>
      <div>
        <label for="Ingredients">Ingredients</label>
        <textarea
          class="form-control"
          id="ingredients"
          rows="5"
          placeholder="Ingredients"
          v-model="recipe.ingredients"
        ></textarea>
      </div>
      <div class="d-flex-inline">
        <label for="Volume">Volume</label>
        <input
          type="text"
          class="form-control"
          id="Volume"
          placeholder="Enter volume"
          v-model="recipe.volume"
        />
        <label for="Units">Units</label>
        <select class="form-control" id="Units" v-model="recipe.units">
          <option>L</option>
          <option>Gal</option>
        </select>
      </div>
      <div>
        <label for="ABV">ABV/SG</label>
        <input
          type="text"
          class="form-control"
          id="ABV"
          placeholder="ABV/SG"
          v-model="recipe.abv"
        />
      </div>
      <div>
        <label for="Directions">Directions</label>
        <textarea
          class="form-control"
          id="Directions"
          rows="10"
          placeholder="Directions"
          v-model="recipe.directions"
        ></textarea>
      </div>
      <div>
        <label for="Author">Author</label>
        <input
          type="text"
          class="form-control"
          id="Author"
          placeholder="Author"
          v-model="recipe.author"
        />
      </div>
      <div>
        <br />
        <button
          v-if="this.$route.params.id == null"
          id="addBeer"
          type="submit"
          class="btn btn-primary btn-sm"
          v-on:click.prevent="addRecipe"
        >
          Add Beer
        </button>
        <button
          v-else
          id="updateBeer"
          type="submin"
          class="btn btn-primary btn-sm"
          v-on:click.prevent="updateRecipe"
        >
          Update Beer
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import recipeService from "../services/RecipeService.js";

export default {
  data() {
    return {
      recipe: {userId: 1},
    };
  },
  methods: {
    addRecipe() {
      recipeService.addRecipe(this.recipe).then((response) => {
        if (response.status == 200) {
          console.log(this.recipe.name + " added");
          this.$router.push({name: "findRecipe"})
        }
      });
    },
    updateRecipe() {
      recipeService.updateRecipe(this.recipe).then((response) => {
        if (response.status == 200) {
          console.log(this.recipe.name + " updated");
          this.$router.push({name: "recipeDetails", params: {id: this.$route.params.id}})
        }
      });
    },
  },
  created() {
    if (this.$route.name == "updateRecipe") {
      recipeService.getRecipeById(this.$route.params.id).then((response) => {
        this.recipe = response.data;
        console.log(this.recipe)
      });
    }
  },
};
</script>

<style>
</style>