<template>
	<div id="todayStatistics">
		<head-Bar></head-Bar>
		<data-Ul :xdy="xdy"></data-Ul> 
		<data-Echart :condition="condition"></data-Echart>
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
module.exports= {
	data: function () {
	    return  {
	    	xdy
		}
	},
	events: {
	    'head-bar-date-condition': function (dateData) {
	      // 事件回调内的 `this` 自动绑定到注册它的实例上
	      //console.log(dateData);
	      this.getDateTime(dateData);
	    }
	},
	methods: {
		getDateTime: function(dateData){			
			if(dateData.dateBegin !=='' && dateData.dateEnd!=='' && dateData.dateType!==''){
				//console.log("xdy-xdy");
				this.$broadcast('query-condition', dateData);
				//console.log("xdy-xdy99");
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