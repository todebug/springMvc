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
var echartData={
	queryDates: [],
	dataCpic: [],
	dataPicc: [],
	dataGpic: [],
	dataCpicName: '',
	dataPiccName: '',
	dataGpicName: ''
}
var tradeOption = {
	title: {
        		text: '交易统计'
    	},
	tooltip: {
		trigger: 'axis'
	},
	legend: {
		orient: 'horizontal', 
        		x: 'right',
        		y: 'top',
		data: [echartData.dataCpicName, echartData.dataPiccName,echartData.dataGpicName]
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
		boundaryGap : false,
		axisLabel :{  
		    interval:0   
		},
		data:  echartData.queryDates
	}],
	yAxis: [{
		type: 'value',
		splitArea: {
			show: false
		}
	}],
	series: [{
		name: echartData.dataCpicName,
		type:'line',
		data: echartData.dataCpic
	}, {
		name: echartData.dataPiccName,
		type:'line',
		data: echartData.dataPicc
	},{
		name: echartData.dataGpicName,
		type:'line',
		data: echartData.dataGpic
	}]
};
var renderChart=function(chart,queryData,_this){
	chart.showLoading({
		textStyle : {
			fontSize : 20
		}
	});
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
			j={"reportData":{"dataTitle":["19:04","19:06","19:08","19:10","19:12","19:14","19:16","19:18","19:20","19:22","19:24","19:26","19:28","19:30","19:32","19:34","19:36","19:38","19:40","19:42","19:44","19:46","19:48","19:50","19:52","19:54","19:56","19:58","20:00","20:02"],"indicators":{"picc_TOTAL_COUNT":"总交易数-人保财险","gpic_TOTAL_COUNT":"总交易数-国寿财险","cpic_TOTAL_COUNT":"总交易数-太保财险"},"items":{"picc_TOTAL_COUNT":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"gpic_TOTAL_COUNT":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"cpic_TOTAL_COUNT":[3,2,2,0,0,0,0,1,2,1,1,0,0,0,0,4,2,0,1,0,1,1,0,0,1,3,4,2,1,1]},"sumTitle":{"cpic_calculatePremium":"保费计算","gpic_calculatePremium":"保费计算","gpic":"合计","picc_calculatePremium":"保费计算","picc_submitApplication":"保单提交","cpic":"合计","cpic_submitApplication":"保单提交","picc":"合计","gpic_submitApplication":"保单提交"},"sumItems":{"cpic_calculatePremium":[5,0,5,0,8,1,null,3.8],"gpic_calculatePremium":[0,0,0,0,0,0,null,0],"gpic":[0,0,0,0,0,0,null,0],"picc_calculatePremium":[0,0,0,0,0,0,null,0],"picc_submitApplication":[0,0,0,0,0,0,null,0],"cpic":[33,12,21,36.36,8,0,null,1.48],"cpic_submitApplication":[0,0,0,0,0,0,null,0],"picc":[0,0,0,0,0,0,null,0],"gpic_submitApplication":[0,0,0,0,0,0,null,0]}}};
			//判断[dataUl.vue]中[交易统计]还是[交易时效]渲染name
			 var flag = _this.judgeIndicator(queryData.indicators[0]);
			 //赋判断属性---dataUl.vue
			 j = Object.assign({}, j, {show:flag});
			//实时设置其他vue数据
			_this.dispatchDataLoad(_this,j);
			//按照指标设置数据
			echartData = _this.modifyEchartData(j,echartData,queryData.indicators[0]);
			//指标为成功率时,设置y轴百分比
			if(queryData.indicators[0]==='SUCCESS_RATE'){
				tradeOption.yAxis=[{
					type: 'value',
					splitArea: {
						show: false
					},
					axisLabel: {//设置y轴显示百分百
						show: true,
						interval: 'auto',
						formatter: '{value}%'
					}
				}];
			}else{
				tradeOption.yAxis=[{
					type: 'value',
					splitArea: {
						show: false
					}
				}];
			}
			//设置legend显示位置
			tradeOption.legend.orient='horizontal';
			tradeOption.legend.x='right';
			tradeOption.legend.y='top';
			tradeOption.legend.data=[echartData.dataCpicName, echartData.dataPiccName,echartData.dataGpicName];
			if(queryData.periodType==='HOUR' || queryData.periodType==='TWO_MINUTE' ){
				tradeOption.xAxis=[{
					type: 'category',
					boundaryGap : false,//设置坐标轴数据靠近原点
					axisLabel :{  
					    	interval:0   //设置x轴坐标数据显示齐全
					},
					data:  echartData.queryDates
				}];
			}else{
				tradeOption.xAxis=[{
					type: 'category',
					boundaryGap : false,//设置坐标轴数据靠近原点
					data:  echartData.queryDates
				}];
			}
			tradeOption.series=[{
				name: echartData.dataCpicName,
				type:'line',
				data: echartData.dataCpic
			}, {
				name: echartData.dataPiccName,
				type:'line',
				data: echartData.dataPicc
			},{
				name: echartData.dataGpicName,
				type:'line',
				data: echartData.dataGpic
			}];
			chart.hideLoading();
			chart.setOption(tradeOption);
			window.onresize = chart.resize; 
	}).catch(function(ex) {
		console.log('failed', ex)
	});
};
var myChart;
var schedule = require('node-schedule');
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
module.exports= {
	data: function () {
	    //绑定查询条件
	    return  {queryCondition}
	},
	ready: function(){
		this.drawEchart(this.queryCondition);
		this.nodeSchedule(this.queryCondition);
  	},
  	methods: {
  		drawEchart: function(queryData) {
  			//echart渲染
  			var _this = this;
  			myChart = echarts.init(document.getElementById('statisticsData'));
			renderChart(myChart,queryData,_this);	
  		},
  		getQueryData: function() {
  			//实时获取查询vue绑定的查询条件
  			return this.queryCondition;
  		},
  		nodeSchedule: function(queryData) {
  			//页面定时刷新处理
  			var rule = new schedule.RecurrenceRule();  
  			var times = [];
  			//每三分钟执行一次
			for(var i=0; i<60; i=i+3){
			　　times.push(i);
			}
			rule.minute  = times;
			var _this = this;
			var count = 0;
			var j = schedule.scheduleJob(rule, function(){
				//指标为实时时才刷新
				if(_this.getQueryData().periodType==='TWO_MINUTE'){
					//根据查询条件动态渲染echart
				     　　_this.drawEchart(_this.getQueryData());
			     		count++;
			     		var myDate = new Date();
					console.log('npm schedule 第'+count+'次执行,当前时间是:'+myDate.getHours()+':'+myDate.getMinutes());
				}
			　　});
  		},
  		dispatchDataLoad: function(_this,data) {
  			if(data!==''&&data!==undefined){
  				//将查询数据上载到父组件进行分发处理
  				_this.$dispatch('dispatch-dataEchart-dataUl-loadingData', data);
  			}
  		},
  		modifyEchartData: function(j,echartData,indicators) {
  			if(indicators==='TOTAL_COUNT'){
  				echartData.queryDates = j.reportData.dataTitle;
				echartData.dataCpic = j.reportData.items.cpic_TOTAL_COUNT;
				echartData.dataPicc = j.reportData.items.picc_TOTAL_COUNT;
				echartData.dataGpic = j.reportData.items.gpic_TOTAL_COUNT;
				echartData.dataCpicName = j.reportData.indicators.cpic_TOTAL_COUNT;
				echartData.dataPiccName = j.reportData.indicators.picc_TOTAL_COUNT;
				echartData.dataGpicName = j.reportData.indicators.gpic_TOTAL_COUNT;
  			}else if(indicators==='SUCCESS_COUNT'){
  				echartData.queryDates = j.reportData.dataTitle;
				echartData.dataCpic = j.reportData.items.cpic_SUCCESS_COUNT;
				echartData.dataPicc = j.reportData.items.picc_SUCCESS_COUNT;
				echartData.dataGpic = j.reportData.items.gpic_SUCCESS_COUNT;
				echartData.dataCpicName = j.reportData.indicators.cpic_SUCCESS_COUNT;
				echartData.dataPiccName = j.reportData.indicators.picc_SUCCESS_COUNT;
				echartData.dataGpicName = j.reportData.indicators.gpic_SUCCESS_COUNT;
  			}else if(indicators==='FAILURE_COUNT'){
  				echartData.queryDates = j.reportData.dataTitle;
				echartData.dataCpic = j.reportData.items.cpic_FAILURE_COUNT;
				echartData.dataPicc = j.reportData.items.picc_FAILURE_COUNT;
				echartData.dataGpic = j.reportData.items.gpic_FAILURE_COUNT;
				echartData.dataCpicName = j.reportData.indicators.cpic_FAILURE_COUNT;
				echartData.dataPiccName = j.reportData.indicators.picc_FAILURE_COUNT;
				echartData.dataGpicName = j.reportData.indicators.gpic_FAILURE_COUNT;
  			}else if(indicators==='SUCCESS_RATE'){
  				echartData.queryDates = j.reportData.dataTitle;
				echartData.dataCpic = j.reportData.items.cpic_SUCCESS_RATE;
				echartData.dataPicc = j.reportData.items.picc_SUCCESS_RATE;
				echartData.dataGpic = j.reportData.items.gpic_SUCCESS_RATE;
				echartData.dataCpicName = j.reportData.indicators.cpic_SUCCESS_RATE;
				echartData.dataPiccName = j.reportData.indicators.picc_SUCCESS_RATE;
				echartData.dataGpicName = j.reportData.indicators.gpic_SUCCESS_RATE;
  			}else if(indicators==='MAX_DURATION'){
  				echartData.queryDates = j.reportData.dataTitle;
				echartData.dataCpic = j.reportData.items.cpic_MAX_DURATION;
				echartData.dataPicc = j.reportData.items.picc_MAX_DURATION;
				echartData.dataGpic = j.reportData.items.gpic_MAX_DURATION;
				echartData.dataCpicName = j.reportData.indicators.cpic_MAX_DURATION;
				echartData.dataPiccName = j.reportData.indicators.picc_MAX_DURATION;
				echartData.dataGpicName = j.reportData.indicators.gpic_MAX_DURATION;
  			}else if(indicators==='MIN_DURATION'){
  				echartData.queryDates = j.reportData.dataTitle;
				echartData.dataCpic = j.reportData.items.cpic_MIN_DURATION;
				echartData.dataPicc = j.reportData.items.picc_MIN_DURATION;
				echartData.dataGpic = j.reportData.items.gpic_MIN_DURATION;
				echartData.dataCpicName = j.reportData.indicators.cpic_MIN_DURATION;
				echartData.dataPiccName = j.reportData.indicators.picc_MIN_DURATION;
				echartData.dataGpicName = j.reportData.indicators.gpic_MIN_DURATION;
  			}else if(indicators==='AVERAGE_DURATION'){
  				echartData.queryDates = j.reportData.dataTitle;
				echartData.dataCpic = j.reportData.items.cpic_AVERAGE_DURATION;
				echartData.dataPicc = j.reportData.items.picc_AVERAGE_DURATION;
				echartData.dataGpic = j.reportData.items.gpic_AVERAGE_DURATION;
				echartData.dataCpicName = j.reportData.indicators.cpic_AVERAGE_DURATION;
				echartData.dataPiccName = j.reportData.indicators.picc_AVERAGE_DURATION;
				echartData.dataGpicName = j.reportData.indicators.gpic_AVERAGE_DURATION;
  			}else if(indicators==='NINETY_DURATION'){
  				echartData.queryDates = j.reportData.dataTitle;
				echartData.dataCpic = j.reportData.items.cpic_NINETY_DURATION;
				echartData.dataPicc = j.reportData.items.picc_NINETY_DURATION;
				echartData.dataGpic = j.reportData.items.gpic_NINETY_DURATION;
				echartData.dataCpicName = j.reportData.indicators.cpic_NINETY_DURATION;
				echartData.dataPiccName = j.reportData.indicators.picc_NINETY_DURATION;
				echartData.dataGpicName = j.reportData.indicators.gpic_NINETY_DURATION;
  			}
  			return echartData;
  		},
  		judgeIndicator: function(indicator) {
  			if(indicator==='TOTAL_COUNT' || indicator==='SUCCESS_COUNT' || indicator==='FAILURE_COUNT' || indicator==='SUCCESS_RATE'){
  				return true;
  			}else{
  				return false;
  			}
  		}
  	},
  	events: {
	    'broadcast-todayStatistics-dataEchart-dataList-queryCondition': function(date) {
	      // 动态绑定查询对象数据
	      this.queryCondition = Object.assign({}, this.queryCondition, date);
	      this.drawEchart(this.queryCondition);
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