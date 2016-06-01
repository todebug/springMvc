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
var dateValueBegin,dateValueEnd;
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

module.exports= {
	data: function () {
	    return  {
	    	xdy
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
	dataUl: require ('../../components/vue/todayStatistics/dataUl.vue'),
	dataEchart: require ('../../components/vue/todayStatistics/dataEchart.vue'),
	conditionBar: require ('../../components/vue/todayStatistics/conditionBar.vue'),
	dataList: require ('../../components/vue/todayStatistics/dataList.vue')
	}
	
}
</script>

<style type="text/css">

.listDiv {
	background: grey;
}

.dataList {
	width: 100%;
	display:block;
	text-align: center;
	vertical-align: middle;
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