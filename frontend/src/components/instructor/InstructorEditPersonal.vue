<template>
  <div class="personal-form">
    <form @submit.prevent="submitForm">
      <label>Фамилия
        <input v-model="form.lastName" required />
      </label>

      <label>Имя
        <input v-model="form.firstName" required />
      </label>

      <label>Отчество
        <input v-model="form.middleName" />
      </label>

      <label>Пол
        <select v-model="form.gender" required>
          <option disabled value="">Выберите пол</option>
          <option value="Male">Мужской</option>
          <option value="Female">Женский</option>
        </select>
      </label>

      <label>Возрастная категория
        <select v-model="form.ageId" required>
          <option disabled value="">Выберите категорию</option>
          <option v-for="cat in ageCategories" :key="cat.id" :value="cat.id">
            {{ cat.nameCategories }}
          </option>
        </select>
      </label>

      <label>Навыки
        <textarea v-model="form.skillDescription" rows="3"></textarea>
      </label>

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
import { getInstructorById, updatePersonalInstructor } from '@/api/instructor'
import { getAllAgeCategories } from '@/api/agecategories'
import { getCurrentUser } from '@/api/auth'

const props = defineProps({
  instructorId: { type: Number, required: false }
})
const emit = defineEmits(['saved', 'cancel'])

const loading = ref(false)
const error = ref('')
const ageCategories = ref([])

const form = ref({
  lastName: '',
  firstName: '',
  middleName: '',
  gender: '',
  ageId: '',
  skillDescription: ''
})

async function loadAgeCategories() {
  try {
    const { data } = await getAllAgeCategories()
    ageCategories.value = data
  } catch (e) {
    console.error('Не удалось загрузить возрастные категории', e)
  }
}

async function loadData() {
  try {
    let id = props.instructorId
    if (!id) {
      const me = await getCurrentUser()
      id = me.data.id
    }
    const { data } = await getInstructorById(id)
    form.value.lastName = data.fullNameLastName || ''
    form.value.firstName = data.fullNameFirstName || ''
    form.value.middleName = data.fullNameMiddleName || ''
    form.value.gender = data.gender || ''
    form.value.ageId = data.ageId || ''
    form.value.skillDescription = data.skillDescription || ''
  } catch (e) {
    console.error('Ошибка загрузки данных инструктора', e)
  }
}

async function submitForm() {
  loading.value = true
  error.value = ''
  try {

    const payload = {
      fullNameLastName: form.value.lastName || null,
      fullNameFirstName: form.value.firstName || null,
      fullNameMiddleName: form.value.middleName || null,
      gender: form.value.gender || null,
      ageId: form.value.ageId || null,
      skillDescription: form.value.skillDescription || null
    }

    let id = props.instructorId
    if (!id) {
      const me = await getCurrentUser()
      id = me.data.id
    }

    await updatePersonalInstructor(id, payload)
    emit('saved')
  } catch (e) {
    console.error(e)
    error.value = 'Ошибка при сохранении'
  } finally {
    loading.value = false
  }
}

onMounted(async () => {
  await loadAgeCategories()
  await loadData()
})
</script>


