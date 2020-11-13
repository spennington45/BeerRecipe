import axios from 'axios';

const http = axios.create({ baseURL: 'http://localhost:8080' })

export default {

    getAllRecipe() {
        return http.get('/recipe');
    },
    getRecipeById(id) {
        return http.get(`/recipe/${id}`)
    },
    addRecipe(recipe) {
        return http.post("/save", recipe)
    },
    updateRecipe(recipe) {
        return http.put(`/update/${recipe.id}`, recipe)
    },
    deleteRecipe(id) {
        return http.delete(`/delete/${id}`)
    },
    getFavoriteByUserId(id) {
        return http.get(`/favorite/${id}`)
    },
    favorite(favorite) {
        return http.post("/favorite", favorite)
    },
    unfavorite(favorite) {
        return http.put("/unfavorite", favorite)
    }
}