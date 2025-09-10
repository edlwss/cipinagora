import { createRouter, createWebHistory } from 'vue-router';
import LoginPage from "@/pages/LoginPage.vue";
import RegistrationForm from "@/pages/RegistrationForm.vue";
import { authrole } from '@/api/auth/authrole.js'
import ProfilePage from "@/pages/ProfilePage.vue";
import NotFoundPage from "@/pages/error/NotFoundPage.vue";

const routes = [
    {
        path: '/cipinagora/profile/:id',
        component: ProfilePage,
        name: 'profile',
        meta: {
            roles: ['ROLE_STUDENT', 'ROLE_INSTRUCTOR', 'ROLE_SPORTSCENTER']
        }
    },
    {
        path: '/register',
        component: RegistrationForm,
        name: 'register',
        meta: {
            title: 'Регистрация',
            public: true
        }
    },
    {
        path: '/login',
        component: LoginPage,
        meta: {
            public: true
        }
    },
    {
        path: '/:pathMatch(.*)*',
        name: 'not-found',
        component: NotFoundPage,
        meta: { public: true }
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

router.beforeEach(authrole)
export default router;
