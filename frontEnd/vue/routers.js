var index = require('./view/index.vue')
var user = require('./view/user.vue')

module.exports = {
    router: function(router) {
        router.map({
            'index': {
                component: index
            },
            '/user': {
                component: user
            }
        })
        return router;
    }
}
