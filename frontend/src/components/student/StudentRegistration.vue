<template>
  <form class="form" @submit.prevent="submitForm">
    <label>Фамилия <input v-model="form.fullNameLastName" required /></label>
    <label>Имя <input v-model="form.fullNameFirstName" required /></label>
    <label>Отчество <input v-model="form.fullNameMiddleName" /></label>
    <label>Почта <input v-model="form.email" type="email" required /></label>
    <label>Телефон <input v-model="form.phone" required /></label>
    <label>Логин <input v-model="form.login" required /></label>
    <label>Пароль <input v-model="form.password" type="password" required /></label>

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

    <div class="photo-upload">
      <label>Фото профиля</label>
      <input type="file" @change="onFileChange" accept="image/*" />
      <img v-if="preview" :src="preview" alt="Preview" class="preview-img" />
    </div>

    <button type="submit">Создать</button>
    <p class="error" v-if="error">{{ error }}</p>
  </form>
</template>

<script setup>
import {ref, reactive, onMounted} from 'vue'
import { useRouter } from 'vue-router'
import { createStudent } from '@/api/student.js'
import { getAllAgeCategories } from '@/api/agecategories.js'

const router = useRouter()
const error = ref('')
const preview = ref(null)
const photoFile = ref(null)
const ageCategories = ref([])

const form = reactive({
  login: '',
  password: '',
  email: '',
  phone: '',
  fullNameLastName: '',
  fullNameFirstName: '',
  fullNameMiddleName: '',
  ageId: '',
  gender: '',
  photo: ''
})

function generateUniqueName() {
  const timestamp = Date.now()
  return `student_${form.login}_${timestamp}`
}

function onFileChange(event) {
  const file = event.target.files[0]
  if (!file) return
  photoFile.value = file
  preview.value = URL.createObjectURL(file)
}

async function uploadFileLocally(file, fileName) {
  const localUploadEndpoint = 'http://localhost:3001/upload'
  const formData = new FormData()
  formData.append('file', file, fileName)
  await fetch(localUploadEndpoint, {method: 'POST', body: formData})
}

async function loadAgeCategories() {
  try {
    const { data } = await getAllAgeCategories()
    ageCategories.value = data
  } catch (e) {
    console.error('Ошибка загрузки возрастных категорий', e)
  }
}

async function submitForm() {
  let photoPath = null

  if (photoFile.value) {
    const ext = photoFile.value.name.split('.').pop()
    const uniqueFileName = generateUniqueName() + '.' + ext
    photoPath = `/uploads/${uniqueFileName}`
    await uploadFileLocally(photoFile.value, uniqueFileName)
  }

  try {
    await createStudent({
      login: form.login,
      password: form.password,
      email: form.email,
      phone: form.phone,
      fullNameLastName: form.fullNameLastName,
      fullNameFirstName: form.fullNameFirstName,
      fullNameMiddleName: form.fullNameMiddleName,
      ageId: form.ageId,
      gender: form.gender,
      photo: photoPath
    })

    await router.push('/login')
  } catch (e) {
    error.value = 'Ошибка при регистрации'
    console.error(e)
  }
}

onMounted(() => {
  loadAgeCategories()
})
</script>