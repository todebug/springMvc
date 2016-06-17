<template>
	<div id="todayStatistics">
		<head-Bar></head-Bar>
		<data-Ul :info="info"></data-Ul> 
		<data-Echart></data-Echart>
		<condition-Bar></condition-Bar>
		<data-List></data-List>
	</div>
</template>

<script>
'use strict';
//初始化对象属性
var info = {
	reportData: {
		sumTitle: {
			cpic: {},
			cpic_calculatePremium: {},
			cpic_submitApplication: {},
			picc: {},
			picc_calculatePremium: {},
			picc_submitApplication: {},
			gpic: {},
			gpic_calculatePremium: {},
			gpic_submitApplication: {},
		},
		sumItems: {
			cpic: {},
			cpic_calculatePremium: {},
			cpic_submitApplication: {},
			picc: {},
			picc_calculatePremium: {},
			picc_submitApplication: {},
			gpic: {},
			gpic_calculatePremium: {},
			gpic_submitApplication: {}
		}
	}
};
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
	    	return  {info,queryData}
	},
	events: {
	    'dispatch-headBar-dataEchart-queryCondition': function (dateData) {//获取[headBar.vue]中时间查询条件
	      	// 事件回调内的 `this` 自动绑定到注册它的实例上
	      	this.queryData.startDate = dateData.startDate;
	      	this.queryData.endDate = dateData.endDate;
	      	this.queryData.periodType = dateData.periodType;
	      	this.getDateTime(this.queryData);
	    },
	    'dispatch-dataUl-dataEchart-tradeCount': function (tradeCount) {//获取[dataUl.vue]中的指标信息
	    	if(this.queryData.indicators[0]!==tradeCount){
	    		this.queryData.indicators.$set(0,tradeCount);
	    	}
	      	this.getDateTime(this.queryData);
	    },
	    'dispatch-dataUl-dataEchart-tradeTime': function (tradeTime) {//获取[dataUl.vue]中的指标信息
	    	if(this.queryData.indicators[1]!==tradeTime){
	    		this.queryData.indicators.$set(1,tradeTime);
	    	}
	      	this.getDateTime(this.queryData);
	    },
	    'dispatch-conditionBar-dataEchart-provinces': function (provinces) {//获取[conditionBar.vue]中的查询数据
	    	this.queryData.provinces.shift();
	    	if(provinces!==''){
	      		this.queryData.provinces.push(provinces);
	      	}
	      	this.getDateTime(this.queryData);
	    },
	   'dispatch-conditionBar-dataEchart-interfaceTypes': function (interfaceTypes) {//获取[conditionBar.vue]中的查询数据
	      	this.queryData.interfaceTypes.shift();
	      	if(interfaceTypes!==''){
	      		this.queryData.interfaceTypes.push(interfaceTypes);
	      	}
	      	this.getDateTime(this.queryData);
	    },
	    'dispatch-dataEchart-dataUl-loadingData': function (loadingData) {//获取[dataEchart.vue的fetch的j数据,绑定到info上,通过prop传递给子组件
	      	// 动态设置属性
	      	this.info = Object.assign({}, this.info, loadingData);
	    },
	    'dispatch-pageBar-dataList-curPage': function(curPage) {//获取[pageBar.vue]当前点击页数,将数据传递至子组件[dataList.vue]中
	    	this.$broadcast('broadcast-todayStatistics-dataList-curPage', curPage);
	    },
	    'dispatch-dataList-pageBar-PageData': function(pageData) {//通知[pageBar.vue]当前页数及当前总页数
	    	this.$broadcast('broadcast-todayStatistics-pageBar-getPageData', pageData);
	    }
	},
	methods: {
		getDateTime: function(queryData){//将查询条件广播到子组件中.[dataEchart.vue]
			this.$broadcast('broadcast-todayStatistics-dataEchart-dataList-queryCondition', queryData);
		}
	},
	components: {
		headBar: require ('../../components/vue/todayStatistics/headBar.vue'),
		dataUl: require ('../../components/vue/todayStatistics/dataUl.vue'),
		dataEchart: require ('../../components/vue/todayStatistics/dataEchart.vue'),
		conditionBar: require ('../../components/vue/todayStatistics/conditionBar.vue'),
		dataList: require ('../../components/vue/todayStatistics/dataList.vue')
		
	}
}
</script>
