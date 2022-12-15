import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
      path: '/',
      name: 'UserDetailPage',
      component: () => import('../views/admin/UserDetailPage')
    },
    {
      path: '/appointments',
      name: 'Appointments',
      component: () => import('../views/admin/Appointments')
    },
    {
      path: '/doctorDetails',
      name: 'DoctorDetails',
      component: () => import('../views/admin/DoctorDetails')
    },
    {
      path: '/adminDetails',
      name: 'AdminDetails',
      component: () => import('../views/admin/AdminDetails')
    },
//   {
//     path: '/',
//     name: 'home',
//     component: () => import('../views/Home.vue')
//   },
//   {
//     path: '/employee',
//     name: 'employee-login',
//     component: () => import('../views/employee/employee-login.vue')
//   },
//   {
//     path: '/employee/register',
//     name: 'employee-register',
//     component: () => import('../views/employee/employee-register.vue')
//   },
//   {
//     path: '/employee/main/:account_id',
//     name: 'employee-main',
//     component: () => import('../views/employee/employee-main.vue')
//   },
//   {
//     path: '/employee/profile/:account_id',
//     name: 'employee-profile',
//     component: () => import('../views/employee/employee-profile.vue')
//   },
//   {
//     path: '/customer',
//     name: 'customer-login',
//     component: () => import('../views/customer/customer-main.vue')
//   }
]

const router = new VueRouter({
  routes
})

export default router