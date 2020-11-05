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
                    v-model="filterText"
                  />
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
              <div v-for="recipe in filteredList" v-bind:key="recipe.id">
                <router-link
                  class="nav-link"
                  v-bind:to="{
                    name: 'recipeDetails',
                    params: { id: recipe.id },
                  }"
                >
                  {{ recipe.name }}
                <span class="gap-left">- {{recipe.author}}</span>
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
      filterText: "",
    };
  },
  computed: {
    filteredList() {
      return this.recipes.filter((recipe) => {
        return recipe.name.toLowerCase().includes(this.filterText.toLowerCase());
      });
    }
  },
  methods: {
  },
  created() {
    recipeService.getAllRecipe().then((response) => {
      this.recipes = response.data;
    });
  },
};
</script>

<style scoped>
.gap-left {
  margin-left: 10px;
}
</style>