<template>
  <v-app>
    <br />
    <br />
    <div class="appointments">
      <h2>Appointment</h2>
      <v-container>
        <v-form v-model="isFormValid">
          <v-row>
            <v-col cols="12">
              <v-text-field
                label="Full Name"
                placeholder="Enter Your Full Name"
                outlined
                v-model="v$.Fullname.$model"
              ></v-text-field>
              <template v-if="v$.Fullname.$error">
                <p
                  class="text-subtitle-2 red--text"
                  v-if="v$.Fullname.required.$invalid"
                >
                  This field is requried
                </p>
                <p
                  class="text-subtitle-2 red--text"
                  v-else-if="v$.Fullname.minLength.$invalid"
                >
                  This field should be at least 6 characters
                </p>
              </template>
            </v-col>

            <v-col cols="12">
              <v-text-field
                label="Email"
                placeholder="Enter Your Email"
                outlined
                v-model="v$.Email.$model"
              />
              <template v-if="v$.Email.$error">
                <p
                  class="text-subtitle-2 red--text"
                  v-if="v$.Email.required.$invalid"
                >
                  This field is requried
                </p>
                <p
                  class="text-subtitle-2 red--text"
                  v-else-if="v$.Email.minLength.$invalid"
                >
                  This field should be at least 3 characters long
                </p>
              </template>
            </v-col>

            <v-col sm="6">
              <v-menu
                v-model="menu"
                :close-on-content-click="false"
                :nudge-right="100"
                transition="scale-transition"
                offset-y
                min-width="auto"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    label="YYYY/MM/DD format"
                    v-model="date"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-model="date"
                  @input="menu = false"
                ></v-date-picker>
              </v-menu>
            </v-col>

            <v-col cols="12" sm="6">
              <v-select
                label="Time"
                outlined
                v-model="v$.Time.$model"
                :items="SelectTime"
                :rules="rules.Times"
              ></v-select>
            </v-col>

            <v-col cols="12">
              <v-textarea v-model="v$.Description.$model" color="teal" outlined>
                <template v-slot:label>
                  <div>Description</div>
                </template>
              </v-textarea>
              <template v-if="v$.Description.$error">
                <p
                  class="text-subtitle-2 red--text"
                  v-if="v$.Description.required.$invalid"
                >
                  This field is requried
                </p>
              </template>
            </v-col>

            <v-dialog v-model="dialog" persistent max-width="500">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  style="margin-left: 30px"
                  class="btn"
                  v-bind="attrs"
                  v-on="on"
                  elevation="3"
                  rounded
                  x-large
                  :disabled="!isFormValid"
                  color="#8539E6"
                  >ยืนยันการจอง</v-btn
                >
              </template>
              <v-card>
                <v-card-title class="text-h5" style="justify-content: center">
                  ต้องการจองหมอคนนี้?
                </v-card-title>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-row style="margin-right:200px">
                    <v-btn
                      class="btn"
                      elevation="3"
                      rounded
                      large
                      color="#E63946"
                      @click="dialog = false"
                    >
                      ไม่จอง
                    </v-btn>
                  </v-row>
                  <v-col>
                    <v-btn
                      class="btn"
                      elevation="3"
                      rounded
                      large
                      color="#2d9646"
                      @click="dialog = false"
                    >
                      ต้องการจอง
                    </v-btn>
                  </v-col>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-row>
        </v-form>
      </v-container>
    </div>
  </v-app>
</template>
<style>
.v-application--wrap {
  background-color: #a8dadc;
  justify-content: center;
  align-items: center;
  overflow: hidden;
}

.v-text-field.v-text-field--enclosed {
  width: 100%;
}
.v-input__slot {
  margin-bottom: 0;
}
.appointments {
  background-color: #fff;
  width: 30%;
  text-align: center;
}
.labelOnTextField {
  font-size: 12px;
  font-weight: bold;
}
.btn {
  color: white !important;
  align-self: center;
}
</style>
<script>
import { useVuelidate } from "@vuelidate/core";
import { required, minLength } from "@vuelidate/validators";
export default {
  setup() {
    return {
      v$: useVuelidate(),
    };
  },
  data() {
    return {
      Fullname: "",
      Email: "",
      Time: "",
      Description: "",
      SelectTime: [
        "8.00",
        "9.00",
        "10.00",
        "11.00",
        "12.00",
        "13.00",
        "14.00",
        "15.00",
        "16.00",
      ],
      rules: {
        Times: [(val) => (val || "").length > 0 || "This field is required"],
      },
      date: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      menu: false,
      isFormValid: false,
      dialog: false,
    };
  },
  validations() {
    return {
      Fullname: { required, minLength: minLength(6) },
      Email: { required, minLength: minLength(3) },
      Description: { required },
      Time: { required },
    };
  },
};
</script>
