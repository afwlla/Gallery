import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/pages/TheHome.vue'
import Login from '@/pages/TheLogin.vue'

const routes = [
  { path: '/', component: Home },
  { path: '/login', component: Login }
]
const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
