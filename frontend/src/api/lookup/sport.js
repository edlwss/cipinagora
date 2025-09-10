import api from '../axiosInstance.js'

export const getSports = () =>
    api.get('/lookup/sports');