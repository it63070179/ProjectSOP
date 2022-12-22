<template>
  <div style="min-height: 100vh">
    <NavBar />
    <!-- <v-container> -->
    <v-row class="ma-auto">
      <v-col
        ><h1
          class="white--text text-h3"
          style="margin-bottom: 3%; padding-left: 5%"
        >
          Profile
        </h1></v-col
      >
    </v-row>
    <v-row no-gutters class="ma-auto">
      <v-col cols="5" class="pa-5">
        <v-img
          v-if="pic_show != ''"
          class="imguser"
          :src="'http://localhost:3002/' + pic_show"
        ></v-img>
        <v-img
          v-if="pic_show == null"
          class="imguser1"
          src="../../assets/ProfileIcon.png"
        ></v-img>
        <input
          class="file-input"
          type="file"
          name="resume"
          accept="image/png, image/jpeg, image/webp, image/jpg "
          @change="selectImages"
          style="margin-bottom: 3%"
        />

        <v-btn
          depressed
          color="success"
          class="mr-3 ml-2"
          @click="updatepicture"
        >
          Update
        </v-btn>
        <div style="position: relative; left: 10%">
          <p
            class="text-h4"
            style="display: inline-block; overflow-wrap: break-word"
            v-if="!editToggleFullname"
          >
            {{ fullname }}
          </p>
          <v-text-field solo v-model="editFullname" v-else></v-text-field>
          <v-img
            contain
            src="../../assets/Edit.png"
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
        </div>
        <br />
        <div style="position: relative; left: 10%">
          <p
            class="text-h4"
            style="display: inline-block; overflow-wrap: break-word"
            v-if="!editToggleEmail"
          >
            {{ email }}
          </p>
          <v-text-field solo v-model="editEmail" v-else></v-text-field>
          <v-img
            contain
            src="../../assets/Edit.png"
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
        </div>
        <br />
        <div style="position: relative; left: 10%">
          <p
            class="text-h4"
            style="display: inline-block; overflow-wrap: break-word"
            v-if="!editToggleGender"
          >
            {{ gender }}
          </p>
          <v-select
            solo
            :items="GenderForSelect"
            v-model="editGender"
            v-else
          ></v-select>
          <v-img
            contain
            src="../../assets/Edit.png"
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
          </template>
        </div></v-col
      >
      <v-col cols="7" class="pa-5">
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
    <!-- </v-container> -->
  </div>
</template>
<style>
.v-application--wrap {
  background-color: #a8dadc;
}

.imguser {
  border-radius: 100%;
  background-repeat: no-repeat;
  background-size: contain;
  width: 50%;
  height: 50%;
  margin-left: 15%;
  margin-bottom: 10%;
}

.imguser1 {
  border-radius: 100%;
  background-repeat: no-repeat;
  background-size: contain;
  width: 50%;
  margin-left: 15%;
  margin-bottom: 10%;
}

.imgicon {
  width: 12%;
  display: inline-block;
  position: absolute;
  bottom: -10%;
  cursor: pointer;
}

.v-text-field.v-text-field--enclosed {
  width: 40%;
  /* margin-top: 10%; */
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
import axios from "axios";
import NavBar from "../../components/NavBar";
export default {
  //   name: "login",
  components: {
    NavBar,
  },
  data() {
    return {
      id: "",
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
      pic_show: "",
      pic: "",
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

  mounted() {
    const data = JSON.parse(localStorage.getItem("userData"));
    // console.log("data: ", data);
    axios
      .get("http://localhost:3000/users/" + data.username + "/" + data.password)
      .then((response) => {
        console.log(response.data);
        this.id = response.data.id;
        this.fullname = response.data.name;
        this.email = response.data.email;
        this.gender = response.data.gender;
        this.pic_show = response.data.picture;
        this.pic = response.data.picture;
      })
      .catch((err) => {
        console.log(err);
      });
  },

  methods: {
    selectImages(event) {
      this.pic = event.target.files[0];
    },
    clickToEditFullname() {
      this.editToggleFullname = true;
      this.editFullname = this.fullname;
    },

    updatepicture() {
      let formData = new FormData();
      formData.append("file", this.pic);
      formData.append("id", this.id);
      formData.append("name", this.fullname);
      formData.append("email", this.email);
      formData.append("gender", this.gender);

      axios
        .put("http://localhost:3002/users/update", formData)
        .then((response) => {
          const data = JSON.parse(localStorage.getItem("userData"));
          console.log(data)
          axios
            .get(
              "http://localhost:3000/users/" +
                data.username +
                "/" +
                data.password
            )
            .then((response) => {
              console.log(response.data);
              this.pic_show = response.data.picture;
            })
            .catch((err) => {
              console.log(err);
            });
          console.log(response.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    updateFullname() {
      this.editToggleFullname = false;
      this.fullname = this.editFullname;

      let formData = new FormData();
      formData.append("file", this.pic);
      formData.append("id", this.id);
      formData.append("name", this.fullname);
      formData.append("email", this.email);
      formData.append("gender", this.gender);
      axios
        .put("http://localhost:3002/users/update", formData)
        .then((response) => {
          console.log(response.data);
        })
        .catch((err) => {
          console.log(err);
        });
      this.editFullname = "";
    },

    clickToEditEmail() {
      this.editToggleEmail = true;
      this.editEmail = this.email;
    },

    updateEmail() {
      this.editToggleEmail = false;
      this.email = this.editEmail;
      let formData = new FormData();
      formData.append("file", this.pic);
      formData.append("id", this.id);
      formData.append("name", this.fullname);
      formData.append("email", this.email);
      formData.append("gender", this.gender);
      axios
        .put("http://localhost:3002/users/update", formData)
        .then((response) => {
          console.log(response.data);
        })
        .catch((err) => {
          console.log(err);
        });
      this.editEmail = "";
    },

    clickToEditGender() {
      this.editToggleGender = true;
      this.editGender = this.gender;
    },

    updateGender() {
      this.editToggleGender = false;
      this.gender = this.editGender;
      let formData = new FormData();
      formData.append("file", this.pic);
      formData.append("id", this.id);
      formData.append("name", this.fullname);
      formData.append("email", this.email);
      formData.append("gender", this.gender);
      axios
        .put("http://localhost:3002/users/update", formData)
        .then((response) => {
          console.log(response.data);
        })
        .catch((err) => {
          console.log(err);
        });
      this.editGender = "";
    },
  },
};
</script>
