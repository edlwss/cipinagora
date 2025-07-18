<template>
  <div class="modal-overlay" @click.self="closeModal">
    <div class="modal-content">
      <button class="close-button" @click="closeModal">×</button>
      <h2>Редактировать инструктора</h2>

      <form @submit.prevent="submitForm">
        <label>Фамилия <input v-model="form.lastName" required /></label>
        <label>Имя <input v-model="form.firstName" required /></label>
        <label>Отчество <input v-model="form.middleName" /></label>
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

        <div class="modal-actions">
          <button type="submit">Сохранить</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import { getCurrentUser } from '@/api/auth'
import { getInstructorById, updateInstructor } from '@/api/instructor'
import '@/assets/profile/edit.css'

const router = useRouter()
const emit = defineEmits(['close', 'saved'])

const userId = ref(null)
const previousRoute = ref(router.currentRoute.value.fullPath)

const form = ref({
  lastName: '',
  firstName: '',
  middleName: '',
  skillDescription: '',
  certificateNumber: '',
  officialEmployment: false,
  dataVerified: false,
  photoFile: null
})

const preview = ref(null)
const originalPhoto = ref(null)

onMounted(async () => {
  const { data } = await getCurrentUser()
  userId.value = data.id

  const { data: instructor } = await getInstructorById(userId.value)
  Object.assign(form.value, {
    lastName: instructor.fullNameLastName || '',
    firstName: instructor.fullNameFirstName || '',
    middleName: instructor.fullNameMiddleName || '',
    skillDescription: instructor.skillDescription || '',
    certificateNumber: instructor.certificateNumber || '',
    officialEmployment: instructor.officialEmployment || false,
    dataVerified: instructor.dataVerified || false
  })
  originalPhoto.value = instructor.photo || null
  preview.value = originalPhoto.value
})

function closeModal() {
  emit('close')
  router.push(previousRoute.value)
}

function generateUniqueName() {
  return `instructor_${userId.value}_${Date.now()}`
}

function onFileChange(event) {
  const file = event.target.files[0]
  if (!file) return
  form.value.photoFile = file
  preview.value = URL.createObjectURL(file)
}

async function uploadFileLocally(file, fileName) {
  const uploadUrl = 'http://localhost:3001/upload'
  const formData = new FormData()
  formData.append('file', file, fileName)
  await fetch(uploadUrl, { method: 'POST', body: formData })
}

async function submitForm() {
  let photoPath = originalPhoto.value

  if (form.value.photoFile) {
    const ext = form.value.photoFile.name.split('.').pop()
    const fileName = generateUniqueName() + '.' + ext
    photoPath = `/uploads/${fileName}`
    await uploadFileLocally(form.value.photoFile, fileName)
  }

  const payload = {
    fullNameLastName: form.value.lastName,
    fullNameFirstName: form.value.firstName,
    fullNameMiddleName: form.value.middleName,
    photo: photoPath,
    skillDescription: form.value.skillDescription,
    certificateNumber: form.value.certificateNumber,
    dataVerified: form.value.dataVerified,
    officialEmployment: form.value.officialEmployment
  }

  try {
    await updateInstructor(userId.value, payload)
    emit('saved')
    await router.push(`/cipinagora/profile/${userId.value}`)
  } catch (err) {
    console.error('Ошибка при сохранении:', err)
  }
}
</script>

