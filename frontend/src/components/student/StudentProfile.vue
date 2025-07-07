<template>
  <AppLayout>
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
  </AppLayout>
</template>

<script setup>
import {ref, onMounted, computed} from 'vue'
import {useRoute} from 'vue-router'
import AppLayout from '@/components/ui/Layouts.vue'
import {getStudentById} from '@/api/student'
import '@/assets/profile/profile.css'

const route = useRoute()
const student = ref({})

const fullName = computed(() => {
  if (!student.value.fullName) return ''
  const {firstName, lastName, middleName} = student.value.fullName
  return `${lastName} ${firstName} ${middleName}`
})

onMounted(async () => {
  const response = await getStudentById(route.params.id)
  student.value = response.data
})
</script>