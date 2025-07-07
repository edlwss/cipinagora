import axios from 'axios';

const api = axios.create({
    baseURL: '/cipinagora/api',
    withCredentials: true,
    headers: {
        'Content-Type': 'application/json',
    },
});

export default api;