<template>
  <transition name="slide">
    <aside v-if="visible" class="sidebar">
      <button @click="$emit('close')" class="close-btn">✕</button>
      <ul class="menu-list">
        <li><RouterLink to="/profile/1">Мой профиль</RouterLink></li>
        <li><RouterLink to="/courses">Записаться на курс</RouterLink></li>
        <li><RouterLink to="/">Дом</RouterLink></li>
      </ul>
    </aside>
  </transition>
</template>


<script setup>
defineProps({ visible: Boolean })
</script>

<style scoped>
.sidebar {
  background: white;
  width: 240px;
  height: 100vh;
  padding: 20px;
  position: fixed;
  top: 0;
  left: 0;
  box-shadow: 2px 0 6px rgba(0, 0, 0, 0.1);
  z-index: 10;
}

.menu-list {
  margin-top: 70px;
  list-style: none;
  padding: 0;
}

.menu-list li {
  margin-bottom: 16px;
}

.menu-list a {
  color: var(--text-color);
  text-decoration: none;
  font-weight: 500;
}

.menu-list a:hover {
  color: var(--primary-color);
}

.close-btn {
  background: none;
  border: none;
  font-size: 20px;
  position: absolute;
  top: 60px;
  right: 12px;
  cursor: pointer;
  color: var(--text-color);
}

/* Animation */
.slide-enter-active,
.slide-leave-active {
  transition: transform 0.3s ease;
}
.slide-enter-from,
.slide-leave-to {
  transform: translateX(-100%);
}
</style>
