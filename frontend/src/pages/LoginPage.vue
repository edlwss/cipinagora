<template>
  <div class="login-container">
    <h1 class="auth-title">Авторизация</h1>
    <div class="login-box">
      <router-link to="/register" class="register-link">регистрация</router-link>
      <form @submit.prevent="handleLogin" class="login-form">
        <input v-model="login" placeholder="Логин" />
        <input v-model="password" type="password" placeholder="Пароль" />
        <button type="submit">Войти</button>
        <p v-if="error" class="error">{{ error }}</p>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuth } from '@/api/auth'
import "@/assets/auth/login.css"

const login = ref('')
const password = ref('')
const error = ref('')
const router = useRouter()
const { login: loginFunc } = useAuth()

async function handleLogin() {
  try {
    await loginFunc({ login: login.value, password: password.value })
    const { user } = useAuth()
    await router.push(`/cipinagora/profile/${user.value.userId}`)
  } catch {
    error.value = 'Неверные данные'
  }
}
</script>


