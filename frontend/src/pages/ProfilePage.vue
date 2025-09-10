<template>
  <div>
    <component :is="currentComponent" :user-id="userId" />
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { getCurrentUser } from '@/api/auth/auth.js'
import StudentProfile from '@/components/student/StudentProfile.vue'
import InstructorProfile from '@/components/instructor/InstructorProfile.vue'
import CenterProfile from "@/components/sportscenter/CenterProfile.vue";

const route = useRoute()
const userId = Number(route.params.id)

const userRole = ref(null)

onMounted(async () => {
  const { data } = await getCurrentUser()
  userRole.value = data.role
})

const currentComponent = computed(() => {
  if (userRole.value === 'student') return StudentProfile
  if (userRole.value === 'instructor') return InstructorProfile
  if (userRole.value === 'sportscenter') return CenterProfile
  return null
})
</script>
