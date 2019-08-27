import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    games: {},
    singleGame: {},
    leaderBoard: {}
  },
  mutations: {
    GET_GAMES: (state, data) => {
      state.games = data
    },
    GET_SINGLEGAME: (state, data) => {
      state.singleGame = data
    },
    GET_LEADERBOARD: (state, data) => {
      state.leaderBoard = data
    }
  },
  actions: {
    getGames(context) {
      axios.get("/api/games").then(json => {
        let games = json.data;
        context.commit("GET_GAMES", games)
      });
    },
    getSingleGame(context, id) {
      axios.get(`/api/game_view/${id}`).then(json => {
        let singleGame = json.data;
        context.commit("GET_SINGLEGAME", singleGame)
      })
    },
    getLeaderBoard(context) {
      axios.get("/api/leader_board").then(json => {
        let leaderBoard = json.data;
        context.commit("GET_LEADERBOARD", leaderBoard)
      });
    },
  }
})
