<template>
  <div>
    <h1>SALVO!!</h1>
    <h2>List of Games</h2>
    <v-simple-table class="gamesTable" fixed-header>
      <thead>
        <tr>
          <th class="text-left">Game</th>
          <th class="text-left">Date</th>
          <th class="text-left">Player 1</th>
          <th class="text-left">Player 2</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(game, index) in games" :key="index">
          <td>{{ game.id }}</td>
          <td>{{ game.created | moment("dddd, MMMM Do YYYY, hh:mm:ss a") }}</td>
          <td>{{ game.gamePlayers[0].player.firstName }} {{ game.gamePlayers[0].player.lastName }}</td>
          <td
            v-if="game.gamePlayers.length > 1"
          >{{ game.gamePlayers[1].player.firstName }} {{ game.gamePlayers[1].player.lastName }}</td>
          <td v-else>Waiting for opponent!</td>
        </tr>
      </tbody>
    </v-simple-table>
    <h2>Leader Board</h2>
    <v-simple-table class="gamesTable" fixed-header>
      <thead>
        <tr>
          <th class="text-left">Player</th>
          <th class="text-left">Total Score</th>
          <th class="text-left">Wins</th>
          <th class="text-left">Losses</th>
          <th class="text-left">Ties</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(user, key) in leaderBoard" :key="key">
          <td>{{ user[0] }}</td>
          <td>{{ user[1].points }}</td>
          <td>{{ user[1].wins }}</td>
          <td>{{ user[1].loses }}</td>
          <td>{{ user[1].draws }}</td>
        </tr>
      </tbody>
    </v-simple-table>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {},
  created() {
    this.$store.dispatch("getGames");
    this.$store.dispatch("getLeaderBoard");
  },
  computed: {
    games() {
      return this.$store.state.games;
    },
    leaderBoard() {
      return Object.entries(this.$store.state.leaderBoard).sort((a, b) => {
        if (a[1].points < b[1].points) {
          return 1;
        }
        if (a[1].points > b[1].points) {
          return -1;
        } else {
          return 0;
        }
      });
    }
  }
};
</script>

<style>
.gamesTable {
  margin-left: 30px;
  margin-right: 30px;
}
</style>

