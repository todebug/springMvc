'use strict';
var Vue = require('vue');
//定义全局过滤器
var filter = require('../components/lib/js/common-filter')
Vue.filter('insurance', filter.insurance)
Vue.filter('parseNum', filter.parseNum)
Vue.filter('line', filter.line)
Vue.filter('statusLine', filter.statusLine)
Vue.filter('date', filter.date)
var VueRouter = require('vue-router');
Vue.config.debug = true;
Vue.use(VueRouter);
var routerMap = require('./routers');
var detail = require('./view/detail.vue');
var App = Vue.extend(detail);
var router = new VueRouter();

//独立出来的路由
routerMap.router(router);
router.go('/errorStatistics');
router.start(App, '#app');
