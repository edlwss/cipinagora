import api from '../axiosInstance.js';


export const getAllInstructors = () => api.get('/instructors');
export const getInstructorById = (id) =>
    api.get(`/instructor/${id}`);
export const createInstructor = (data) =>
    api.post('/instructors/registration', data);
export const updatePersonalInstructor = (id, data) =>
    api.patch(`/instructor/${id}/edit/personal`, data);
export const updateAddSportsInstructor = (id, data) =>
    api.patch(`/instructor/${id}/edit/addsports`, data);
export const updatePhotoInstructor = (id, data) =>
    api.patch(`instructor/${id}/edit/photo`, data);
export const deleteInstructor = (id) =>
    api.delete(`/instructor/${id}/delete`);