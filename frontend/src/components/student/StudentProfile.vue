<template>
  <div class="profile-page">
    <!-- Шапка -->
    <AppHeader
        @toggle-sidebar="sidebarOpen = !sidebarOpen"
        @open-edit="openEditModal"
    />

    <!-- Сайдбар -->
    <AppSidebar
        :visible="sidebarOpen"
        @close="sidebarOpen = false"
    />

    <!-- Основное содержимое -->
    <main class="main-content">
      <div class="profile-card">
        <div class="profile-header">
          <div class="photo-wrapper">
            <img v-if="student.photo" :src="student.photo" alt="Фото" class="photo-img" />
            <span v-else class="photo-placeholder">фото</span>
          </div>
          <div>
            <p class="student-name">{{ fullName }}</p>
            <p class="student-group">возрастная группа — <span class="muted">пока пусто</span></p>
          </div>
        </div>

        <div class="skills-section">
          <h2 class="skills-title">МОИ СКИЛЫ</h2>
          <div class="skills-placeholder">пока ничего</div>
        </div>
      </div>
    </main>

    <StudentEditModal
        v-if="showEditModal"
        :student-id="student.id"
        @close="closeEditModal"
        @saved="handleProfileUpdate"
    />
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { useRoute } from 'vue-router'
import AppHeader from '@/components/ui/Header.vue'
import AppSidebar from '@/components/ui/Sidebar.vue'
import StudentEditModal from '@/components/student/StudentEdit.vue'
import { getStudentById } from '@/api/student'
import '@/assets/profile/profile.css'
import '@/assets/profile/sidebarheader.css'

const route = useRoute()
const student = ref({})
const showEditModal = ref(false)
const sidebarOpen = ref(false)

const fullName = computed(() => {
  if (!student.value.fullName) return ''
  const { firstName, lastName, middleName } = student.value.fullName
  return `${lastName} ${firstName} ${middleName}`
})

const openEditModal = () => {
  showEditModal.value = true
}

const closeEditModal = () => {
  showEditModal.value = false
}

const handleProfileUpdate = async () => {
  const response = await getStudentById(route.params.id)
  student.value = response.data
  closeEditModal()
}

onMounted(async () => {
  const response = await getStudentById(route.params.id)
  student.value = response.data
})
</script>

<style scoped>
</style>