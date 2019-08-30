<template>
  <v-app>
    <navBar></navBar>
    <v-content>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>
import navBar from "@/components/navBar.vue";
export default {
  name: "App",
  components: {
    navBar
  },
  data: () => ({
    //
  })
};
</script>
