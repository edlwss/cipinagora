<template>
  <div>
    <component :is="currentComponent" />
  </div>
</template>

<script>
import StudentProfile from '@/components/student/StudentProfile.vue'
import StudentEdit from '@/components/student/StudentEdit.vue'

export default {
  components: {
    StudentProfile,
    StudentEdit,
  },
  computed: {
    currentComponent() {
      return this.$route.meta.component || 'StudentsTable'
    },
    pageTitle() {
      return this.$route.meta.title || 'Список студентов'
    }
  }
}
</script>
