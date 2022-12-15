<template>
  <v-app>
    <v-toolbar dense color="#324B4C" elevation="4" prominent>
      <router-link to="/">
        <v-img
          max-height="100"
          max-width="130"
          src="../assets/HospitalImages.png"
        ></v-img>
      </router-link>
      <v-spacer></v-spacer>
      <v-img
        style="margin-right: 30px; align-self: center"
        max-height="100"
        max-width="70"
        src="../assets/ProfileIcon.png"
      ></v-img>
      <v-btn class="btn" elevation="3" rounded large color="#E63946"
        >logout</v-btn
      >
    </v-toolbar>
  </v-app>
</template>
<style>
.btn {
  color: white !important;
  align-self: center;
}
.v-application--wrap {
  min-height: 0vh !important;
}
</style>
<script>
export default {
  name: "NavBar",
};
</script>
