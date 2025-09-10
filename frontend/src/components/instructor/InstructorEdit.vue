<template>
  <div class="modal-overlay" @click.self="handleClose">
    <div class="modal-content">
      <button class="close-button" @click="handleClose">×</button>

      <!-- Меню -->
      <div v-if="view === 'menu'" class="menu-screen">
        <h3>Настройки профиля</h3>
        <ul class="menu-list">
          <li @click="open('personal')">Персональные данные</li>
          <li @click="open('confidential')">Конфиденциальные данные</li>
          <li @click="open('sports')">Навыки</li>
          <li @click="open('documents')">Документы</li>
          <li @click="open('attach')">Прикрепление к СЦ</li>
        </ul>
      </div>

      <div v-if="view === 'personal'" class="form-screen">
        <div class="header">
          <button class="back" @click="open('menu')">←</button>
          <h3>Личные данные</h3>
        </div>
        <PersonalForm
            :instructor-id="instructorId"
            @saved="handleSaved"
            @cancel="open('menu')"
        />
      </div>

      <div v-if="view === 'confidential'" class="form-screen">
        <div class="header">
          <button class="back" @click="open('menu')">←</button>
          <h3>Конфиденциальные данные</h3>
        </div>
        <ConfidentialForm
            :user-id="userId"
            @saved="handleSaved"
            @cancel="open('menu')"
        />
      </div>

      <!-- Виды спорта -->
      <div v-if="view === 'sports'" class="form-screen">
        <div class="header">
          <button class="back" @click="open('menu')">←</button>
          <h3>Виды спорта</h3>
          <SportsForm
              :instructor-id="instructorId"
              @saved="handleSaved"
              @cancel="open('menu')"
          />
        </div>
      </div>

      <!-- Документы -->
      <div v-if="view === 'documents'" class="form-screen">
        <div class="header">
          <button class="back" @click="open('menu')">←</button>
          <h3>Документы</h3>
        </div>
        <p>Загрузка документов появится позже.</p>
      </div>

      <!-- Прикрепление к СЦ -->
      <div v-if="view === 'attach'" class="form-screen">
        <div class="header">
          <button class="back" @click="open('menu')">←</button>
          <h3>Прикрепление к СЦ</h3>
        </div>
        <p>Тут будет логика прикрепления к спортцентру.</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import PersonalForm from '@/components/instructor/InstructorEditPersonal.vue'
import ConfidentialForm from '@/components/instructor/InstructorEditConfid.vue'
import SportsForm from '@/components/instructor/InstructorEditAddSports.vue'
import { getCurrentUser } from '@/api/auth/auth.js'
import '@/assets/profile/editmenu.css'

const props = defineProps({
  instructorId: { type: Number, required: false }
})
const emit = defineEmits(['close', 'saved'])

const view = ref('menu') // текущее отображение
const userId = ref(null)

function open(name) {
  view.value = name
}

function handleClose() {
  emit('close')
}

function handleSaved(payload) {
  emit('saved', payload)
  open('menu')
}

onMounted(async () => {
  try {
    const { data } = await getCurrentUser()
    userId.value = data.id
  } catch (e) {
    console.warn('Не удалось получить текущего пользователя', e)
  }
})
</script>


