<template>
  <div>
    <h1>SALVO!!</h1>
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
  },
  computed: {
    games() {
      return this.$store.state.games;
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

