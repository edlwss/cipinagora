import api from '../axiosInstance.js';

export const updateUser = (id, data) =>
    api.patch(`/user/${id}/edit`, data);
export const getUser = (id) =>
    api.get(`/user/${id}`)
export const changePassword = (id, data) =>
    api.patch(`user/${id}/edit/password`, data)
