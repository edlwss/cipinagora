import { createRouter, createWebHistory } from 'vue-router';
import StudentPage from "@/pages/StudentPage.vue";
import LoginPage from "@/pages/LoginPage.vue";
import { authrole } from '@/api/authrole.js'

const routes = [
    {
        path: '/',
        redirect: '/login'
    },
    {
        path: '/cipinagora/profile/:id',
        component: StudentPage,
        name: 'student-profile',
        meta: {
            title: 'Профиль студента',
            component: 'StudentProfile',
            roles: ['ROLE_STUDENT']
        }
    },
    {
        path: '/login',
        component: LoginPage
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

router.beforeEach(authrole)
export default router;
