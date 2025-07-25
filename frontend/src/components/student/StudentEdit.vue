<template>
  <div class="modal-overlay" @click.self="closeModal">
    <div class="modal-content">

      <button class="close-button" @click="closeModal">×</button>

      <h2>Редактировать профиль</h2>

      <form @submit.prevent="submitForm">
        <label>Фамилия <input v-model="form.lastName" required /></label>
        <label>Имя <input v-model="form.firstName" required /></label>
        <label>Отчество <input v-model="form.middleName" /></label>

        <div class="photo-upload">
          <label>Фото профиля</label>
          <input type="file" @change="onFileChange" accept="image/*" />
          <img v-if="preview" :src="preview" alt="Preview" class="preview-img" />
        </div>

        <div class="modal-actions">
          <button type="submit">Сохранить</button>
        </div>
      </form>
    </div>
  </div>
</template>


<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { getStudentById, updateStudent } from '@/api/student'
import { getCurrentUser } from '@/api/auth'
import '@/assets/profile/edit.css'

const router = useRouter()
const emit = defineEmits(['close', 'saved'])
const userId = ref(null)
const previousRoute = ref(router.currentRoute.value.fullPath)

const form = ref({
  lastName: '',
  firstName: '',
  middleName: '',
  photoFile: null
})

const preview = ref(null)
const originalPhoto = ref(null)

onMounted(async () => {
  const { data } = await getCurrentUser()
  userId.value = data.id

  const userData = await getStudentById(userId.value)
  form.value.lastName = userData.data.lastName || ''
  form.value.firstName = userData.data.firstName || ''
  form.value.middleName = userData.data.middleName || ''
  originalPhoto.value = userData.data.photo || null
  preview.value = originalPhoto.value
})

function closeModal() {
  emit('close')
  router.push(previousRoute.value)
}

function generateUniqueName() {
  const timestamp = Date.now()
  return `student_${userId.value}_${timestamp}`
}

function onFileChange(event) {
  const file = event.target.files[0]
  if (!file) return
  form.value.photoFile = file
  preview.value = URL.createObjectURL(file)
}

async function uploadFileLocally(file, fileName) {
  const localUploadEndpoint = 'http://localhost:3001/upload'
  const formData = new FormData()
  formData.append('file', file, fileName)
  await fetch(localUploadEndpoint, { method: 'POST', body: formData })
}

async function submitForm() {
  let photoPath = originalPhoto.value

  if (form.value.photoFile) {
    const ext = form.value.photoFile.name.split('.').pop()
    const uniqueFileName = generateUniqueName() + '.' + ext
    photoPath = `/uploads/${uniqueFileName}`
    await uploadFileLocally(form.value.photoFile, uniqueFileName)
  }

  const payload = {
    fullNameLastName: form.value.lastName,
    fullNameFirstName: form.value.firstName,
    fullNameMiddleName: form.value.middleName,
    photo: photoPath
  }

  try {
    await updateStudent(userId.value, payload)
    emit('saved')
    await router.push(`/cipinagora/profile/${userId.value}`)
  } catch (error) {
    console.error('Ошибка при сохранении:', error)
  }
}
</script>




