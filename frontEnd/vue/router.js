'use strict';
var Vue = require('vue')
var VueRouter = require('vue-router')
Vue.config.debug = true;
Vue.use(VueRouter)

// Define some components
var routerMap = require('./routers')
// var index = require('./view/index.vue')
// var user = require('./view/user.vue')

// The router needs a root component to render.
// For demo purposes, we will just use an empty one
// because we are using the HTML as the app template.
// !! Note that the App is not a Vue instance.
//var App = Vue.extend({})
var detail = require('./view/detail.vue')
var App = Vue.extend(detail) 

// Create a router instance.
// You can pass in additional options here, but let's
// keep it simple for now.
var router = new VueRouter()

// Define some routes.
// Each route should map to a component. The "component" can
// either be an actual component constructor created via
// Vue.extend(), or just a component options object.
// We'll talk about nested routes later.
// router.map({
//     '/index': {
//         component: index
//     },
//     '/user': {
//         component: user
//     }
// })

//独立出来的路由
routerMap.router(router)

router.go('/todayStatistics')

// Now we can start the app!
// The router will create an instance of App and mount to
// the element matching the selector #app.
router.start(App, '#app')
