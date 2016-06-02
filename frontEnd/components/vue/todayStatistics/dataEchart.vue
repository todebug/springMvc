<template>
    <div class="dataEchart">
                <div id="statisticsData" class="statisticsData">
                </div>
    </div>
</template>

<script>
'use strict';
var echarts = require('echarts');
var dates=[],errorDate=[],errorData=[],bookingData=[],replyData=[],cbaData=[];
var optionbooking = {
	tooltip: {
		trigger: 'axis'
	},
	legend: {
		data: ['订舱提交', '订舱批复','CBA发布']
	},
	toolbox: {
		show: false,
		feature: {

		}
	},
	xAxis: [{
		type: 'category',
		data:  dates
	}],
	yAxis: [{
		type: 'value',
		splitArea: {
			show: true
		}
	}],
	series: [{
		name: '订舱提交',
		type:'line',
		data: bookingData
	}, {
		name: '订舱批复',
		type:'line',
		data: replyData
	},{
		name: 'CBA发布',
		type:'line',
		data: cbaData
	}]
};
var renderChart=function(chart){
	chart.showLoading({
		textStyle : {
			fontSize : 20
		}
	});
	var url = 'http://localhost:8888/statistic/getSucessSaveHistory/2016-05-23/2016-05-27';
	var result = fetch(url)
	result.then(function(response) {
		return response.json();
	}).then(function(j) {
			dates = ['2016-05-21','2016-05-22','2016-05-23','2016-05-24','2016-05-25','2016-05-26','2016-05-27'];//dates=j.flowRecordDate;
			bookingData=[1,2,3,4,5,6,7];//j.flowRecordCount[0].recordCount;
			replyData=[2,5,6,9,3,5,2];//j.flowRecordCount[1].recordCount;
			cbaData=[4,5,8,2,6,7,2];//j.flowRecordCount[2].recordCount;
			optionbooking.xAxis=[{
				type: 'category',
				data:  dates
			}];
			optionbooking.series=[{
				name: '订舱提交',
				type:'line',
				data: bookingData
			}, {
				name: '订舱批复',
				type:'line',
				data: replyData
			},{
				name: 'CBA发布',
				type:'line',
				data: cbaData
			}];
			chart.hideLoading();
			chart.setOption(optionbooking);
	}).catch(function(ex) {
		console.log('failed', ex)
	});
};
var myChart;
module.exports= {
	ready:function(){
	myChart = echarts.init(document.getElementById('statisticsData'));
	renderChart(myChart);
  }
}

</script>

<style type="text/css">

.dataEchart {
	margin-left: 5px;
	margin-right: 5px;
	margin-top: 5px;
	border: 1px solid #d6d6d6;
    	border-radius: 5px;
    	width: 99.3%;
    	min-width: 1000px;
}

.statisticsData {
	width: 95%;
	height: 400px;
}


</style>