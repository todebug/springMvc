<template>
	<div id="todayStatistics">
		<head-Bar></head-Bar>
		<data-Ul :info="info"></data-Ul> 
		<data-Echart></data-Echart>
		<condition-Bar></condition-Bar>
		<div class="listDiv">
			<table class="dataList">
				<thead>
					<tr>
						<td>日期</td>
						<td>时效性</td>
						<td>交易次数</td>
						<td>平均时长</td>
					</tr>
				</thead>
			</table>
		</div>
		<data-List v-for="item in info.dataList"  :item="item"></data-List>
		<page-bar></page-bar>
	</div>
</template>

<script>
'use strict';
var dataList = [];
var data1 = {
	date: '2016-06-01',
	timeRate: '25%',
	tradeCount: '20'
};
var data2 = {
	date: '2016-06-02',
	timeRate: '45%',
	tradeCount: '60'
};
var data3 = {
	date: '2016-06-03',
	timeRate: '75%',
	tradeCount: '20'
};
dataList.push(data1);
dataList.push(data2);
dataList.push(data3);
//初始化对象属性
var info = {
	reportData: {
		sumTitle: {
			cpic: '',
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
	},
	dataList: dataList
};
var Vue = require('vue');
var vm = new Vue({
  data: info
})
var moment=require('moment');
var today=moment().format('YYYY-MM-DD');
//初始化默认值
var queryData = {
	startDate: today,
	endDate: today,
	contrastStartDate: '',
            	contrastEndDate: '',
	periodType: 'TWO_MINUTE',//默认实时
            	indicators: ['TOTAL_COUNT'],//默认总交易数
	selectData: ''
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
	    'dispatch-dataUl-dataEchart-indicator': function (indicator) {//获取[dataUl.vue]中的指标信息
	      	this.queryData.indicators = [];
	      	this.queryData.indicators.push(indicator);
	      	this.getDateTime(this.queryData);
	    },
	    'dispatch-conditionBar-dataEchart-queryData': function (selectData) {//获取[conditionBar.vue]中的查询数据
	      	this.queryData.selectData = selectData;
	      	this.getDateTime(this.queryData);
	    },
	    'dispatch-dataEchart-dataUl-loadingData': function (loadingData) {//获取[dataEchart.vue的fetch的j数据,绑定到当前vue中绑定的info上,通过prop传递给子组件]
	      	// 动态设置属性
	      	this.info = Object.assign({}, this.info, loadingData);
	    },
	    'dispatch-dataEchart-dataUl-names': function (setNames) {//获取[dataEchart.vue的name数据,通知[dataUl.vue]交易统计数或时效name赋值
	     	this.$broadcast('broadcast-todayStatistics-dataUl-names', setNames);
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
			this.$broadcast('broadcast-todayStatistics-dataEchart-queryCondition', queryData);
		}
	},
	components: {
		headBar: require ('../../components/vue/todayStatistics/headBar.vue'),
		dataUl: require ('../../components/vue/todayStatistics/dataUl.vue'),
		dataEchart: require ('../../components/vue/todayStatistics/dataEchart.vue'),
		conditionBar: require ('../../components/vue/todayStatistics/conditionBar.vue'),
		dataList: require ('../../components/vue/todayStatistics/dataList.vue'),
		pageBar: require ('../../components/vue/todayStatistics/pageBar.vue')
	}
	
}
</script>

<style type="text/css">

.listDiv {
	margin-left: 5px;
	margin-right: 5px;
	border-radius: 5px;
	height: 26px;
	border: 1px solid #d6d6d6;
	background: grey;
	vertical-align: middle;
	min-width: 1000px;
}

.dataList {
	margin-top: 3px;
	width: 100%;
	display:block;
	text-align: center;
	vertical-align: middle;
	min-width: 1000px;
}

.dataList thead {
	width: 100%;
	min-width: 1000px;
	text-align: center;
	vertical-align: middle;
}

.dataList thead tr td {
	color: #fff;
	font-weight: bold;
	min-width: 250px;
	text-align: center;
	vertical-align: middle;
	overflow: hidden;
    	text-overflow: ellipsis;
    	white-space: nowrap;
}
</style>