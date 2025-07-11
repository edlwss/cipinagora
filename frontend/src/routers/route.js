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

];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
