import Vue from 'vue'
import Router from 'vue-router'
import Home from "./views/Home.vue"
import Games from './views/Games.vue'
import Game from './views/Game.vue'

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
      path: '/Games',
      name: 'games',
      component: Games
    },
    {
      path: '/Game/:id',
      props: true,
      name: 'game',
      component: Game
    }
  ]
})
