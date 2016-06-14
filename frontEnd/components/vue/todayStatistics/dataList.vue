<template>
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
	<data-Item  v-for="item in dataList"  :item="item"></data-Item>
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
var config = require('../../lib/js/config');
var moment=require('moment');
var today=moment().format('YYYY-MM-DD');
//初始化查询条件
var queryCondition={
            startDate: today,
            endDate: today,
            contrastStartDate: '',
            contrastEndDate: '',
            periodType: 'TWO_MINUTE',//默认实时
            indicators: ['TOTAL_COUNT']//默认总交易数
}
var pageData = {
	cur: 0,
	all: 0
}
module.exports= {
	data: function() {
	    //绑定数据
	    return  {dataList,queryCondition,pageData}
	},
	ready: function() {
		this.getDataList(this.queryCondition);
	},
	props:['item'],
	compiled: function () {
		//console.log(this.item);
	},
	methods: {
		dispatchPageData: function() {//将页数总数传递到父组件中,最终传递至子组件[pageBar.vue]中
	            		//设置点击页数
	            		this.$dispatch('dispatch-dataList-pageBar-PageData', this.pageData);
	            },
	            getDataList: function(queryData) {
	            		var url = config.host+'statistic/getStatistic';
			var result = fetch(url,{
				  method: 'post',
				  mode: 'cors',
				   headers: {
				     'Accept': 'application/json',
				     'Content-Type': 'application/json'
				 	},
				  body: JSON.stringify({
				 		    'startDate': queryData.startDate,
				 		    'endDate': queryData.endDate,
				 		    'contrastStartDate': queryData.contrastStartDate,
				 		    'contrastEndDate': queryData.contrastEndDate,
				 		    'periodType': queryData.periodType,
				 		    'indicators': queryData.indicators
				  })
			});
			result.then(function(response) {
				return response.json();
			}).then(function(j) {
				//console.log(j);
				
			}).catch(function(ex) {
				console.log('failed', ex)
			});
	            }
	},
	events: {
		'broadcast-todayStatistics-dataList-curPage': function(curPage) {//获取[pageBar.vue]传递至父组件的数据,当前点击页数
	    		//查询当前页数信息
	    		
	    	}
	},
	components: {
		dataItem: require ('./dataItem.vue')
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