<template>
	<div id="todayStatistics">
		<head-Bar></head-Bar>
		<data-Table :xdy="xdy"></data-Table> 
		<data-Echart></data-Echart>
		<condition-Bar></condition-Bar>
		<data-List></data-List>
	</div>
</template>

<script>
var dateValueBegin,dateValueEnd;
module.exports= {
	data: function () {
	    return { 
	    	xdy: {
	    	    accessCount: '434',
		    accessTime: '00:03:09',
		    averageAccessPage: '1.19',
		    conversionCount: '34',
		    conversionRate: '31%',
		    orderConversionRate: '55%'}
		     }
	  },
	  events: {
	    'head-bar-date-begin': function (begin) {
	      // 事件回调内的 `this` 自动绑定到注册它的实例上
	      dateValueBegin = begin;
	      this.getDateTime();
	    },
	    'head-bar-date-end': function (end) {
	      // 事件回调内的 `this` 自动绑定到注册它的实例上
	      dateValueEnd = end;
	      this.getDateTime();
	    }
	},
	methods: {
		getDateTime: function(){
			if(dateValueBegin !=='' && dateValueEnd!==''){
				//TODO
			}
		}
	},
	components: {
	headBar: require ('../../components/vue/todayStatistics/headBar.vue'),
	dataTable: require ('../../components/vue/todayStatistics/dataTable.vue'),
	dataEchart: require ('../../components/vue/todayStatistics/dataEchart.vue'),
	conditionBar: require ('../../components/vue/todayStatistics/conditionBar.vue'),
	dataList: require ('../../components/vue/todayStatistics/dataList.vue')
	}
	
}
</script>