import { ref } from 'vue'
import api from '../axiosInstance.js'

const token = ref(sessionStorage.getItem('token') || '')
const user = ref(parseToken(token.value))

if (token.value) {
    api.defaults.headers.common['Authorization'] = `Bearer ${token.value}`
}

function parseToken(token) {
    if (!token) return null
    try {
        const payload = JSON.parse(atob(token.split('.')[1]))
        return {
            login: payload.sub,
            userId: payload.userId,
            roles: payload.roles
        }
    } catch (e) {
        console.warn('Ошибка парсинга токена', e)
        return null
    }
}

function login({ login, password }) {
    return api.post('/auth', { login, password })
        .then(res => {
            token.value = res.data.token
            sessionStorage.setItem('token', token.value)
            api.defaults.headers.common['Authorization'] = `Bearer ${token.value}`
            user.value = parseToken(token.value)
        })
}

function logout() {
    token.value = ''
    user.value = null
    sessionStorage.removeItem('token')
    delete api.defaults.headers.common['Authorization']
}

export function getCurrentUser() {
    return api.get('/auth/me')
}

export function useAuth() {
    return { token, user, login, logout }
}
