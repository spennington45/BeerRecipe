<template>
  <div>
    <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark py-lg-4 navbar-fixed-top" id="mainNav">
    <div class="container">
      <a class="navbar-brand text-uppercase text-expanded font-weight-bold d-lg-none" href="#">Beer Recipes</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
        aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav mx-auto">
          <li class="nav-item active px-lg-4">
            <router-link class="nav-link text-uppercase text-expanded" v-bind:to="{name: 'home'}">Home
              <span class="sr-only">(current)</span>
            </router-link>
          </li>
          <li class="nav-item px-lg-4">
            <router-link class="nav-link text-uppercase text-expanded" v-bind:to="{name: 'findRecipe'}">Find a Recipes</router-link>
          </li>
          <li class="nav-item px-lg-4">
            <router-link class="nav-link text-uppercase text-expanded" v-bind:to="{name: 'addRecipe'}">Add a Recipe</router-link>
          </li>
          <li class="nav-item px-lg-4">
            <router-link class="nav-link text-uppercase text-expanded" v-bind:to="{name: 'forums'}">Forums</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <router-view>
      <footer class="footer text-faded text-center py-5">
    <div class="container">
      <p class="m-0 small">Copyright &copy; Website TBD 2020</p>
    </div>
  </footer>
  </div>
</template>

<script>
export default {};
</script>

<style>
</style>
