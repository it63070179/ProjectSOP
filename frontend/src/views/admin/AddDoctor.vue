<template>
  <div>
    <v-app-bar class="flex-grow-0" app dark>
      <v-app-bar-title>Add Doctor</v-app-bar-title>
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
              <v-col cols="4" style="margin-left: -100px; margin-top: 0.5%">
                <v-text-field v-model="v$.email.$model" dense></v-text-field>
              </v-col>
              <v-dialog v-model="dialog" persistent max-width="400">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="primary"
                    style="margin-top: 1.5%; margin-left: 5%"
                    v-bind="attrs"
                    v-on="on"
                    @click="addDoctor()"
                    >Add to doctor</v-btn
                  >
                </template>
                <v-card>
                  <v-card-title class="text-h5"> {{ text }} </v-card-title>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="green darken-1" text @click="dialog = false">
                      OK
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-row>
          </v-card-title>
          <template v-if="v$.email.$error">
            <p
              class="text-subtitle-2 red--text ml-10"
              v-if="v$.email.required.$invalid"
            >
              This field is requried
            </p>
            <p
              class="text-subtitle-2 red--text ml-10"
              v-else-if="v$.email.minLength.$invalid"
            >
              This field should be at least 3 characters long
            </p>
            <p
              class="text-subtitle-2 red--text ml-10"
              v-else-if="v$.email.ValidateEmail.$invalid"
            >
              This field is invalid email
            </p>
          </template>
        </v-card>
      </div>
    </v-content>
  </div>
</template>

<script>
import { useVuelidate } from "@vuelidate/core";
import { required, minLength } from "@vuelidate/validators";
import axios from "axios";
const ValidateEmail = (value) => {
  const ReduxEmail =
    /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  if (value.match(ReduxEmail)) {
    // console.log("Email Match");
    return true;
  } else {
    // console.log("Email Not Match");
    return false;
  }
};
export default {
  name: "admin-addDoctor",

  setup() {
    return {
      v$: useVuelidate(),
    };
  },

  data() {
    return {
      result: "",
      DataUserinDB: [],
      findEmailinDB: undefined,
      text: "",
      dialog: false,
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

  validations() {
    return {
      email: { required, minLength: minLength(3), ValidateEmail },
    };
  },

  mounted() {
    axios
      .get("http://localhost:3000/users")
      .then((response) => {
        // console.log(response.data);
        response.data.forEach((element) => {
          this.DataUserinDB.push(element);
        });
        console.log(this.DataUserinDB);
      })
      .catch((err) => {
        console.log(err);
      });
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

    addDoctor() {
      this.findEmailinDB = this.DataUserinDB.find(
        (element) => element.email === this.email
      );
      console.log(this.findEmailinDB);
      if (this.findEmailinDB === undefined) {
        this.text = "don't have email in system";
        console.log("don't have email in system");
        return;
      } else if (this.findEmailinDB.role === "doctor") {
        this.text = "this user is role doctor already";
        console.log("this user is role doctor already");
        return;
      } else if (this.findEmailinDB.role === "admin") {
        this.text = "this user is role admin already";
        console.log("this user is role admin already");
        return;
      }
      this.text = "Add Doctor Success";
      axios
        .put("http://localhost:3002/users/adddoctor", {
          email: this.email,
          role: "doctor",
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
