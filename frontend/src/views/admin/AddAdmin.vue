<template>
  <v-app>
    <v-app-bar class="flex-grow-0" app dark>
      <v-app-bar-title>Add Admin</v-app-bar-title>
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
            <v-row style="margin-top: 2%">
              <v-col cols="2">
                <v-subheader style="font-size: 20px">Email :</v-subheader>
              </v-col>
              <v-col cols="4" style="margin-left: -120px; margin-top: 0.5%">
                <v-text-field
                  v-model="email"
                  :rules="[rules.required, rules.email]"
                  dense
                ></v-text-field>
              </v-col>
              <v-btn
                color="primary"
                style="margin-top: 1.5%; margin-left: 5%"
                @click="addAdmin()"
                >Add to admin</v-btn
              >
            </v-row>
          </v-card-title>
        </v-card>
      </div>
    </v-content>
  </v-app>
</template>

<script>
import axios from "axios";
export default {
  name: "admin-addAdmin",
  data() {
    return {
      result: "",
      items: [
        { title: "User Details", icon: "mdi-account-circle", path: "/" },
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
      title: "Preliminary report",
      email: "",
      rules: {
        required: (value) => !!value || "Required.",
        counter: (value) => value.length <= 20 || "Max 20 characters",
        email: (value) => {
          const pattern =
            /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
          return pattern.test(value) || "Invalid e-mail.";
        },
      },
    };
  },
  methods: {
    logout() {
      localStorage.removeItem("userData");
      this.$router.push({
        name: "LoginPage",
      });
    },

    addAdmin() {
      axios
        .put("http://localhost:3002/users/addadmin", {
          email: this.email,
          role: "admin",
        })
        .then((response) => {
          console.log(response.data);
        })
        .catch((err) => {
          console.log(err);
        });
      this.email = "";
    },
  },
};
</script>
