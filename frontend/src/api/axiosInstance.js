import axios from 'axios';

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

export default api;