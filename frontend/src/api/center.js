import api from './axiosInstance';

export const getAllCenters = () => api.get('/centers');
export const getCenterById = (id) =>
    api.get(`/center/${id}`);
export const createCenter = (data) =>
    api.post('/centers/registration', data);
export const updateCenter = (id, data) =>
    api.patch(`/center/${id}/edit`, data);
export const deleteCenter = (id) =>
    api.delete(`/center/${id}/delete`);