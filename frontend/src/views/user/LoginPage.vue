<template>
  <div class="login">
    <img src="../assets/Hospital.png" class="icon_img" />
    <p class="text-body-1 labelOnTextField">Username</p>
    <v-text-field label="Username" solo v-model="v$.UserName.$model" />
    <template v-if="v$.UserName.$error">
      <p class="text-subtitle-2 red--text" v-if="v$.UserName.required.$invalid">
        This field is requried
      </p>
      <p
        class="text-subtitle-2 red--text"
        v-else-if="v$.UserName.minLength.$invalid"
      >
        This field should be at least 3 characters long
      </p>
    </template>
    <p class="text-body-1 labelOnTextField">Password</p>
    <v-text-field label="Password" solo v-model="v$.Password.$model" />
    <template v-if="v$.Password.$error">
      <p class="text-subtitle-2 red--text" v-if="v$.Password.required.$invalid">
        This field is requried
      </p>
      <p
        class="text-subtitle-2 red--text"
        v-else-if="v$.Password.minLength.$invalid"
      >
        This field should be at least 8 characters long
      </p>
    </template>
    <router-link to="/register" style="text-decoration: none">
      <p class="mb-6" style="color: #3447f5">register</p></router-link
    >
    <v-btn
      depressed
      color="#FB9C9C"
      style="margin-bottom: 50%; width: 40%; height: 8%"
      @click="login()"
      >Login</v-btn
    >
  </div>
</template>
<style>
.v-application--wrap {
  background-color: #a8dadc;
  justify-content: center;
  align-items: center;
}

.icon_img {
  width: 40%;
  margin-bottom: -2%;
}
.v-application p {
  margin-bottom: 10px;
}
.v-text-field {
  margin-top: 0;
}
.v-text-field.v-text-field--enclosed {
  width: 70%;
  margin-left: 20%;
}
.v-input__slot {
  margin-bottom: 0;
}
.login {
  background-color: #fff;
  width: 30%;
  text-align: center;
}
.labelOnTextField {
  position: relative;
  right: 20%;
}
.theme--light.v-btn {
  color: #fff;
}
.v-btn__content {
  font-size: 1.2rem;
}
</style>
<script>
import { useVuelidate } from "@vuelidate/core";
import { required, minLength } from "@vuelidate/validators";
import axios from "axios";
export default {
  //   name: "login",
  setup() {
    return {
      v$: useVuelidate(),
    };
  },
  data() {
    return {
      UserName: "",
      Password: "",
    };
  },

  unmounted() {
    this.style.remove();
  },

  methods: {
    login() {
      axios
        .get(
          `http://localhost:3000/users/` + this.UserName + "/" + this.Password
        )
        .then((response) => {
          console.log(response);
          if (response.data != "") {
            localStorage.setItem("userData", JSON.stringify(response.data));
            const backgroundElement = document.getElementsByClassName(
              "v-application--wrap"
            );
            const button =
              document.getElementsByClassName("theme--light v-btn");
            const buttonElement =
              document.getElementsByClassName("v-btn__content");
            if (response.data.role == "user") {
              backgroundElement[0].style.justifyContent = "normal";
              backgroundElement[0].style.alignItems = "normal";
              this.$router.push({
                name: "HomePage",
              });
            } else if (response.data.role == "admin") {
              backgroundElement[0].style.backgroundColor = "#fff";
              backgroundElement[0].style.justifyContent = "normal";
              backgroundElement[0].style.alignItems = "normal";
              button[0].style.color = "rgba(0, 0, 0, 0.87)";
              buttonElement[0].style.color = "rgba(0, 0, 0, 0.87)";
              this.$router.push({
                name: "UserDetailPage",
              });
            } else if (response.data.role == "doctor") {
              backgroundElement[0].style.backgroundColor = "#fff";
              backgroundElement[0].style.justifyContent = "normal";
              backgroundElement[0].style.alignItems = "normal";
              button[0].style.color = "rgba(0, 0, 0, 0.87)";
              buttonElement[0].style.color = "rgba(0, 0, 0, 0.87)";
              this.$router.push({
                name: "doctor-appointment",
              });
            }
          } else {
            alert("username or password is incorrect");
          }
        })
        .catch((error) => {
          console.log(error);
          alert("username or password is incorrect");
        });
    },
  },
  validations() {
    return {
      UserName: { required, minLength: minLength(3) },
      Password: { required, minLength: minLength(8) },
    };
  },
};
</script>
