<template>
  <div>
    <v-app-bar class="flex-grow-0" app dark>
      <v-app-bar-title>Appointments</v-app-bar-title>
    </v-app-bar>
    <v-navigation-drawer app dark>
      <v-divider style="margin-top: 25%"></v-divider>
      <v-list dense nav>
        <v-list-item
          v-for="item in items"
          :to="item.path"
          :key="item.title"
          link
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <template v-slot:append>
        <div class="pa-2" @click="logout()">
          <v-btn block light>Logout</v-btn>
        </div>
      </template>
    </v-navigation-drawer>

    <v-content>
      <div class="p-5">
        <v-card>
          <v-card-title>
            <v-text-field
              v-model="search"
              append-icon="mdi-magnify"
              label="Search"
              single-line
              hide-details
            ></v-text-field>
          </v-card-title>
          <v-data-table
            :headers="headers"
            :items="appointments"
            :search="search"
          ></v-data-table>
        </v-card>
      </div>
    </v-content>
  </div>
</template>
<script>
// import axios from "axios";
export default {
  name: "doctor-appointment",
  data() {
    return {
      result: "",
      items: [
        { title: "Appointments", icon: "mdi-calendar-clock", path: "/doctor" },
        {
          title: "Update Information",
          icon: "mdi-card-account-details",
          path: "/profiledoctor",
        },
      ],
      search: "",
      headers: [
        {
          text: "ID",
          align: "start",
          filterable: false,
          value: "id",
        },
        { text: "Full Name", value: "fullName" },
        { text: "Email", value: "email" },
        { text: "Date", value: "date" },
        { text: "Time", value: "time" },
        { text: "Description", value: "description" },
      ],
      appointments: [
        {
          id: 1,
          fullName: "User1 Lastname",
          email: "user1@gmail.com",
          date: "15/12/2022",
          time: "12.00",
          description: "headache",
        },
        {
          id: 2,
          fullName: "User2 Lastname",
          email: "user2@gmail.com",
          date: "15/12/2022",
          time: "13.00",
          description: "stomachache",
        },
      ],
    };
  },

  mounted() {
    const data = JSON.parse(localStorage.getItem("userData"));
    console.log("data: ", data);
  },

  methods: {
    logout() {
      localStorage.removeItem("userData");
      const backgroundElement = document.getElementsByClassName(
        "v-application--wrap"
      );
      backgroundElement[0].style.backgroundColor = "#a8dadc";
      backgroundElement[0].style.justifyContent = "center";
      backgroundElement[0].style.alignItems = "center";
      this.$router.push({
        name: "LoginPage",
      });
    },
  },
};
</script>
