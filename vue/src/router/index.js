import Vue from 'vue'
import Router from 'vue-router'
import store from '../store/index.js'
import Home from "../views/Home.vue"
import AddRecipe from "../views/AddRecipe.vue"
import FindRecipe from "../views/FindRecipe.vue"
import Forums from "../views/Forums.vue"
import RecipeDetails from "../views/RecipeDetails.vue"
import UpdateRecipe from "../views/UpdateRecipe.vue"
import Login from "../views/Login.vue"
import Logout from "../views/Logout.vue"
import Register from "../views/Register.vue"
import PostDetails from "../components/PostDetails.vue"
import PostForm from "../components/PostForm.vue"


Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home,
            meta: {
                requiresAuth: false
            }
        },
        {
            path: "/login",
            name: "login",
            component: Login,
            meta: {
                requiresAuth: false
            }
        },
        {
            path: "/logout",
            name: "logout",
            component: Logout,
            meta: {
                requiresAuth: false
            }
        },
        {
            path: "/register",
            name: "register",
            component: Register,
            meta: {
                requiresAuth: false
            }
        },
        {
            path: "/findRecipe",
            name: "findRecipe",
            component: FindRecipe,
            meta: {
                requiresAuth: false
            }
        },
        {
            path: "/addRecipe",
            name: "addRecipe",
            component: AddRecipe,
            meta: {
                requiresAuth: false
            }
        },
        {
            path: "/forums",
            name: "forums",
            component: Forums,
            meta: {
                requiresAuth: false
            }
        },
        {
            path: "/recipe/:id",
            name: "recipeDetails",
            component: RecipeDetails,
            meta: {
                requiresAuth: false
            }
        },
        {
            path: "/updateRecipe/:id",
            name: "updateRecipe",
            component: UpdateRecipe,
            meta: {
                requiresAuth: false
            }
        },
        {
            path: "/post/:id",
            name: "postDetails",
            component: PostDetails,
            meta: {
                requiresAuth: false
            }
        },
        {
            path: "/postForm",
            name: "postForm",
            component: PostForm,
            meta: {
                requiresAuth: true
            }
        },
        {
            path: "/postForm/:id",
            name: "updatePost",
            component: PostForm,
            meta: {
                requiresAuth: true
            }
        }
    ]
})

router.beforeEach((to, from, next) => {
    // Determine if the route requires Authentication
    const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

    // If it does and they are not logged in, send the user to "/login"
    if (requiresAuth && store.state.token === '') {
        next("/login");
    } else {
        // Else let them go to their next destination
        next();
    }
});

export default router;