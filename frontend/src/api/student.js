import api from './axiosInstance';


export const getAllStudents = () => api.get('/students');
export const getStudentById = (id) =>
    api.get(`/students/student/${id}`);
export const createStudent = (data) =>
    api.post('/students', data);
export const updateStudent = (id, data) =>
    api.patch(`/students/student/${id}`, data);
export const deleteStudent = (id) =>
    api.delete(`/students/student/${id}`);
