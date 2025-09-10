<template>
  <div class="sports-form">
    <div class="add-section">
      <select v-model="selectedSportId">
        <option disabled value="">Выберите вид спорта</option>
        <option
            v-for="sport in sports"
            :key="sport.id"
            :value="sport.id"
            :disabled="selectedSports.some(s => s.id === sport.id)"
        >
          {{ sport.name }}
        </option>
      </select>
      <button type="button" @click="addSport" :disabled="!selectedSportId">
        Добавить
      </button>
    </div>

    <ul class="selected-list">
      <li v-for="sport in selectedSports" :key="sport.id">
        {{ sport.name }}
        <button type="button" class="remove" @click="removeSport(sport.id)">×</button>
      </li>
    </ul>

    <div class="actions">
      <button
          type="button"
          @click="submitSports"
          :disabled="loading"
      >
        {{ loading ? "Сохраняю..." : "Сохранить" }}
      </button>
      <button type="button" @click="$emit('cancel')">Отмена</button>
    </div>

    <p v-if="error" class="error">{{ error }}</p>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue"
import { getSports } from "@/api/lookup/sport"
import { getInstructorById, updateAddSportsInstructor } from "@/api/entity/instructor.js"
import { getCurrentUser } from "@/api/auth/auth.js"

const props = defineProps({
  instructorId: { type: Number, required: false }
})
const emit = defineEmits(["saved", "cancel"])

const sports = ref([])               // все доступные виды спорта
const selectedSports = ref([])       // уже привязанные
const selectedSportId = ref("")      // текущий выбранный для добавления
const loading = ref(false)
const error = ref("")

async function loadData() {
  try {
    // загрузить справочник всех видов спорта
    const {data: sportsList} = await getSports()
    sports.value = sportsList

    // определить id инструктора
    let id = props.instructorId
    if (!id) {
      const me = await getCurrentUser()
      id = me.data.id
    }

    // получить инструктора с текущими видами спорта
    const {data: instructor} = await getInstructorById(id)
    selectedSports.value = instructor.sports || []
  } catch (e) {
    console.error("Ошибка загрузки данных", e)
  }
}

function addSport() {
  const sport = sports.value.find(s => s.id === selectedSportId.value)
  if (sport && !selectedSports.value.some(s => s.id === sport.id)) {
    selectedSports.value.push(sport)
  }
  selectedSportId.value = ""
}

function removeSport(id) {
  selectedSports.value = selectedSports.value.filter(s => s.id !== id)
}

async function submitSports() {
  loading.value = true
  error.value = ""
  try {
    let id = props.instructorId
    if (!id) {
      const me = await getCurrentUser()
      id = me.data.id
    }

    const payload = {
      sportIds: selectedSports.value.map(s => s.id)
    }

    await updateAddSportsInstructor(id, payload)
    emit("saved")
  } catch (e) {
    console.error(e)
    error.value = "Ошибка при сохранении"
  } finally {
    loading.value = false
  }
}

onMounted(loadData)
</script>

<style scoped>
.sports-form {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.add-section {
  display: flex;
  gap: 8px;
  align-items: center;
}

.selected-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.selected-list li {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 6px 10px;
  border: 1px solid #ddd;
  border-radius: 6px;
  margin-bottom: 6px;
}

.remove {
  background: none;
  border: none;
  color: #b00;
  font-size: 18px;
  cursor: pointer;
}

.actions {
  display: flex;
  gap: 10px;
}

.error {
  color: #b00;
}
</style>
