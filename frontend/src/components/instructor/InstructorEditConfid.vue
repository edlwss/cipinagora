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

      <div class="password-section">
        <button
            type="button"
            class="change-password-btn"
            @click="togglePasswordChange"
        >
          {{ showPasswordFields ? 'Отменить смену пароля' : 'Изменить пароль' }}
        </button>

        <div v-if="showPasswordFields" class="password-fields">
          <label>Старый пароль
            <input v-model="passwordForm.oldPassword" type="password" />
          </label>

          <label>Новый пароль
            <input v-model="passwordForm.newPassword" type="password" />
          </label>

          <button
              type="button"
              class="confirm-password-btn"
              :disabled="loadingPassword"
              @click="submitPasswordChange"
          >
            {{ loadingPassword ? 'Сохраняю...' : 'Сменить пароль' }}
          </button>

          <p v-if="passwordError" class="error">{{ passwordError }}</p>
        </div>
      </div>

      <div class="actions">
        <button type="submit" :disabled="loading">{{ loading ? 'Сохраняю...' : 'Сохранить' }}</button>
        <button type="button" @click="$emit('cancel')">Отмена</button>
      </div>

      <p v-if="error" class="error">{{ error }}</p>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getUser, updateUser, changePassword } from '@/api/entity/user.js'
import { getCurrentUser } from '@/api/auth/auth.js'

const props = defineProps({
  userId: { type: Number, required: false }
})
const emit = defineEmits(['saved', 'cancel'])

const loading = ref(false)
const error = ref('')
const showPasswordFields = ref(false)
const loadingPassword = ref(false)
const passwordError = ref('')

const form = ref({
  login: '',
  email: '',
  phone: ''
})

const passwordForm = ref({
  oldPassword: '',
  newPassword: ''
})

async function loadData() {
  try {
    let id = props.userId
    if (!id) {
      const me = await getCurrentUser()
      id = me.data.id
    }

    const { data } = await getUser(id)
    form.value.login = data.login || ''
    form.value.email = data.email || ''
    form.value.phone = data.phone || ''
  } catch (e) {
    console.error('Не удалось загрузить user данные', e)
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

    const payload = {
      login: form.value.login || null,
      email: form.value.email || null,
      phone: form.value.phone || null
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

function togglePasswordChange() {
  showPasswordFields.value = !showPasswordFields.value
  if (!showPasswordFields.value) {
    passwordForm.value.oldPassword = ''
    passwordForm.value.newPassword = ''
    passwordError.value = ''
  }
}

async function submitPasswordChange() {
  loadingPassword.value = true
  passwordError.value = ''
  try {
    let id = props.userId
    if (!id) {
      const me = await getCurrentUser()
      id = me.data.id
    }

    const payload = {
      oldPassword: passwordForm.value.oldPassword,
      newPassword: passwordForm.value.newPassword
    }

    await changePassword(id, payload)

    passwordForm.value.oldPassword = ''
    passwordForm.value.newPassword = ''
    togglePasswordChange()
  } catch (e) {
    console.error(e)
    passwordError.value = e.response?.data?.message || 'Ошибка при смене пароля'
  } finally {
    loadingPassword.value = false
  }
}


onMounted(loadData)
</script>

<style scoped>
label {
  display: block;
  margin-bottom: 10px;
}

.actions {
  margin-top: 12px;
  display: flex;
  gap: 8px;
}

.error {
  color: #b00;
  margin-top: 8px;
}

.password-section {
  margin: 16px 0;
  padding: 10px;
  background: #f8f8f8;
  border-radius: 6px;
  font-size: 13px;
  color: #666;
}
</style>
