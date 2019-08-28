<template>
  <div>
    <h1>THIS IS THE HOME PAGE</h1>
    <input v-model="name" placeholder="E-Mail" />
    <input v-model="pwd" placeholder="Password" />
    <v-btn v-on:click="getLogIn" block color="#009688" large dark>LOG IN</v-btn>
  </div>
</template>

<script>
export default {
  data() {
    return {
      name: "",
      pwd: ""
    };
  },
  created() {},
  methods: {
    getLogIn() {
      var ourData = {
        name: this.name,
        pwd: this.pwd
      };
      fetch("/api/login", {
        credentials: "include",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded"
        },
        method: "POST",
        body: this.getBody(ourData)
      })
        .then(function(data) {
          return data.json();
        })
        .then(function(json) {
          console.log("Request success: ", json);
        })
        .catch(function(error) {
          console.log("Request failure: ", error);
        });
    },
    getBody(json) {
      var body = [];
      for (var key in json) {
        var encKey = encodeURIComponent(key);
        var encVal = encodeURIComponent(json[key]);
        body.push(encKey + "=" + encVal);
      }
      return body.join("&");
    }
  }
};
</script>

<style>
</style>
