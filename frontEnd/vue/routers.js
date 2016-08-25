'use strict';
module.exports = {
    router: function(router) {
        router.map({
            '/errorStatistics': {
                    component: require('./view/interface/errorStatistics.vue')
            },
            '/interfaceLog': {
                    component: require('./view/interface/interfaceLog.vue')
            },
        })
        return router;
    }
}
