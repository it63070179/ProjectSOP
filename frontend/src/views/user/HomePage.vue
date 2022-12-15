<template>
  <v-app>
    <!-- <NavBar /> -->
    <v-container>
      <h1 class="titledoc">เลือกหมอที่ต้องการจอง</h1>
      <v-row dense>
        <v-col sm="6">
          <v-dialog
            v-model="removeTagDialogs[doctors.doc_id]"
            persistent
            max-width="600px"
            v-for="doctors in detailindoctor"
            :key="doctors.doc_id"
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
              <v-img height="400" :src="doctors.image"></v-img>
              <v-card-text
                style="font-size: 26px; font-weight: bold; color: blue"
                >Name: {{ doctors.name }}
              </v-card-text>
              <v-card-text
                style="font-size: 26px; font-weight: bold; color: green"
                >Branch: {{ doctors.branch }}</v-card-text
              >
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  color="blue darken-1"
                  text
                  @click="removeTagDialogs[doctors.doc_id] = false"
                >
                  Close
                </v-btn>
                <v-btn
                  color="blue darken-1"
                  text
                  @click="removeTagDialogs[doctors.doc_id] = false"
                >
                  Save
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
// import NavBar from "../components/NavBar";
export default {
  //   components: {
  //     NavBar,
  //   },
  data() {
    return {
      removeTagDialogs: {},
      dialog: false,
      detailindoctor: [
        {
          doc_id: 1,
          name: "BK",
          branch: "neurologist",
          image: require("../assets/Hospital.png"),
        },
        {
          doc_id: 2,
          name: "Sin",
          branch: "Cardiologists",
          image: require("../assets/logo.png"),
        },
        { doc_id: 3, name: "Bank", branch: "Gastroenterologists" },
        {
          doc_id: 4,
          name: "Tee",
          branch: "Dermatologists",
          image: require("../assets/logo.png"),
        },
      ],
    };
  },
  methods: {
    removeResearchTag(name, id) {
      this.tags = this.tags.filter((t) => t.id !== id);
    },
  },
};
</script>
