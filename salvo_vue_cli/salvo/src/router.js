import Vue from 'vue'
import Router from 'vue-router'
import Home from "./views/Home.vue"
import Game from './views/Game.vue'
import LogIn from './views/LogIn.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/Game/:id',
      props: true,
      name: 'game',
      component: Game
    },
    {
      path: '/LogIn',
      name: 'logIn',
      component: LogIn
    }
  ]
})
