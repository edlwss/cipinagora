import api from './axiosInstance';


export const getAllInstructors = () => api.get('/instructors');
export const getInstructorById = (id) =>
    api.get(`/instructor/${id}`);
export const createInstructor = (data) =>
    api.post('/instructors/registration', data);
export const updateInstructor = (id, data) =>
    api.patch(`/instructor/${id}/edit`, data);
export const deleteInstructor = (id) =>
    api.delete(`/instructor/${id}/delete`);