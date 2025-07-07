import { createRouter, createWebHistory } from 'vue-router';
import StudentPage from "@/pages/StudentPage.vue";

const routes = [
    {
        path: '/cipinagora/student/profile/:id',
        component: StudentPage,
        name: 'student-profile',
        meta: {
            title: 'Профиль студента',
            component: 'StudentProfile'
        }
    },
    {
        path: '/cipinagora/student/profile/:id/edit',
        component: StudentPage,
        name: 'student-edit',
        meta: {
            title: 'Редактировать студента',
            component: 'StudentEdit'
        }
    }

];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
