<template>
	<div v-bind:class="{ 'displayDivHide': isHide}">
		<div id="ui-loading-backdrop" class="ui-loading-backdrop"></div>
		<div id="ui-loading-bar" class="ui-loading-bar">
		    <h3 class="ui-loading-img"></h3>
		    <p class="ui-loading-text">拼命加载中，请稍等...</p>
		</div>
	</div>
</template>

<script>
'use strict';
var isHide=true;
module.exports= {
        data: function() {
	    return  {isHide}
        },  
        created: function() {
                this.initQueryData();
        }, 
	methods: {
                    initQueryData: function(){
                            this.isHide=true;
                    },
	},
	events: {
		'broadcast-loading-show': function(show) {
			this.isHide=false;
		},
		'broadcast-loading-hide': function(hide) {
			this.isHide=true;
		},
	},
}
</script>

<style type="text/css">
.displayDivHide {
	display: none;
}
.ui-loading-backdrop {
    display: block;
    width: 100%;
    height: 100%;
    opacity: 0.3;
    filter: alpha(opacity=30);
    background: #000;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 20000;
}
.ui-loading-bar {
    display: block;
    width: 140px;
    height: 200px;
    position: fixed;
    top: 40%;
    left: 50%;
    margin-left: -64px;
    z-index: 20001;
}
.ui-loading-img {
    width: 128px;
    height: 128px;
    padding: 0;
    background: url(../../lib/image/loading.gif) no-repeat 0 0;
}
.ui-loading-text {
    padding: 0;
    margin: 0;
    font-size: 14px;
}
</style>