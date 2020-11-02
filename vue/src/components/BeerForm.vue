<template>
  <div>
    <form>
      <div class="flex-grow-1">
        <label for="name">Title</label>
        <input
          type="text"
          class="form-control"
          id="name"
          placeholder="Enter recipe name"
          v-bind="recipe.name"
        />
      </div>
      <div>
        <label for="Ingredents">Ingredents</label>
        <textarea
          class="form-control"
          id="ingredents"
          rows="5"
          placeholder="Ingredents"
          v-bind="recipe.ingredents"
        ></textarea>
      </div>
      <div class="d-flex-inline">
        <label for="Volume">Volume</label>
        <input
          type="text"
          class="form-control"
          id="Volume"
          placeholder="Enter volume"
          v-bind="recipe.volume"
        />
        <label for="Units">Units</label>
        <select class="form-control" id="Units" v-bind="recipe.units">
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
          v-bind="recipe.abv"
        />
      </div>
      <div>
        <label for="Directions">Directions</label>
        <textarea
          class="form-control"
          id="Directions"
          rows="10"
          placeholder="Directions"
          v-bind="recipe.directions"
        ></textarea>
      </div>
      <div>
        <label for="Author">Author</label>
        <input
          type="text"
          class="form-control"
          id="Author"
          placeholder="Author"
          v-bind="recipe.author"
        />
      </div>
      <div>
        <br />
        <button
          v-if="this.$route.params.id == null"
          id="addBeer"
          type="submit"
          class="btn btn-primary btn-sm"
          v-on="addRecipe"
        >
          Add Beer
        </button>
        <button
          v-else
          id="updateBeer"
          type="submin"
          class="btn btn-primary btn-sm"
          v-on="updateRecipe"
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
      recipe: {},
    };
  },
  methods: {
    addRecipe() {
      recipeService.addRecipe(this.recipe).then((response) => {
        if (response.status == 200) {
        //   console.log(this.recipe.name + " added");
        }
      });
    },
    updateRecipe() {
      recipeService.updateRecipe(this.recipe).then((response) => {
        if (response.status == 200) {
        //   console.log(this.recipe.name + " updated");
        }
      });
    },
  },
  created() {
    if (this.$route.params.id != null) {
      recipeService.getRecipeById(this.$route.params.id).then((response) => {
        this.recipe = response.data;
      });
    }
  },
};
</script>

<style>
</style>