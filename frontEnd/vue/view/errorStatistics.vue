<template>
	<div id="errorStatistics">
		<head-Bar></head-Bar>
		<!-- <data-Ul :info="info"></data-Ul> 
		<data-Echart></data-Echart>
		<condition-Bar></condition-Bar>
		<data-List></data-List> -->
	</div>
</template>

<script>
'use strict';
//初始化对象属性
var moment=require('moment');
var today=moment().format('YYYY-MM-DD');
//初始化默认值
var arr1 = [];
var arr2 = [];
var queryData = {
	startDate: today,
	endDate: today,
	contrastStartDate: '',
            	contrastEndDate: '',
	periodType: 'TWO_MINUTE',//默认实时
            	indicators: ['TOTAL_COUNT','MAX_DURATION'],//默认总交易数
	provinces: arr1,
              interfaceTypes: arr2
};
module.exports= {
	data: function () {
	    	//绑定数据
	    	return  {queryData}
	},
	events: {
	    // 'dispatch-headBar-dataEchart-queryCondition': function (dateData) {//获取[headBar.vue]中时间查询条件
	    //   	// 事件回调内的 `this` 自动绑定到注册它的实例上
	    //   	this.queryData.startDate = dateData.startDate;
	    //   	this.queryData.endDate = dateData.endDate;
	    //   	this.queryData.periodType = dateData.periodType;
	    //   	this.getDateTime(this.queryData);
	    // }
	},
	methods: {
		// getDateTime: function(queryData){//将查询条件广播到子组件中.[dataEchart.vue]
		// 	this.$broadcast('broadcast-todayStatistics-dataEchart-dataList-queryCondition', queryData);
		// }
	},
	components: {
		headBar: require ('../../components/vue/errorStatistics/headBar.vue'),
		// dataUl: require ('../../components/vue/todayStatistics/dataUl.vue'),
		// dataEchart: require ('../../components/vue/todayStatistics/dataEchart.vue'),
		// conditionBar: require ('../../components/vue/todayStatistics/conditionBar.vue'),
		// dataList: require ('../../components/vue/todayStatistics/dataList.vue')
	}
}
</script>
