<template>
  <v-app>
    <v-app-bar class="flex-grow-0" app dark>
      <v-app-bar-title>Admin Details</v-app-bar-title>
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
        <div class="pa-2" @click="result = 'test'">
          <v-btn block light>Logout</v-btn>
        </div>
      </template>
    </v-navigation-drawer>
  </v-app>
</template>

<script>
  export default {
    name: 'admin-adminDetails',
    data() {
      return {
        result: '',
        items: [
          { title: 'User Details', icon: 'mdi-account-circle', path: '/'},
          { title: 'Appointments', icon: 'mdi-calendar-clock', path: '/appointments' },
          { title: 'Dotor Details', icon: 'mdi-card-account-details', path: '/doctorDetails' },
          { title: 'Admin Details', icon: 'mdi-card-account-details-star', path: '/adminDetails' },
          { title: 'Add Doctor', icon: 'mdi-account-plus-outline', path: '/addDoctor' },
          { title: 'Add Admin', icon: 'mdi-account-plus', path: '/addAdmin' },
        ],
      }
    },
  }
</script>