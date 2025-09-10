<template>
  <div class="modal-backdrop" @click.self="close">
    <div class="modal-content">
      <h3>Редактировать фото</h3>
      <div class="photo-preview">
        <img v-if="preview || initialPhoto" :src="preview || initialPhoto" alt="Preview" />
        <div v-else class="placeholder">Фото отсутствует</div>
      </div>

      <input type="file" accept="image/*" @change="onFileChange" />

      <div class="modal-actions">
        <button @click="save">Сохранить</button>
        <button @click="close">Отмена</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { updatePhotoInstructor } from "@/api/entity/instructor.js";

const props = defineProps({
  initialPhoto: String,
  userId: Number
})

const emit = defineEmits(['close', 'saved'])

const preview = ref(null)
const file = ref(null)

function onFileChange(event) {
  const selected = event.target.files[0]
  if (!selected) return
  file.value = selected
  preview.value = URL.createObjectURL(selected)
}

function generateUniqueName() {
  const timestamp = Date.now()
  return `user_${props.userId}_${timestamp}`
}

async function uploadFileLocally(file, fileName) {
  const localUploadEndpoint = 'http://localhost:3001/upload'
  const formData = new FormData()
  formData.append('file', file, fileName)
  await fetch(localUploadEndpoint, { method: 'POST', body: formData })
}

async function save() {
  if (!file.value) {
    emit('close')
    return
  }

  const ext = file.value.name.split('.').pop()
  const uniqueFileName = generateUniqueName() + '.' + ext
  const photoPath = `/uploads/${uniqueFileName}`
  await uploadFileLocally(file.value, uniqueFileName)
  await updatePhotoInstructor(props.userId, { photo: photoPath })

  emit('saved', photoPath)
  emit('close')
}

function close() {
  emit('close')
}
</script>

<style scoped>
.modal-backdrop {
  position: fixed;
  inset: 0;
  background: rgba(0,0,0,0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background: #fff;
  padding: 1.5rem;
  border-radius: 12px;
  width: 300px;
  text-align: center;
}

.photo-preview {
  width: 300px;
  height: 300px;
  margin: 0 auto 1rem;
  overflow: hidden;
  border-radius: 12px;
  border: 1px solid #ccc;
}

.photo-preview img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.placeholder {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #f0f0f0;
  color: #999;
}

.modal-actions {
  display: flex;
  justify-content: space-between;
  margin-top: 1rem;
}
</style>
