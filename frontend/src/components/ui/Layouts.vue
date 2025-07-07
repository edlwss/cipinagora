<template>
  <div class="layout">
    <AppHeader @toggle-sidebar="sidebarOpen = !sidebarOpen" />
    <AppSidebar :visible="sidebarOpen" />
    <AppSidebar :visible="sidebarOpen" @close="sidebarOpen = false" />
    <main class="main-content">
      <slot />
    </main>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import AppHeader from './Header.vue'
import AppSidebar from './Sidebar.vue'

const sidebarOpen = ref(false)
</script>

<style scoped>
.layout {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.main-content {
  flex: 1;
  padding: 24px;
  margin-left: 0;
}
</style>
