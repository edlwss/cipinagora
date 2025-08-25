<template>
  <div class="profile-page">
   <AppHeader
        @toggle-sidebar="sidebarOpen = !sidebarOpen"
        @open-edit="openEditModal"
    />
    <AppSidebar
        :visible="sidebarOpen"
        @close="sidebarOpen = false"
    />
    <main class="main-content">
      <div class="profile-card">
        <div class="profile-header">
          <div class="photo-wrapper">
            <img v-if="student.photo" :src="student.photo" alt="Фото" class="photo-img" />
            <span v-else class="photo-placeholder">фото</span>
          </div>
          <div>
            <p class="student-name">{{ student.fullName }}</p>
            <p class="student-group">
              Возрастная группа:
              <span class="muted">{{ student.age }}</span>
              <span
                  v-if="student.gender"
                  class="gender-inline"
                  @mouseenter="showTooltip = true"
                  @mouseleave="showTooltip = false"
              >({{ genderShort }})
                  <span v-if="showTooltip" class="tooltip">{{ genderFull }}</span>
              </span>
            </p>
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
import router from "@/routers/route.js";

const route = useRoute()
const student = ref({})
const showEditModal = ref(false)
const sidebarOpen = ref(false)

const showTooltip = ref(false)

const genderShort = computed(() => {
  if (student.value.gender === 'Male') return 'М'
  if (student.value.gender === 'Female') return 'Ж'
  return ''
})

const genderFull = computed(() => {
  if (student.value.gender === 'Male') return 'Мужской пол'
  if (student.value.gender === 'Female') return 'Женский пол'
  return ''
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
  try {
    const response = await getStudentById(route.params.id)
    student.value = response.data
  } catch (error) {
    const status = error?.response?.status
    if ([404, 500].includes(status)) {
      await router.replace({name: 'not-found'})
    } else {
      console.error('Ошибка загрузки профиля:', error)
    }
  }
})
</script>

<style scoped>
</style>