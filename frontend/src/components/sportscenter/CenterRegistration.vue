<template>
  <form class="form" @submit.prevent="submitForm">
    <label>Название <input v-model="form.name" required /></label>
    <label>Описание <textarea v-model="form.description" /></label>
    <label>Координаты <input v-model="form.coordinates" /></label>

    <label>Регион <input v-model="form.addressRegion" /></label>
    <label>Город <input v-model="form.addressCity" /></label>
    <label>Улица <input v-model="form.addressStreet" /></label>
    <label>Дом <input v-model="form.addressBuilding" /></label>

    <label>Email <input v-model="form.email" type="email" required /></label>
    <label>Телефон <input v-model="form.phone" required /></label>
    <label>Логин <input v-model="form.login" required/></label>
    <label>Пароль <input v-model="form.password" type="password" required/></label>

    <button type="submit">Создать</button>
    <p class="error" v-if="error">{{ error }}</p>
  </form>
</template>

<script setup>
import {ref, reactive} from 'vue'
import {useRouter} from 'vue-router'
import {createCenter} from '@/api/entity/center.js'

const router = useRouter()
const error = ref('')

const form = reactive({
  login: '',
  password: '',
  email: '',
  phone: '',
  name: '',
  description: '',
  coordinates: '',
  addressRegion: '',
  addressCity: '',
  addressStreet: '',
  addressBuilding: ''
})

async function submitForm() {
  try {
    await createCenter({...form})
    await router.push('/login')
  } catch (e) {
    error.value = 'Ошибка при регистрации'
    console.error(e)
  }
}
</script>
