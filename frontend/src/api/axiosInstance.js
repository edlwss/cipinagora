import axios from 'axios';
import router from "@/routers/route.js";

const api = axios.create({
    baseURL: '/cipinagora/api',
    withCredentials: false,
    headers: {
        'Content-Type': 'application/json',
    },
})

const token = sessionStorage.getItem('token')
if (token){
    api.defaults.headers.common["Authorization"] = `Bearer ${token}`
}

api.interceptors.response.use(
    response => response,
    error => {
        if (error.response && [401, 403].includes(error.response.status)) {
            sessionStorage.removeItem('token')
            delete api.defaults.headers.common['Authorization']
            router.push('/login')
        }
        return Promise.reject(error)
    }
)

export default api;