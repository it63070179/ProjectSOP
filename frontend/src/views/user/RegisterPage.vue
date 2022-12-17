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
    <p class="labelOnTextField">Gender</p>
    <v-select
      :items="GenderForSelect"
      label="Gender"
      solo
      v-model="Gender"
    ></v-select>
    <v-btn
      depressed
      color="#FB9C9C"
      style="margin-bottom: 10%; width: 40%; height: 6.5%"
      >Register</v-btn
    >
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
};
</script>