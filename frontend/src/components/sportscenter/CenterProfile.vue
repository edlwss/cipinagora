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
          <div>
            <p class="student-name">{{ center.name }}</p>
            <p class="student-group">Город — <span class="muted">{{ center.addressCity || 'не указано' }}</span></p>
          </div>
        </div>

        <div class="skills-section">
          <h2 class="skills-title">Описание</h2>
          <p>{{ center.description || 'Пока ничего' }}</p>
        </div>
      </div>
    </main>

    <SportsCenterEditModal
        v-if="showEditModal"
        @close="closeEditModal"
        @saved="handleProfileUpdate"
    />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getCenterById } from '@/api/entity/center.js'
import AppHeader from '@/components/ui/Header.vue'
import AppSidebar from '@/components/ui/Sidebar.vue'
import SportsCenterEditModal from '@/components/sportscenter/CenterEdit.vue'

import '@/assets/profile/profile.css'
import '@/assets/profile/sidebarheader.css'
import router from "@/routers/route.js";
import {useRoute} from "vue-router";

const sidebarOpen = ref(false)
const showEditModal = ref(false)
const center = ref({})
const route = useRoute()

function openEditModal() {
  showEditModal.value = true
}

function closeEditModal() {
  showEditModal.value = false
}

onMounted(async () => {
  try {
    const response = await getCenterById(route.params.id)
    center.value = response.data
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
