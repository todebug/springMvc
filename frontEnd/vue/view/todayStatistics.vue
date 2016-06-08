<template>
	<div id="todayStatistics">
		<head-Bar></head-Bar>
		<data-Ul :xdy="xdy"></data-Ul> 
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
		<data-List v-for="item in xdy.dataList"  :item="item"></data-List>
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
var xdy = {
	    	    accessCount: '434',
		    accessTime: '00:03:09',
		    averageAccessPage: '1.19',
		    conversionCount: '34',
		    conversionRate: '31%',
		    orderConversionRate: '55%',
		    dataList: dataList
		};
var moment=require('moment');
var today=moment().format('YYYY-MM-DD');
//初始化默认值
var queryData = {
	startDate: today,
	endDate: today,
	periodType: 'byDay',
	checkedName: 'tradeCount',
	selectData: ''
};
module.exports= {
	data: function () {
	    return  {
	    	xdy
		}
	},
	events: {
	    'head-bar-date-condition': function (dateData) {
	      // 事件回调内的 `this` 自动绑定到注册它的实例上
	      queryData.startDate = dateData.startDate;
	      queryData.endDate = dateData.endDate;
	      queryData.periodType = dateData.periodType;
	      this.getDateTime(queryData);
	    },
	    'data-Ul-checkedNames': function (checkedNames) {
	      // 事件回调内的 `this` 自动绑定到注册它的实例上
	      queryData.checkedName = checkedNames;
	      this.getDateTime(queryData);
	    },
	    'condition-bar-selectData': function (selectData) {
	      // 事件回调内的 `this` 自动绑定到注册它的实例上
	      queryData.selectData = selectData;
	      this.getDateTime(queryData);
	    }
	},
	methods: {
		getDateTime: function(queryData){			
			if(queryData.startDate !=='' && queryData.endDate!=='' && queryData.periodType!==''){
				this.$broadcast('query-condition', queryData);
				//console.log(queryData);
			}
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