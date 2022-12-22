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
      <div style="text-align: center">
        <!-- <v-container> -->
          <v-img
          v-if="pic_show != null"
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
          accept="image/png, image/jpeg, image/webp, image/jpg"
          style="margin-top: 3%; margin-bottom: 3%"
          @change="selectImages"
        />

        <v-btn
          depressed
          color="success"
          class="mr-3 ml-2"
          @click="updatepicture"
        >
          Update
        </v-btn>
        <div style="position: relative; ">
          <p
            class="text-h4"
            style="display: inline-flex; overflow-wrap: break-word; "
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
        <div style="position: relative">
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
        <div style="position: relative">
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
        </div>
        <br />
        <div style="position: relative">
          <p
            class="text-h4"
            style="display: inline-block; overflow-wrap: break-word"
            v-if="!editToggleBranch"
          >
            {{ branch }}
          </p>
          <v-text-field solo v-model="editBranch" v-else></v-text-field>
          <v-img
            contain
            src="../../assets/Edit.png"
            class="imgicon"
            v-if="!editToggleBranch"
            @click="clickToEditBranch"
          ></v-img>
          <template v-else>
            <v-btn
              depressed
              color="success"
              class="mr-3 ml-2"
              @click="updateBranch"
            >
              Update
            </v-btn>
            <v-btn depressed color="error" @click="editToggleBranch = false">
              Cancle
            </v-btn>
          </template>
        </div>
        <br />
        <div style="position: relative">
          <p
            class="text-h4"
            style="display: inline-block; overflow-wrap: break-word"
            v-if="!editToggleDescription"
          >
            {{ description }}
          </p>
          <v-text-field solo v-model="editDescription" v-else></v-text-field>
          <v-img
            contain
            src="../../assets/Edit.png"
            class="imgicon"
            v-if="!editToggleDescription"
            @click="clickToEditDescription"
          ></v-img>
          <template v-else>
            <v-btn
              depressed
              color="success"
              class="mr-3 ml-2"
              @click="updateDescription"
            >
              Update
            </v-btn>
            <v-btn
              depressed
              color="error"
              @click="editToggleDescription = false"
            >
              Cancle
            </v-btn>
          </template>
        </div>
        <!-- </v-container> -->
      </div>
    </v-content>
  </div>
</template>
<style>
.imguser {
  left: 40%;
  border-radius: 100%;
  background-repeat: no-repeat;
  background-size: contain;
  width: 300px;
  height: 300px;
  margin-top: 5%;
}

.imguser1 {
  left: 35%;
  border-radius: 100%;
  background-repeat: no-repeat;
  background-size: contain;
  width: 30%;
  height: 30%;
}

.imgicon {
  width: 5%;
  display: inline-flex;
  cursor: pointer;
  position: absolute;
  bottom: -30%;
}

.v-application p {
  margin: 0;
}

.v-text-field.v-text-field--enclosed {
  width: 20%;
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
export default {
  //   name: "login",
  data() {
    return {
      items: [
        { title: "Appointments", icon: "mdi-calendar-clock", path: "/doctor" },
        {
          title: "Update Information",
          icon: "mdi-card-account-details",
          path: "/profiledoctor",
        },
      ],
      id: "",
      fullname: "",
      email: "",
      branch: "",
      description: "",
      gender: "",
      editFullname: "",
      editEmail: "",
      pic:'',
      editGender: "",
      editBranch: "",
      pic_show:'',
      editDescription: "",
      editToggleFullname: false,
      editToggleEmail: false,
      editToggleGender: false,
      editToggleBranch: false,
      editToggleDescription: false,
      GenderForSelect: ["Male", "Female", "Other"],
    };
  },

  mounted() {
    const data = JSON.parse(localStorage.getItem("userData"));

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
        this.branch = response.data.branch;
        this.description = response.data.description;
      })
      .catch((err) => {
        console.log(err);
      });

  },

  methods: {
    selectImages(event) {
      this.pic = event.target.files[0];
    },
    updatepicture() {
    
      let formData = new FormData();
      formData.append("file", this.pic);
      formData.append("id", this.id);
      formData.append("name", this.fullname);
      formData.append("email", this.email);
      formData.append("gender", this.gender);
      formData.append("branch", this.branch);
      formData.append("description", this.description);

      axios
        .put("http://localhost:3002/users/update/doctor", formData)
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

    clickToEditFullname() {
      this.editToggleFullname = true;
      this.editFullname = this.fullname;
    },

    updateFullname() {
      this.editToggleFullname = false;
      this.fullname = this.editFullname;let formData = new FormData();
      formData.append("file", this.pic);
      formData.append("id", this.id);
      formData.append("name", this.fullname);
      formData.append("email", this.email);
      formData.append("gender", this.gender);
      formData.append("branch", this.branch);
      formData.append("description", this.description);
      axios
        .put("http://localhost:3002/users/update/doctor", formData)
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
      formData.append("branch", this.branch);
      formData.append("description", this.description);
      axios
      .put("http://localhost:3002/users/update/doctor", formData)
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
      formData.append("branch", this.branch);
      formData.append("description", this.description);
      axios
      .put("http://localhost:3002/users/update/doctor", formData)
        .then((response) => {
          console.log(response.data);
        })
        .catch((err) => {
          console.log(err);
        });
      this.editGender = "";
    },

    clickToEditBranch() {
      this.editToggleBranch = true;
      this.editBranch = this.branch;
    },

    updateBranch() {
      this.editToggleBranch = false;
      this.branch = this.editBranch;
      let formData = new FormData();
      formData.append("file", this.pic);
      formData.append("id", this.id);
      formData.append("name", this.fullname);
      formData.append("email", this.email);
      formData.append("gender", this.gender);
      formData.append("branch", this.branch);
      formData.append("description", this.description);
      axios
      .put("http://localhost:3002/users/update/doctor", formData)
        .then((response) => {
          console.log(response.data);
        })
        .catch((err) => {
          console.log(err);
        });
      this.editBranch = "";
    },

    clickToEditDescription() {
      this.editToggleDescription = true;
      this.editDescription = this.description;
    },

    updateDescription() {
      this.editToggleDescription = false;
      this.description = this.editDescription;
      let formData = new FormData();
      formData.append("file", this.pic);
      formData.append("id", this.id);
      formData.append("name", this.fullname);
      formData.append("email", this.email);
      formData.append("gender", this.gender);
      formData.append("branch", this.branch);
      formData.append("description", this.description);
      axios
      .put("http://localhost:3002/users/update/doctor", formData)
        .then((response) => {
          console.log(response.data);
        })
        .catch((err) => {
          console.log(err);
        });
      this.editDescription = "";
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
