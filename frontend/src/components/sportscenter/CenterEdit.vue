<template>
  <div class="modal-overlay" @click.self="closeModal">
    <div class="modal-content">
      <button class="close-button" @click="closeModal">×</button>

      <h2>Редактировать центр</h2>

      <form @submit.prevent="submitForm">
        <label>Название <input v-model="form.name" required /></label>
        <label>Описание <textarea v-model="form.description" /></label>
        <label>Координаты <input v-model="form.coordinates" /></label>

        <label>Регион <input v-model="form.addressRegion" /></label>
        <label>Город <input v-model="form.addressCity" /></label>
        <label>Улица <input v-model="form.addressStreet" /></label>
        <label>Дом <input v-model="form.addressBuilding" /></label>

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
import { getCenterById, updateCenter } from '@/api/center'
import '@/assets/profile/editmenu.css'

const emit = defineEmits(['close', 'saved'])
const router = useRouter()

const userId = ref(null)
const previousRoute = ref(router.currentRoute.value.fullPath)

const form = ref({
  name: '',
  description: '',
  coordinates: '',
  addressRegion: '',
  addressCity: '',
  addressStreet: '',
  addressBuilding: ''
})

onMounted(async () => {
  const { data } = await getCurrentUser()
  userId.value = data.id

  const { data: center } = await getCenterById(userId.value)
  Object.assign(form.value, center)
})

function closeModal() {
  emit('close')
  router.push(previousRoute.value)
}

async function submitForm() {
  try {
    await updateCenter(userId.value, { ...form.value })
    emit('saved')
    await router.push(`/cipinagora/profile/${userId.value}`)
  } catch (err) {
    console.error('Ошибка при сохранении:', err)
  }
}
</script>
