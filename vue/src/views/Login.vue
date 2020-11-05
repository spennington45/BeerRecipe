<template>
  <div
    id="login"
    class="text-center col-xl-5 mx-auto border border-dark rounded"
  >
    <section class="page-section cta">
      <div class="container">
        <div class="row">
          <div class="col-xl-11 mx-auto">
            <div class="cta-inner rounded">
              <form class="form-signin" @submit.prevent="login">
                <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
                <div
                  class="alert alert-danger"
                  role="alert"
                  v-if="invalidCredentials"
                >
                  Invalid username and password!
                </div>
                <div
                  class="alert alert-success"
                  role="alert"
                  v-if="this.$route.query.registration"
                >
                  Thank you for registering, please sign in.
                </div>
                <label for="username" class="sr-only">Username</label>
                <input
                  type="text"
                  id="username"
                  class="form-control"
                  placeholder="Username"
                  v-model="user.username"
                  required
                  autofocus
                />
                <label for="password" class="sr-only">Password</label>
                <input
                  type="password"
                  id="password"
                  class="form-control"
                  placeholder="Password"
                  v-model="user.password"
                  required
                />
                <br />
                <router-link :to="{ name: 'register' }" class="nav-link"
                  >Need an account?</router-link
                >
                <button type="submit" class="btn btn-lg btn-primary btn-block">
                  Sign in
                </button>
                <br />
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import authService from "../services/AuthService";
export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    // Lets users log in and mutates data in the Store
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style>
</style>