<template>
  <header class="app-header">
    <button @click="$emit('toggle-sidebar')" class="icon-btn">&#9776;</button>

    <div class="relative">
      <button @click="toggleSettings" class="icon-btn">
        <SettingsIcon class="icon" />
      </button>

      <div v-if="settingsOpen" class="settings-dropdown">
        <RouterLink :to="profileEditLink" class="dropdown-link">
          Настройки профиля
        </RouterLink>
      </div>
    </div>
  </header>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRoute } from 'vue-router'
import { SettingsIcon } from 'lucide-vue-next'

const settingsOpen = ref(false)
const toggleSettings = () => (settingsOpen.value = !settingsOpen.value)

const route = useRoute()

const profileEditLink = computed(() => {
  const segments = route.path.split('/')
  const id = route.params.id

  const role = segments.includes('student')
      ? 'student'
      : segments.includes('instructor')
          ? 'instructor'
          : null

  return role && id ? `/cipinagora/${role}/profile/${id}/edit` : '#'
})
</script>


<style scoped>
.app-header {
  position: relative;
  background: var(--primary-color);
  color: white;
  padding: 12px 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  z-index: 30;
}

.icon-btn {
  background: none;
  border: none;
  color: white;
  font-size: 22px;
  cursor: pointer;
}

.icon {
  width: 24px;
  height: 24px;
}

.settings-dropdown {
  position: absolute;
  top: 100%;
  right: 0;
  background: white;
  border: 1px solid #ddd;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  border-radius: 6px;
  margin-top: 6px;
  z-index: 10;
}

.dropdown-link {
  display: block;
  padding: 10px 16px;
  color: #333;
  text-decoration: none;
}

.dropdown-link:hover {
  background: var(--secondary-color);
}
</style>
