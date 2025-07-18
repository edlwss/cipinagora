<template>
  <form class="form" @submit.prevent="submitForm">
    <label>Фамилия <input v-model="form.fullNameLastName" required /></label>
    <label>Имя <input v-model="form.fullNameFirstName" required /></label>
    <label>Отчество <input v-model="form.fullNameMiddleName" /></label>

    <label>Почта <input v-model="form.email" type="email" required /></label>
    <label>Телефон <input v-model="form.phone" required /></label>

    <label>Логин <input v-model="form.login" required /></label>
    <label>Пароль <input v-model="form.password" type="password" required /></label>

    <label>Навыки <textarea v-model="form.skillDescription" /></label>
    <label>Номер сертификата <input v-model="form.certificateNumber" /></label>

    <label>
      <input type="checkbox" v-model="form.officialEmployment" />
      Официальное трудоустройство
    </label>

    <label>
      <input type="checkbox" v-model="form.dataVerified" />
      Данные подтверждены
    </label>

    <div class="photo-upload">
      <label>Фото профиля</label>
      <input type="file" @change="onFileChange" accept="image/*" />
      <img v-if="preview" :src="preview" alt="Preview" class="preview-img" />
    </div>

    <button type="submit">Зарегистрировать</button>
    <p class="error" v-if="error">{{ error }}</p>
  </form>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { createInstructor } from '@/api/instructor.js' // создай функцию API для POST-запроса

const router = useRouter()
const error = ref('')
const preview = ref(null)
const photoFile = ref(null)

const form = reactive({
  login: '',
  password: '',
  email: '',
  phone: '',
  fullNameLastName: '',
  fullNameFirstName: '',
  fullNameMiddleName: '',
  photo: '',
  skillDescription: '',
  certificateNumber: '',
  dataVerified: false,
  officialEmployment: false
})

function generateUniqueName() {
  const timestamp = Date.now()
  return `instructor_${form.login}_${timestamp}`
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
  await fetch(localUploadEndpoint, {
    method: 'POST',
    body: formData
  })
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
    await createInstructor({
      login: form.login,
      password: form.password,
      email: form.email,
      phone: form.phone,
      fullNameLastName: form.fullNameLastName,
      fullNameFirstName: form.fullNameFirstName,
      fullNameMiddleName: form.fullNameMiddleName,
      photo: photoPath,
      skillDescription: form.skillDescription,
      certificateNumber: form.certificateNumber,
      dataVerified: form.dataVerified,
      officialEmployment: form.officialEmployment
    })

    await router.push('/login')
  } catch (e) {
    error.value = 'Ошибка при регистрации инструктора'
    console.error(e)
  }
}
</script>


