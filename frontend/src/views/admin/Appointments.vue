<template>
    <v-app>
      <v-app-bar class="flex-grow-0" app dark>
        <v-app-bar-title>Appointments</v-app-bar-title>
      </v-app-bar>
      <v-navigation-drawer app dark>
        <v-divider style="margin-top:25%"></v-divider>
        <v-list dense nav>
          <v-list-item v-for="item in items" :to="item.path" :key="item.title" link>
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
              <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                label="Search"
                single-line
                hide-details
              ></v-text-field>
            </v-card-title>
            <v-data-table
              :headers="headers"
              :items="appointments"
              :search="search"
            ></v-data-table>
          </v-card>
        </div>
      </v-content>
    </v-app>
  </template>
  
  <script>
import axios from 'axios'
    export default {
      name: 'admin-appointment',
      data() {
        return {
          result: '',
          items: [
            { title: 'User Details', icon: 'mdi-account-circle', path: '/userDetails'},
            { title: 'Appointments', icon: 'mdi-calendar-clock', path: '/appointments' },
            { title: 'Dotor Details', icon: 'mdi-card-account-details', path: '/doctorDetails' },
            { title: 'Admin Details', icon: 'mdi-card-account-details-star', path: '/adminDetails' },
            { title: 'Add Doctor', icon: 'mdi-account-plus-outline', path: '/addDoctor' },
            { title: 'Add Admin', icon: 'mdi-account-plus', path: '/addAdmin' },
          ],
          search: '',
          headers: [
            {
              text: 'ID',
              align: 'start',
              filterable: false,
              value: 'id',
            },
            { text: 'Full Name', value: 'fullName' },
            { text: 'Email', value: 'email' },
            { text: 'Date', value: 'date' },
            { text: 'Time', value: 'time' },
            { text: 'Description', value: 'description' },
            { text: 'Doctor', value: 'doctor' },
          ],
          appointments: [
            {
              id: 1,
              fullName: 'User1 Lastname',
              email: 'user1@gmail.com',
              date: '15/12/2022',
              time: '12.00',
              description: 'headache',
              doctor: 'doctor1'
            },
            {
              id: 2,
              fullName: 'User2 Lastname',
              email: 'user2@gmail.com',
              date: '15/12/2022',
              time: '13.00',
              description: 'stomachache',
              doctor: 'doctor2'
            },
          ],
        }
      },
      methods: {
        getUsers(){
          axios
          .get(`http://localhost:3003/appointmentUser`)
          .then((response) => {
            let filterData = response.data.filter(item => item.role == "admin");
            this.admins = filterData;
          })
          .catch((error) => {
            console.log(error);
          })
        },
        logout(){
          localStorage.removeItem('userData');
          this.$router.push({
            name: "LoginPage",
          });
        }
      }
    }
  </script>