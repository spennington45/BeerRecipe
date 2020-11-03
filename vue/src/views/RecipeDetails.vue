<template>
  <div>
    <section class="page-section cta">
      <div class="container">
        <div class="row">
          <div class="col-xl-12 mx-auto">
            <div class="cta-inner rounded">
              <div class="flex-grow-1">
                <h3 class="section-heading mb-4">{{ this.recipe.name }}</h3>
                <h5>Author: {{this.recipe.author}}</h5>
                <p>Expected ABV: {{this.recipe.abv}}</p>
                <p>Volume: {{this.recipe.volume}}</p>
                <p>Ingredients:
                    {{this.recipe.ingredients}}
                </p>
                <p>Directions:
                    {{this.recipe.directions}}
                </p>
                <button v-on:click="updateRecipe" class="btn btn-primary btn-sm">Update Recipe</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import recipeServices from "../services/RecipeService.js";
export default {
  data() {
    return {
      recipe: {},
    };
  },
  methods: {
    updateRecipe() {
      this.$router.push({name: "updateRecipe", params: { id: this.$route.params.id }});
    }
  },
  created() {
    recipeServices.getRecipeById(this.$route.params.id).then((response) => {
      this.recipe = response.data;
    });
  },
};
</script>

<style>
</style>