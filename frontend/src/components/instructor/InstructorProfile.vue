<template>
  <div class="profile-page">
    <AppHeader @toggle-sidebar="sidebarOpen = !sidebarOpen" @open-edit="openEditModal" />
    <AppSidebar :visible="sidebarOpen" @close="sidebarOpen = false" />

    <main class="main-content">
      <div class="profile-card">
        <div class="profile-header">
          <div class="photo-wrapper">
            <img v-if="instructor.photo" :src="instructor.photo" alt="Фото" class="photo-img" />
            <span v-else class="photo-placeholder">фото</span>
          </div>

          <div class="info-wrapper">
            <p class="instructor-name">
              {{ instructor.fullName }}
              <span v-if="instructor.dataVerified" class="verified-icon">✔</span>
            </p>
            <p class="certificate-number">
              № {{ instructor.certificateNumber || '—' }}
            </p>
            <div class="basic-details">
              <span>Группа: {{ '—' }}</span>
              <span>Пол: {{ '—' }}</span>
            </div>
          </div>
        </div>

        <div class="profile-section">
          <h3>Навыки</h3>
          <p>{{ instructor.skillDescription || 'Виды спорта, краткое описание навыков' }}</p>
          <p>Файлы (сертификаты и т.д. в PDF) — пока заглушка</p>
        </div>

        <div class="profile-section">
          <h3>Меня можно найти</h3>
          <p>
            {{ instructor.officialEmployment ? 'Центр спорта: заглушка' : 'Работаю в частном порядке' }}
          </p>
        </div>
      </div>
    </main>

    <InstructorEditModal
        v-if="showEditModal"
        :instructor-id="instructorId"
        @close="closeEditModal"
        @saved="fetchInstructor"
    />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import AppHeader from '@/components/ui/Header.vue'
import AppSidebar from '@/components/ui/Sidebar.vue'
import InstructorEditModal from '@/components/instructor/InstructorEdit.vue'
import { getInstructorById } from '@/api/instructor'
import '@/assets/profile/profile.css'
import '@/assets/profile/sidebarheader.css'
import router from "@/routers/route.js"

const route = useRoute()
const instructorId = Number(route.params.id)
const instructor = ref({})
const showEditModal = ref(false)
const sidebarOpen = ref(false)

const openEditModal = () => (showEditModal.value = true)
const closeEditModal = () => (showEditModal.value = false)

async function fetchInstructor() {
  try {
    const response = await getInstructorById(route.params.id)
    instructor.value = response.data
  } catch (error) {
    const status = error?.response?.status
    if ([404, 500].includes(status)) {
      await router.replace({name: 'not-found'})
    } else {
      console.error('Ошибка загрузки профиля:', error)
    }
  }
}

onMounted(fetchInstructor)
</script>