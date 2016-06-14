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
	    'head-bar-date-condition': function (dateData) {
	      // 事件回调内的 `this` 自动绑定到注册它的实例上
	      this.queryData.startDate = dateData.startDate;
	      this.queryData.endDate = dateData.endDate;
	      this.queryData.periodType = dateData.periodType;
	      this.getDateTime(this.queryData);
	    },
	    'data-Ul-checkedNames': function (checkedNames) {
	      // 事件回调内的 `this` 自动绑定到注册它的实例上
	      this.queryData.indicators = [];
	      this.queryData.indicators.push(checkedNames);
	      this.getDateTime(this.queryData);
	    },
	    'condition-bar-selectData': function (selectData) {
	      // 事件回调内的 `this` 自动绑定到注册它的实例上
	      this.queryData.selectData = selectData;
	      this.getDateTime(this.queryData);
	    },
	    'loading-other-vue-data': function (loadingData) {
	      // 事件回调内的 `this` 自动绑定到注册它的实例上
	      // 动态设置属性
	      this.info = Object.assign({}, this.info, loadingData);
	    },
	    'set-names': function (setNames) {
	      // 事件回调内的 `this` 自动绑定到注册它的实例上
	     this.$broadcast('set-dataUl-names', setNames);
	    }
	},
	methods: {
		getDateTime: function(queryData){	
			this.$broadcast('query-condition', queryData);
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