import api from './axiosInstance';

export const updateUser = (id, data) =>
    api.patch(`cipinagora/api/user/${id}`, data);