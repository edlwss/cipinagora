<template>
  <div class="confidential-form">
    <form @submit.prevent="submitForm">
      <label>Логин
        <input v-model="form.login" required />
      </label>

      <label>Email
        <input v-model="form.email" type="email" />
      </label>

      <label>Телефон
        <input v-model="form.phone" />
      </label>

      <hr />

      <label>Новый пароль
        <input v-model="form.password" type="password" placeholder="Оставьте пустым, если не менять" />
      </label>

      <div class="actions">
        <button :disabled="loading">{{ loading ? 'Сохраняю...' : 'Сохранить' }}</button>
        <button type="button" @click="$emit('cancel')">Отмена</button>
      </div>

      <p v-if="error" class="error">{{ error }}</p>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getCurrentUser } from '@/api/auth'
import { updateUser } from '@/api/user' // api-путь к update user

const props = defineProps({
  userId: { type: Number, required: false }
})
const emit = defineEmits(['saved', 'cancel'])

const loading = ref(false)
const error = ref('')

const form = ref({
  login: '',
  email: '',
  phone: '',
  password: ''
})

async function loadData() {
  try {
    let id = props.userId
    if (!id) {
      const me = await getCurrentUser()
      id = me.data.id
    }

    // Здесь предполагается эндпоинт /auth/me дает логин/email/phone
    // Если нет — получить данные пользователя другим запросом
    const me = await getCurrentUser()
    form.value.login = me.data.login || form.value.login
    form.value.email = me.data.email || form.value.email
    form.value.phone = me.data.phone || form.value.phone
  } catch (e) {
    console.warn('Не удалось загрузить user данные', e)
  }
}

async function submitForm() {
  loading.value = true
  error.value = ''
  try {
    let id = props.userId
    if (!id) {
      const me = await getCurrentUser()
      id = me.data.id
    }

    // соберём частичный пейлод: не отправляем пустой пароль
    const payload = {
      login: form.value.login || null,
      email: form.value.email || null,
      phone: form.value.phone || null,
      password: form.value.password && form.value.password.length ? form.value.password : null
    }

    await updateUser(id, payload)
    emit('saved')
  } catch (e) {
    console.error(e)
    error.value = 'Ошибка при сохранении'
  } finally {
    loading.value = false
  }
}

onMounted(loadData)
</script>

<style scoped>
label { display:block; margin-bottom:10px; }
.actions { margin-top:12px; display:flex; gap:8px; }
.error { color:#b00; margin-top:8px; }
</style>
