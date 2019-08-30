<template>
  <div>
    <v-toolbar dark>
      <v-toolbar-title>Salvo!</v-toolbar-title>
      <div class="flex-grow-1"></div>
      <v-toolbar-title v-if="this.$store.state.player != null">
        Logged in as:
        <span class="user">{{this.$store.state.player}}</span>
      </v-toolbar-title>
      <v-toolbar-items>
        <v-btn text v-bind:to="'/'">Home</v-btn>
        <v-btn v-if="this.$store.state.player == 'guest'" text v-bind:to="'/LogIn'">Log In</v-btn>
        <v-btn v-if="this.$store.state.player != 'guest'" text v-on:click="logOut">Log Out</v-btn>
      </v-toolbar-items>
    </v-toolbar>
  </div>
</template>
<script>
export default {
  methods: {
    logOut() {
      fetch("/api/logout", {
        credentials: "include",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded"
        },
        method: "POST"
      }).then(() => window.location.reload());
    }
  }
};
</script>
<style>
.v-toolbar__title {
  padding-right: 10px;
}
.user {
  color: rgb(106, 106, 243);
}
</style>