<template>
  <div>
    <section class="page-section cta">
      <div class="container">
        <div class="row">
          <div class="col-xl-12 mx-auto">
            <div class="cta-inner rounded">
              <form>
                <div class="flex-grow-1">
                  <h3>
                    <label for="name">Filter By Name</label>
                  </h3>
                  <input
                    type="text"
                    class="form-control"
                    id="search"
                    placeholder="Enter recipe name"
                  />
                  <br /><button
                    id="searchBtn"
                    type="search"
                    class="btn btn-primary btn-sm"
                  >
                    Filter By Name
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-xl-12 mx-auto">
            <div class="cta-inner rounded">
              <div class="flex-grow-1">
                <h3 class="section-heading mb-4">Beer Recipes</h3>
              </div>
              <div v-for="recipe in recipes" v-bind:key="recipe.id">
                <router-link
                  class="nav-link"
                  v-bind:to="{
                    name: 'recipeDetails',
                    params: { id: recipe.id },
                  }"
                >
                  {{ recipe.name }}
                </router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import recipeService from "../services/RecipeService.js";

export default {
  data() {
    return {
      recipes: [],
    };
  },
  methods: {},
  created() {
    recipeService.getAllRecipe().then((response) => {
      this.recipes = response.data;
    });
  },
};
</script>

<style scoped>
.nav-link {
  color: rgba(17, 9, 3, 1);
}
.nav-link:hover {
  color: #e6a756;
}
</style>