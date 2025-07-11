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
import {onMounted, ref} from 'vue'
import {useRoute, useRouter} from 'vue-router'
import {getStudentById, updateStudent} from '@/api/student'
import '@/assets/profile/edit.css'

const route = useRoute()
const router = useRouter()
const studentId = Number(route.params.id)
const emit = defineEmits(['close'])

const form = ref({
  lastName: '',
  firstName: '',
  middleName: '',
  photoFile: null
})
const preview = ref(null)
const originalPhoto = ref(null)

onMounted(async () => {
  const { data } = await getStudentById(studentId)
  form.value.lastName = data.fullName.lastName || ''
  form.value.firstName = data.fullName.firstName || ''
  form.value.middleName = data.fullName.middleName || ''
  originalPhoto.value = data.photo || null
  preview.value = originalPhoto.value
})

function closeModal() {
  emit('close')
  router.push(`/cipinagora/student/profile/${studentId}`)
}

function generateUniqueName() {
  const timestamp = Date.now()
  return `student_${studentId}_${timestamp}`
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
    await updateStudent(studentId, payload)
    emit('saved')
    closeModal()
  } catch (error) {
    console.error('Ошибка при сохранении:', error)
  }
}
</script>



