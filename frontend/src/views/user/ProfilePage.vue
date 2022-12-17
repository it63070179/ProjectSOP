<template>
  <div>
    <v-container>
      <v-row>
        <v-col
          ><h1 class="white--text text-h3" style="margin-bottom: 3%">
            Profile
          </h1></v-col
        >
      </v-row>
      <v-row no-gutters>
        <v-col cols="5">
          <v-img class="imguser" src="../assets/logo.png"></v-img>
          <span class="text-h4 mb-8" v-if="!editToggleFullname">
            {{ fullname }}</span
          >
          <v-text-field solo v-model="editFullname" v-else></v-text-field>
          <v-img
            contain
            src="../assets/Edit.png"
            class="imgicon"
            v-if="!editToggleFullname"
            @click="clickToEditFullname"
          ></v-img>
          <template v-else>
            <v-btn
              depressed
              color="success"
              class="mr-3 ml-2"
              @click="updateFullname"
            >
              Update
            </v-btn>
            <v-btn depressed color="error" @click="editToggleFullname = false">
              Cancle
            </v-btn>
          </template>
          <br />
          <span class="text-h4 mb-8" v-if="!editToggleEmail">{{ email }}</span>
          <v-text-field solo v-model="editEmail" v-else></v-text-field>
          <v-img
            contain
            src="../assets/Edit.png"
            class="imgicon"
            v-if="!editToggleEmail"
            @click="clickToEditEmail"
          ></v-img>
          <template v-else>
            <v-btn
              depressed
              color="success"
              class="mr-3 ml-2"
              @click="updateEmail"
            >
              Update
            </v-btn>
            <v-btn depressed color="error" @click="editToggleEmail = false">
              Cancle
            </v-btn>
          </template>
          <br />
          <span class="text-h4 mb-8" v-if="!editToggleGender">{{
            gender
          }}</span>
          <v-select
            solo
            :items="GenderForSelect"
            v-model="editGender"
            v-else
          ></v-select>
          <v-img
            contain
            src="../assets/Edit.png"
            class="imgicon"
            v-if="!editToggleGender"
            @click="clickToEditGender"
          ></v-img>
          <template v-else>
            <v-btn
              depressed
              color="success"
              class="mr-3 ml-2"
              @click="updateGender"
            >
              Update
            </v-btn>
            <v-btn depressed color="error" @click="editToggleGender = false">
              Cancle
            </v-btn>
          </template></v-col
        >
        <v-col cols="7">
          <v-content>
            <div class="p-5">
              <v-card>
                <v-card-title>
                  <v-text-field
                    append-icon="mdi-magnify"
                    label="Search"
                    single-line
                    hide-details
                  ></v-text-field>
                </v-card-title>
                <v-data-table
                  :headers="headers"
                  :items="admins"
                  :search="search"
                ></v-data-table>
              </v-card></div></v-content></v-col
      ></v-row>
    </v-container>
  </div>
</template>
<style>
.v-application--wrap {
  background-color: #a8dadc;
}

.imguser {
  right: 20%;
  border-radius: 100%;
  background-repeat: no-repeat;
  background-size: contain;
}

.imgicon {
  width: 16%;
  display: inline-block;
  position: relative;
  top: 3%;
  cursor: pointer;
}

.v-text-field.v-text-field--enclosed {
  width: 50%;
  margin-top: 10%;
}

.v-input {
  display: inline-flex;
}

.v-input__slot {
  margin-bottom: 0;
}

.v-text-field.v-text-field--enclosed .v-text-field__details {
  margin-bottom: 0;
}
</style>
<script>
export default {
  //   name: "login",
  data() {
    return {
      fullname: "BANK LEK",
      email: "EmailnaJa@email.com",
      gender: "Male",
      editFullname: "",
      editEmail: "",
      editGender: "",
      editToggleFullname: false,
      editToggleEmail: false,
      editToggleGender: false,
      GenderForSelect: ["Male", "Female", "Other"],
      result: "",
      search: "",
      headers: [
        {
          text: "ID",
          align: "start",
          filterable: false,
          value: "id",
        },
        { text: "Doctor name", value: "DoctorName" },
        { text: "Date", value: "Date" },
        { text: "Time", value: "Time" },
        { text: "Description", value: "Description" },
      ],
      admins: [
        {
          id: 1,
          DoctorName: "SIN",
          Date: "10-12-2022",
          Time: "13.00",
          Description: "Fever headache",
        },
        {
          id: 2,
          DoctorName: "BANK",
          Date: "11-12-2022",
          Time: "15.00",
          Description: "Cancer",
        },
        {
          id: 3,
          DoctorName: "BANK",
          Date: "11-12-2022",
          Time: "15.00",
          Description: "Cancer",
        },
        {
          id: 4,
          DoctorName: "BANK",
          Date: "11-12-2022",
          Time: "15.00",
          Description: "Cancer",
        },
        {
          id: 5,
          DoctorName: "BANK",
          Date: "11-12-2022",
          Time: "15.00",
          Description: "Cancer",
        },
        {
          id: 6,
          DoctorName: "BANK",
          Date: "11-12-2022",
          Time: "15.00",
          Description: "Cancer",
        },
        {
          id: 7,
          DoctorName: "BANK",
          Date: "11-12-2022",
          Time: "15.00",
          Description: "Cancer",
        },
        {
          id: 8,
          DoctorName: "BANK",
          Date: "11-12-2022",
          Time: "15.00",
          Description: "Cancer",
        },
        {
          id: 9,
          DoctorName: "BANK",
          Date: "11-12-2022",
          Time: "15.00",
          Description: "Cancer",
        },
      ],
    };
  },
  methods: {
    clickToEditFullname() {
      this.editToggleFullname = true;
      this.editFullname = this.fullname;
    },

    updateFullname() {
      this.editToggleFullname = false;
      this.fullname = this.editFullname;
      this.editFullname = "";
    },

    clickToEditEmail() {
      this.editToggleEmail = true;
      this.editEmail = this.email;
    },

    updateEmail() {
      this.editToggleEmail = false;
      this.email = this.editEmail;
      this.editEmail = "";
    },

    clickToEditGender() {
      this.editToggleGender = true;
      this.editGender = this.gender;
    },

    updateGender() {
      this.editToggleGender = false;
      this.gender = this.editGender;
      this.editGender = "";
    },
  },
};
</script>
