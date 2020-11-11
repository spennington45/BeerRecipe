import axios from 'axios';

const http = axios.create({ baseURL: 'http://localhost:8080' })

export default {
    getAllPost() {
        return http.get("/post")
    },
    sticky(id) {
        return http.post(`/sticky/${id}`)
    },
    unsticky(id) {
        return http.delete(`/sticky/${id}`)
    },
    addPost(post) {
        return http.post("/post", post)
    },
    getPostById(id) {
        return http.get(`/post/${id}`)
    },
    updatePost(post) {
        return http.put("/post", post)
    },
    deletePost(id) {
        return http.delete(`/post/${id}`)
    }
}