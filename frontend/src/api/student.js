import api from './axiosInstance';


export const getAllStudents = () => api.get('/students');
export const getStudentById = (id) =>
    api.get(`/student/${id}`);
export const createStudent = (data) =>
    api.post('/students/registration', data);
export const updateStudent = (id, data) =>
    api.patch(`/student/${id}/edit`, data);
export const deleteStudent = (id) =>
    api.delete(`/student/${id}/delete`);
