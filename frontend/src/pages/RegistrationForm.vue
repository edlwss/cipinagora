<template>
  <div class="register-page">
    <div class="side-title">Регистрация</div>
    <div class="form-box">
      <div class="role-switcher">
        <label v-for="option in roles" :key="option.value">
          <input
              type="radio"
              v-model="selectedRole"
              :value="option.value"
          />
          {{ option.label }}
        </label>
      </div>

      <component
          :is="currentForm"
          @success="handleSuccess"
      />
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import StudentRegisterForm from '@/components/student/StudentRegistration.vue'
import InstructorRegisterForm from '@/components/instructor/InstructorRegistration.vue'
import CenterRegisterForm from '@/components/sportscenter/CenterRegistration.vue'

const selectedRole = ref('student')

const roles = [
  { label: 'Студент', value: 'student' },
  { label: 'Инструктор', value: 'instructor' },
  { label: 'СЦ', value: 'center' }
]

const currentForm = computed(() => {
  switch (selectedRole.value) {
    case 'student': return StudentRegisterForm
    case 'instructor': return InstructorRegisterForm
    case 'center': return CenterRegisterForm
  }
})

function handleSuccess() {
  alert('Регистрация прошла успешно!')
}
</script>

<style scoped>
.register-page {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  font-family: sans-serif;
}
.side-title {
  writing-mode: vertical-rl;
  transform: rotate(180deg);
  font-size: 24px;
  margin-right: 24px;
}
.form-box {
  border: 2px solid black;
  border-radius: 20px;
  padding: 30px;
  width: 300px;
}
.role-switcher {
  display: flex;
  justify-content: space-around;
  margin-bottom: 20px;
}
</style>
