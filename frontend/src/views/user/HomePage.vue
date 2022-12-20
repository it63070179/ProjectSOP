<template>
  <v-app>
    <NavBar />
    <v-container>
      <h1 class="titledoc">เลือกหมอที่ต้องการจอง</h1>
      <v-row dense>
        <v-col sm="6" v-for="doctors in detailindoctor" :key="doctors.doc_id">
          <v-dialog
            v-model="Dialogs[doctors.doc_id]"
            persistent
            max-width="900px"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-card
                class="mx-auto my-12"
                max-width="400"
                style="border: 5px solid black"
              >
                <v-img
                  height="400"
                  :src="doctors.image"
                  v-bind="attrs"
                  v-on="on"
                ></v-img>

                <v-card-text
                  style="font-size: 26px; font-weight: bold; color: blue"
                  >Name: {{ doctors.name }}
                </v-card-text>
                <v-card-text
                  style="font-size: 26px; font-weight: bold; color: green"
                  >Branch: {{ doctors.branch }}</v-card-text
                >
              </v-card>
            </template>
            <v-card>
              <v-img :src="doctors.image"></v-img>
              <v-card-text
                style="
                  font-size: 26px;
                  font-weight: bold;
                  color: blue;
                  text-align: center;
                "
                >Name: {{ doctors.name }}
              </v-card-text>
              <v-card-text
                style="
                  font-size: 26px;
                  font-weight: bold;
                  color: green;
                  text-align: center;
                "
                >Branch: {{ doctors.branch }}</v-card-text
              >
              <v-card-text style="font-size: 20px; font-weight: bold">{{
                doctors.description
              }}</v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  class="btn"
                  elevation="3"
                  rounded
                  large
                  color="#E63946"
                  @click="Dialogs[doctors.doc_id] = false"
                >
                  ย้อนกลับ
                </v-btn>

                <v-btn
                  style="margin-left: 30px"
                  class="btn"
                  v-bind="attrs"
                  v-on="on"
                  elevation="3"
                  rounded
                  x-large
                  color="#8539E6"
                  @click="appointmentDoctor(doctors.name)"
                >
                  จองหมอคนนี้
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>
<style>
body {
  background-color: #a8dadc;
}
.v-application--wrap {
  min-height: 0vh !important;
  background-color: #a8dadc;
}
.titledoc {
  text-align: center;
  font-size: 50px;
  font-weight: bold;
  color: white;
}
</style>
<script>
import NavBar from "../components/NavBar";
import axios from "axios";
export default {
  components: {
    NavBar,
  },
  data() {
    return {
      Dialogs: {},
      dialog: false,
      detailindoctor: [
        {
          doc_id: 1,
          name: "BK",
          branch: "neurologist",
          image: require("../assets/HospitalImages.png"),
          description: "asdasdasdasdasdasdasdasdasdasdsadada1",
        },
        {
          doc_id: 2,
          name: "Sin",
          branch: "Cardiologists",
          image: require("../assets/logo.png"),
          description: "asdasdasdasdasdasdasdasdasdasdsadada2",
        },
        {
          doc_id: 3,
          name: "Bank",
          branch: "Gastroenterologists",
          image: require("../assets/HospitalImages.png"),
          description: "asdasdasdasdasdasdasdasdasdasdsadada3",
        },
        {
          doc_id: 4,
          name: "Tee",
          branch: "Dermatologists",
          image: require("../assets/logo.png"),
          description: "asdasdasdasdasdasdasdasdasdasdsadada4",
        },
      ],
    };
  },
  mounted() {
    this.getAppointment();
  },
  methods: {
    getAppointment() {
      axios
        .get(`http://localhost:3000/appointmentUser/`)
        .then((response) => {
          console.log(response.data);
          this.users = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    appointmentDoctor(doctorname) {
      this.$router.push({
        name: "AppointmentUser",
        params: { doctorname: doctorname },
      });
    },
  },
};
</script>
