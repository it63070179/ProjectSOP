<template>
  <div class="login">
    <img src="../assets/Hospital.png" class="icon_img" />
    <p class="labelOnTextField" style="right: 22.5%">Name</p>
    <v-text-field label="Enter Your Name" solo v-model="v$.Name.$model" />
    <template v-if="v$.Name.$error">
      <p class="text-subtitle-2 red--text" v-if="v$.Name.required.$invalid">
        This field is requried
      </p>
      <p
        class="text-subtitle-2 red--text"
        v-else-if="v$.Name.minLength.$invalid"
      >
        This field should be at least 3 characters long
      </p>
    </template>
    <template v-if="this.findNameinDB !== undefined">
      <p class="text-subtitle-2 red--text">This field is invalid</p>
    </template>
    <p class="labelOnTextField">Username</p>
    <v-text-field
      label="Enter Your Username"
      solo
      v-model="v$.Username.$model"
    />
    <template v-if="v$.Username.$error">
      <p class="text-subtitle-2 red--text" v-if="v$.Username.required.$invalid">
        This field is requried
      </p>
      <p
        class="text-subtitle-2 red--text"
        v-else-if="v$.Username.minLength.$invalid"
      >
        This field should be at least 3 characters long
      </p>
    </template>
    <template v-if="this.findUsernameinDB !== undefined">
      <p class="text-subtitle-2 red--text">This field is invalid</p>
    </template>
    <p class="labelOnTextField">Password</p>
    <v-text-field
      label="Enter Your Password"
      solo
      v-model="v$.Password.$model"
    />
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
      <p
        class="text-subtitle-2 red--text"
        v-else-if="v$.Password.ValidatePassword.$invalid"
      >
        This field should be harder password
      </p>
    </template>
    <p class="labelOnTextField" style="right: 14%">Confirm Password</p>
    <v-text-field
      label="Enter Your Confirm Password"
      solo
      v-model="v$.ConfirmPassword.$model"
    />
    <template v-if="v$.ConfirmPassword.$error">
      <p
        class="text-subtitle-2 red--text"
        v-if="v$.ConfirmPassword.required.$invalid"
      >
        This field is requried
      </p>
      <p
        class="text-subtitle-2 red--text"
        v-else-if="v$.ConfirmPassword.minLength.$invalid"
      >
        This field should be at least 8 characters long
      </p>
      <p
        class="text-subtitle-2 red--text"
        v-else-if="v$.ConfirmPassword.sameAsPassword.$invalid"
      >
        This field not equal password field
      </p>
    </template>
    <p class="labelOnTextField" style="right: 23%">Email</p>
    <v-text-field label="Enter Your Email" solo v-model="v$.Email.$model" />
    <template v-if="v$.Email.$error">
      <p class="text-subtitle-2 red--text" v-if="v$.Email.required.$invalid">
        This field is requried
      </p>
      <p
        class="text-subtitle-2 red--text"
        v-else-if="v$.Email.minLength.$invalid"
      >
        This field should be at least 3 characters long
      </p>
      <p
        class="text-subtitle-2 red--text"
        v-else-if="v$.Email.ValidateEmail.$invalid"
      >
        This field is invalid email
      </p>
    </template>
    <template v-if="this.findEmailinDB !== undefined">
      <p class="text-subtitle-2 red--text">This field is invalid</p>
    </template>
    <p class="labelOnTextField">Gender</p>
    <v-select
      :items="GenderForSelect"
      label="Select Your Gender"
      solo
      v-model="Gender"
    ></v-select>
    <v-dialog v-model="showModal" persistent max-width="400">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          @click="addUser()"
          depressed
          color="#FB9C9C"
          style="margin-bottom: 10%; width: 40%; height: 6.5%"
          v-bind="attrs"
          v-on="on"
          >Register</v-btn
        >
      </template>

      <!-- show if register success -->
      <v-card
        v-if="
          v$.$invalid === false &&
          this.Gender !== '' &&
          this.findNameinDB === undefined &&
          this.findUsernameinDB === undefined &&
          this.findEmailinDB === undefined
        "
      >
        <v-card-title class="text-h5"> Register Success </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="modalClick()"> OK </v-btn>
        </v-card-actions>
      </v-card>
      <v-card v-else>
        <v-card-title class="text-h5"> Register not Success </v-card-title>
        <v-card-text
          >some field have wrong data or field require to input
          data</v-card-text
        >
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="showModal = false">
            OK
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>
<style>
.v-application--wrap {
  background-color: #a8dadc;
  justify-content: center;
  align-items: center;
  overflow: hidden;
}
.icon_img {
  width: 40%;
  margin-bottom: -2%;
}
.v-application p {
  margin-bottom: 10px;
}
.v-label {
  opacity: 1;
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
import { required, minLength, sameAs } from "@vuelidate/validators";
import axios from "axios";
const ValidatePassword = (value) => {
  const ReduxPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
  if (value.match(ReduxPassword)) {
    // console.log("password match");
    return true;
  } else {
    // console.log("password not match");
    return false;
  }
};
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
  // name: "login",
  setup() {
    return {
      v$: useVuelidate(),
    };
  },
  data() {
    return {
      Name: "",
      Username: "",
      Password: "",
      ConfirmPassword: "",
      Email: "",
      GenderForSelect: ["Male", "Female", "Other"],
      Gender: "",
      NameuserinDB: [],
      UsernameinDB: [],
      EmailuserinDB: [],
      findNameinDB: undefined,
      findUsernameinDB: undefined,
      findEmailinDB: undefined,
      showModal: false,
    };
  },
  validations() {
    return {
      Name: { required, minLength: minLength(3) },
      Username: { required, minLength: minLength(3) },
      Password: {
        required,
        minLength: minLength(8),
        ValidatePassword,
      },
      ConfirmPassword: {
        required,
        minLength: minLength(8),
        sameAsPassword: sameAs(this.Password),
      },
      Email: { required, minLength: minLength(3), ValidateEmail },
    };
  },

  mounted() {
    axios
      .get("http://localhost:3001/users")
      .then((response) => {
        // console.log(response.data);
        //ใส่ชื่อ user ใน DB ลง array
        response.data.forEach((element) => {
          this.NameuserinDB.push(element.name);
        });
        //ใส่username user ใน DB ลง array
        response.data.forEach((element) => {
          this.UsernameinDB.push(element.username);
        });
        //ใส่email user ใน DB ลง array
        response.data.forEach((element) => {
          this.EmailuserinDB.push(element.email);
        });
      })
      .catch((err) => {
        console.log(err);
      });
  },

  methods: {
    addUser() {
      this.findNameinDB = this.NameuserinDB.find(
        (element) => element === this.Name
      );
      this.findUsernameinDB = this.UsernameinDB.find(
        (element) => element === this.Username
      );
      this.findEmailinDB = this.EmailuserinDB.find(
        (element) => element === this.Email
      );
      console.log(this.findNameinDB);
      console.log(this.findUsernameinDB);
      console.log(this.findEmailinDB);
      if (this.findNameinDB !== undefined) {
        console.log("found name invalid");
        return;
      }
      if (this.findUsernameinDB !== undefined) {
        console.log("found username invalid");
        return;
      }
      if (this.findEmailinDB !== undefined) {
        console.log("found email invalid");
        return;
      }
      axios
        .post("http://localhost:3000/user/register", {
          name: this.Name,
          username: this.Username,
          password: this.Password,
          email: this.Email,
          gender: this.Gender,
          role: "user",
        })
        .then((response) => {
          console.log(response.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    modalClick() {
      this.showModal = false;
      this.$router.push({
        name: "login",
      });
    },
  },
};
</script>
