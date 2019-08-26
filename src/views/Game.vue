<template>
  <div>
    <h1>Ship locations!</h1>
    <h2 v-if="singleGame.gamePlayers">
      {{singleGame.gamePlayers[0].player.email}}
      <span
        v-if="id == singleGame.gamePlayers[0].id"
      >{{"(YOU)"}}</span>
      {{"VS "}}{{singleGame.gamePlayers[1].player.email}}
      <span
        v-if="id == singleGame.gamePlayers[1].id"
      >{{"(YOU)"}}</span>
    </h2>
    <ul>
      <li v-for="(ship, index) in singleGame.ships" :key="index">{{ship.type}} {{ship.location}}</li>
    </ul>
    <div class="flex">
      <div>
        <p>Ship Grid</p>
        <table class="singleGameTable">
          <tbody>
            <tr v-for="(row, r) in rows" :key="r">
              <td v-for="(col, c) in colums" :id="row + col + 'my'" :key="c">
                <span v-if="c == 0 && r == 0"></span>
                <span v-else-if="c == 0">{{row}}</span>
                <span v-else>{{row + col}}</span>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div>
        <p>Salvo Grid</p>
        <table class="singleGameTable">
          <tbody>
            <tr v-for="(row, r) in rows" :key="r">
              <td v-for="(col, c) in colums" :id="row + col + 'opp'" :key="c">
                <span v-if="c == 0 && r == 0"></span>
                <span v-else-if="c == 0">{{row}}</span>
                <span v-else>{{row + col}}</span>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
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
  methods: {
    paintShips() {
      for (let index in this.ships) {
        var ship = this.ships[index];
        for (let index2 in ship.location) {
          var location = ship.location[index2];
          var loc = document.getElementById(location + "my");
          loc.setAttribute("class", ship.type);
        }
      }
    },
    paintSalvoes(salvos, table) {
      let keys = Object.keys(salvos);
      for (let index in keys) {
        var salvo = salvos[index];
        for (let index2 in salvo) {
          var location = salvo[index2];
          var loc = document.getElementById(location + table);
          loc.classList.add("salvo");
        }
      }
    }
  },
  created() {
    this.$store.dispatch("getSingleGame", this.id);
  },
  watch: {
    ships: function() {
      this.paintShips();
    },
    mySalvoes: function() {
      this.paintSalvoes(this.mySalvoes, "opp");
    },
    oppSalvoes: function() {
      this.paintSalvoes(this.oppSalvoes, "my");
    }
  },

  computed: {
    singleGame() {
      return this.$store.state.singleGame;
    },
    oppId() {
      for (var i = 0; this.singleGame.gamePlayers.length; i++) {
        if (this.singleGame.gamePlayers[i].id != this.id) {
          return this.singleGame.gamePlayers[i].id;
        }
      }
    },
    ships() {
      return this.singleGame.ships;
    },
    mySalvoes() {
      if (this.singleGame.salvos) {
        if (this.singleGame.salvos[0].hasOwnProperty(this.id)) {
          return this.singleGame.salvos[0][this.id];
        }
        return this.singleGame.salvos[1][this.id];
      }
    },
    oppSalvoes() {
      if (this.singleGame.salvos) {
        if (!this.singleGame.salvos[0].hasOwnProperty(this.id)) {
          return this.singleGame.salvos[0][this.oppId];
        }
        return this.singleGame.salvos[1][this.oppId];
      }
    }
  }
};
</script>

<style scoped>
.singleGameTable {
  border-spacing: 1px;
  text-align: center;
  border-collapse: collapse;
}
.flex {
  padding-top: 30px;
  display: flex;
  justify-content: space-around;
}
td {
  height: 40px;
  width: 40px;
  border-style: solid;
  border-width: 1.5px;
}
.Patrol {
  background-color: green;
}
.Destroyer {
  background-color: red;
}
.Submarine {
  background-color: blue;
}
.Patrol.salvo,
.Destroyer.salvo,
.Submarine.salvo {
  background-color: transparent;
  background-image: URL("/Explosion-155624_icon.svg");
}
.salvo {
  background-image: URL("/C_Sea-512.png");
  background-size: cover;
}
tr:first-child > td {
  border: none;
  font-weight: bold;
}
td:first-child {
  border: none;
  font-weight: bold;
}
</style>
