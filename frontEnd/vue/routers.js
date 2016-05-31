'use strict';
module.exports = {
    router: function(router) {
        router.map({
            'index': {
                component: require('./view/index.vue')
            },
            '/user': {
                component: require('./view/user.vue')
            },
            '/todayStatistics': {
                component: require('./view/todayStatistics.vue')
            }
        })
        return router;
    }
}
