import api from '../axiosInstance.js';

export const getAllAgeCategories = () => api.get('/lookup/age');
