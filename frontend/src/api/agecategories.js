import api from './axiosInstance';

export const getAllAgeCategories = () => api.get('/lookup/age');
