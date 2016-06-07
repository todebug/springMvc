<template>
    <div class="dataEchart">
                <div id="statisticsData" class="statisticsData">
                </div>
    </div>
</template>

<script type="text/javascript">
'use strict';
var echarts = require('echarts');
var config = require('../../lib/js/config');
var queryDates=[],sumData=[],successData=[],failureData=[];
var tradeOption = {
	title: {
        		text: '交易统计'
    	},
	tooltip: {
		trigger: 'axis'
	},
	legend: {
		data: ['总笔数', '成功笔数','失败笔数']
	},
	grid: {
	        	left: '3%',
	        	right: '4%',
	        	bottom: '3%',
	        	containLabel: true
	},
	toolbox: {
		show: false,
		feature: {
			saveAsImage: {}
		}
	},
	xAxis: [{
		type: 'category',
		data:  queryDates
	}],
	yAxis: [{
		type: 'value',
		splitArea: {
			show: true
		}
	}],
	series: [{
		name: '总笔数',
		type:'line',
		data: sumData
	}, {
		name: '成功笔数',
		type:'line',
		data: successData
	},{
		name: '失败笔数',
		type:'line',
		data: failureData
	}]
};
var renderChart=function(chart,queryData){
	chart.showLoading({
		textStyle : {
			fontSize : 20
		}
	});
	var host = config.host;
	var url = host+'statistic/getStatistic';
	var result = fetch(url,{
	  method: 'post',
	  mode: 'cors',
	   headers: {
	     'Accept': 'application/json',
	     'Content-Type': 'application/json'
	 	},
	  body: JSON.stringify({
	    	                'dateType': queryData.dateType,
	 		    'beginTime': queryData.dateBegin,
	 		    'endTime': queryData.dateEnd
	  })
	})
	result.then(function(response) {
		return response.json();
	}).then(function(j) {
		console.log(j);
			queryDates = ['2016-05-21','2016-05-22','2016-05-23','2016-05-24','2016-05-25','2016-05-26','2016-05-27'];//queryDates=j.flowRecordDate;
			sumData=[1,2,3,4,5,6,7];//j.flowRecordCount[0].recordCount;
			successData=[2,5,6,9,3,5,2];//j.flowRecordCount[1].recordCount;
			failureData=[4,5,8,2,6,7,2];//j.flowRecordCount[2].recordCount;
			tradeOption.xAxis=[{
				type: 'category',
				data:  queryDates
			}];
			tradeOption.series=[{
				name: '总笔数',
				type:'line',
				data: sumData
			}, {
				name: '成功笔数',
				type:'line',
				data: successData
			},{
				name: '失败笔数',
				type:'line',
				data: failureData
			}];
			chart.hideLoading();
			chart.setOption(tradeOption);
			window.onresize = chart.resize; 
	}).catch(function(ex) {
		console.log('failed', ex)
	});
};
var myChart;
var moment=require('moment');
var today=moment().format('YYYY-MM-DD');
var queryTimeData={
	dateBegin: today,
            dateEnd: today,
            dateType: 'byDay'
}
module.exports= {
	ready: function(){
		this.drawEchart(queryTimeData);
  	},
  	methods: {
  		drawEchart: function(queryData) {
  			myChart = echarts.init(document.getElementById('statisticsData'));
			renderChart(myChart,queryData);
  		}

  	},
  	events: {
	    'query-condition': function (dateData) {
	      // 事件回调内的 `this` 自动绑定到注册它的实例上
	      this.drawEchart(dateData);
	    }
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
	width: 100%;
	height: 400px;
	min-width: 1000px;
}


</style>