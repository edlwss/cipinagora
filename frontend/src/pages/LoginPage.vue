<template>
  <form @submit.prevent="handleLogin">
    <input v-model="login" placeholder="Логин" />
    <input v-model="password" type="password" placeholder="Пароль" />
    <button>Войти</button>
    <p v-if="error">{{ error }}</p>
  </form>
</template>

<script setup>
import { ref } from 'vue'
import { useAuth } from '@/api/auth'
import { useRouter } from 'vue-router'

const login = ref('')
const password = ref('')
const error = ref('')
const { login: loginFunc } = useAuth()
const router = useRouter()

async function handleLogin() {
  try {
    await loginFunc({ login: login.value, password: password.value })
    const {user} = useAuth()
    console.log(user)
    await router.push(`/cipinagora/profile/${user.value.userId}`)
  } catch {
    error.value = 'Неверные данные'
  }
}
</script>
