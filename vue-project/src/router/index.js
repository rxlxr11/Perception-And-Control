import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/perception',
    name: 'perception',
    component: () => import(/* webpackChunkName: "about" */ '../views/PerAndCon/Perception.vue')
  },
  {
    path: '/control',
    name: 'control',
    component: () => import(/* webpackChunkName: "about" */ '../views/PerAndCon/Control.vue')
  },
  {
    path: '/',
    redirect: '/perception'
  }
]

const router = new VueRouter({
  routes
})

export default router
