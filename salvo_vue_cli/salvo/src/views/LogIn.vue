<template>
  <div class="text">
    <h1>THIS IS THE LOGIN PAGE</h1>
    <v-form>
      <v-container>
        <v-row>
          <v-col cols="12" sm="6">
            <v-text-field v-model="name" label="E-Mail" outlined></v-text-field>
          </v-col>
          <v-col cols="12" sm="6">
            <v-text-field v-on:keyup.enter="getLogIn" v-model="pwd" label="Password" outlined></v-text-field>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
    <v-btn v-on:click="getLogIn" block large dark>LOG IN</v-btn>
    <div>
      <v-row justify="center">
        <v-dialog v-model="dialog" max-width="290">
          <v-card>
            <v-card-title class="headline">Wrong User Name or Password!</v-card-title>
            <v-card-actions>
              <div class="flex-grow-1"></div>
              <v-btn color="rgb(106, 106, 243)" text @click="dialog = false">OK</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      name: "j.bauer@ctu.gov",
      pwd: "24",
      dialog: false
    };
  },
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
        .then(data => {
          console.log("Request success: ", data);
          if (data.ok) {
            this.$router.push("/");
          } else {
            throw new Error(data.statusText);
          }
        })
        .catch(error => {
          this.dialog = true;
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
  },
  created() {}
};
</script>

<style>
</style>