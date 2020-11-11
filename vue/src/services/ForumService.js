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
}