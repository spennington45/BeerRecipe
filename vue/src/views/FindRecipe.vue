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
                <div id="stars">
                <img
                  v-on:click="markNotFacorite(recipe.id)"
                  v-if="favoriteRecipe(recipe.id)"
                  src="../resources/favorite-icon-.jpg"
                  alt=""
                />
                <img
                  v-on:click="markFavorite(recipe.id)"
                  v-else
                  src="../resources/notFavorite.png"
                  alt=""
                />
                </div>
                <router-link
                  class="nav-link"
                  v-bind:to="{
                    name: 'recipeDetails',
                    params: { id: recipe.id },
                  }"
                >
                  {{ recipe.name }}
                  <span class="gap-left">- {{ recipe.author }}</span>
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
      favorite: [],
      newFavorite: {},
      index: 0,
      src: ["../resources/favorite-icon-.jpg", 
      "../resources/notFavorite.png"],
    };
  },
  watch: {

  },
  computed: {
    filteredList() {
      return this.recipes.filter((recipe) => {
        return recipe.name.toLowerCase().includes(this.filterText.toLowerCase());
      });
    },
    toggleImg() {
      return this.favoriteRecipe(this.recipe.id)
    }
  },
  methods: {
    markFavorite(recipeId) {
      this.newFavorite.recipeId = recipeId
      this.newFavorite.userId = this.$store.state.user.id
      recipeService.favorite(this.newFavorite).then(() => {this.reload()})
    },
    markNotFacorite(recipeId) {
      this.newFavorite.recipeId = recipeId
      this.newFavorite.userId = this.$store.state.user.id
      recipeService.unfavorite(this.newFavorite).then(() => {this.reload()})
    },
    favoriteRecipe(recipeId) {
      for (let i = 0; i < this.favorite.length; i++) {
        if (this.favorite[i].recipeId == recipeId && this.favorite[i].userId == this.$store.state.user.id) {
          return true;
        }
      }
        return false;
    },
     reload() {
      this.render = false;
      this.$nextTick(() => {
        this.render = true;
      });
    }
  },
  created() {
    recipeService.getAllRecipe().then((response) => {
      this.recipes = response.data;
      recipeService.getFavoriteByUserId(this.$store.state.user.id).then((response) => {
        this.favorite = response.data;
      })
    });
  },
};
</script>

<style scoped>
.gap-left {
  margin-left: 10px;
}
#stars {
  float: left;
  padding-right: 5px;
}
img {
  max-height: 15px;
  widows: auto;
}
img:hover {
  cursor: pointer;
}
</style>