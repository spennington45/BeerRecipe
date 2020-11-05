<template>
  <div
    id="register"
    class="text-center col-xl-5 mx-auto border border-dark rounded"
  >
    <section class="page-section cta">
      <div class="container">
        <div class="row">
          <div class="col-xl-11 mx-auto">
            <div class="cta-inner rounded">
              <form class="form-register" @submit.prevent="register">
                <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
                <div
                  class="alert alert-danger"
                  role="alert"
                  v-if="registrationErrors"
                >
                  {{ registrationErrorMsg }}
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
                <input
                  type="password"
                  id="confirmPassword"
                  class="form-control"
                  placeholder="Confirm Password"
                  v-model="user.confirmPassword"
                  required
                />
                <input
                  type="text"
                  id="firstName"
                  class="form-control"
                  placeholder="First Name"
                  v-model="user.firstName"
                  required
                />
                <input
                  type="text"
                  id="lastName"
                  class="form-control"
                  placeholder="Last Name"
                  v-model="user.lastName"
                  required
                />
                <br />
                <router-link :to="{ name: 'login' }" class="nav-link"
                  >Have an account?</router-link
                >
                <button class="btn btn-lg btn-primary btn-block" type="submit">
                  Create Account
                </button>
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
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        firstName: "",
        lastName: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    // Allows users to register for an account
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style>
</style>
