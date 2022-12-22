<template>
  <div>
    <v-app-bar class="flex-grow-0" app dark>
      <v-app-bar-title>Doctor Details</v-app-bar-title>
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
            :items="doctors"
            :search="search"
          ></v-data-table>
        </v-card>
      </div>
    </v-content>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "admin-doctorDetails",
  data() {
    return {
      result: "",
      items: [
        {
          title: "User Details",
          icon: "mdi-account-circle",
          path: "/userDetails",
        },
        {
          title: "Appointments",
          icon: "mdi-calendar-clock",
          path: "/appointments",
        },
        {
          title: "Dotor Details",
          icon: "mdi-card-account-details",
          path: "/doctorDetails",
        },
        {
          title: "Admin Details",
          icon: "mdi-card-account-details-star",
          path: "/adminDetails",
        },
        {
          title: "Add Doctor",
          icon: "mdi-account-plus-outline",
          path: "/addDoctor",
        },
        { title: "Add Admin", icon: "mdi-account-plus", path: "/addAdmin" },
      ],
      search: "",
      headers: [
        { text: "Name", align: "start", filterable: false, value: "name" },
        { text: "Email", value: "email" },
        { text: "Gender", value: "gender" },
        { text: "Description", value: "description" },
      ],
      doctors: [
        {
          id: 1,
          name: "Doctor1",
          email: "doctor1@gmail.com",
          gender: "male",
          description: "detail",
        },
        {
          id: 2,
          name: "Doctor2",
          email: "doctor2@gmail.com",
          gender: "female",
          description: "detail",
        },
      ],
    };
  },
  mounted() {
    this.getUsers();
  },
  methods: {
    getUsers() {
      axios
        .get(`http://localhost:3000/users`)
        .then((response) => {
          let filterData = response.data.filter(
            (item) => item.role == "doctor"
          );
          this.doctors = filterData;
        })
        .catch((error) => {
          console.log(error);
        });
    },
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
