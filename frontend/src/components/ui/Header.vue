<template>
  <header class="app-header">
    <button @click="$emit('toggle-sidebar')" class="icon-btn">&#9776;</button>

    <div class="relative">
      <button @click="toggleSettings" class="icon-btn">
        <SettingsIcon class="icon" />
      </button>

      <div v-if="settingsOpen" class="settings-dropdown">
        <button @click="openProfileEdit" class="dropdown-link">
          Настройки профиля
        </button>
      </div>
    </div>
  </header>
</template>

<script setup>
import { ref } from 'vue'
import { SettingsIcon } from 'lucide-vue-next'

const emit = defineEmits(['toggle-sidebar', 'open-edit'])

const settingsOpen = ref(false)
const toggleSettings = () => (settingsOpen.value = !settingsOpen.value)

const openProfileEdit = () => {
  emit('open-edit')
  settingsOpen.value = false
}
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
