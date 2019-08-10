<template>
  <div>
    <h1>Ship locations!</h1>
    <table class="singleGameTable">
      <tbody>
        <tr v-for="(row, r) in rows" :key="r">
          <td v-for="(col, c) in colums" :id="row + col" :key="c">
            <span v-if="c == 0 && r == 0"></span>
            <span v-else-if="c == 0">{{row}}</span>
            <span v-else>{{row + col}}</span>
          </td>
        </tr>
      </tbody>
    </table>
    <ul>
      <li
        v-for="(ship, index) in singleGame.ships"
        :key="index"
      >{{index}} {{ship.type}} {{ship.location}}</li>
    </ul>
  </div>
</template>

<script>
export default {
  props: ["id"],
  data() {
    return {
      colums: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
      rows: ["", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"]
    };
  },
  methods: {},
  created() {
    this.$store.dispatch("getSingleGame", this.id);
  },
  computed: {
    singleGame() {
      return this.$store.state.singleGame;
    }
  }
};
</script>

<style scoped>
.singleGameTable {
  margin: auto;
  /* margin-left: 30px; */
  border-spacing: 1px;
  text-align: center;
  border-collapse: collapse;
}
td {
  height: 40px;
  width: 40px;
  border-style: solid;
  border-width: 1.5px;
}
/* td:first-child {
  border-style: none;
} */
/* tr:first-child {
  border-style: none;
} */
</style>
