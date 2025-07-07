import { createApp } from 'vue'
import App from './App.vue'
import route from './routers/route.js'
import './assets/base.css'

const app = createApp(App);
app.use(route);
app.mount('#app');

