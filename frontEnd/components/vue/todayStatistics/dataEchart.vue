<template>
    <div class="dataEchart">
                <div id="statisticsData" class="statisticsData">
                </div>
                <div class="statisticsCalculateData" id="statisticsCalculateData">
                	<div id="calculatePremium" class="calculatePremium"></div>
                	<div id="submitApplication" class="submitApplication"></div>
                </div>
    </div>
</template>

<script type="text/javascript">
'use strict';
var echarts = require('echarts');
var config = require('../../lib/js/config');
var theme = require('../../lib/js/echartTheme');
var echartData={
	queryDates: [],
	dataCpicCount: [],
	dataPiccCount: [],
	dataGpicCount: [],
	dataCpicCountName: '',
	dataPiccCountName: '',
	dataGpicCountName: '',
	dataCpicTime: [],
	dataPiccTime: [],
	dataGpicTime: [],
	dataCpicTimeName: '',
	dataPiccTimeName: '',
	dataGpicTimeName: '',
	//calculatePremium
	dataCpicCountPremium: [],
	dataPiccCountPremium: [],
	dataGpicCountPremium: [],
	dataCpicCountNamePremium: '',
	dataPiccCountNamePremium: '',
	dataGpicCountNamePremium: '',
	dataCpicTimePremium: [],
	dataPiccTimePremium: [],
	dataGpicTimePremium: [],
	dataCpicTimeNamePremium: '',
	dataPiccTimeNamePremium: '',
	dataGpicTimeNamePremium: '',
	//submitApplication
	dataCpicCountApplication: [],
	dataPiccCountApplication: [],
	dataGpicCountApplication: [],
	dataCpicCountNameApplication: '',
	dataPiccCountNameApplication: '',
	dataGpicCountNameApplication: '',
	dataCpicTimeApplication: [],
	dataPiccTimeApplication: [],
	dataGpicTimeApplication: [],
	dataCpicTimeNameApplication: '',
	dataPiccTimeNameApplication: '',
	dataGpicTimeNameApplication: ''
}
var tradeOption1 = {
	color:['#004a97','#e8380d', '#009B63','#004a97','#e8380d', '#009B63'],
	title: {
		show: false,
        		text: '交易统计'
    	},
	tooltip: {
		trigger: 'axis'
	},
	legend: {
		orient: 'horizontal', 
        		x: 'right',
        		y: 'top',
		data: [echartData.dataCpicCountName, echartData.dataPiccCountName,echartData.dataGpicCountName,echartData.dataCpicTimeName, echartData.dataPiccTimeName,echartData.dataGpicTimeName]
	},
	grid: {
	        	left: '3%',
	        	right: '4%',
	        	bottom: '3%',
	        	containLabel: true
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
		name : '',
		splitArea: {
			show: false
			}
		},
		{
		type: 'value',
		name : '',
		splitArea: {
			show: false
			}
		}
	],
	series: [{
		name: echartData.dataCpicCountName,
		type:'bar',
		data: echartData.dataCpicCount
	}, {
		name: echartData.dataPiccCountName,
		type:'bar',
		data: echartData.dataPiccCount
	},{
		name: echartData.dataGpicCountName,
		type:'bar',
		data: echartData.dataGpicCount
	},{
		name: echartData.dataGpicTimeName,
		type:'line',
		yAxisIndex: 1,
		data: echartData.dataGpicTime
	},{
		name: echartData.dataGpicTimeName,
		type:'line',
		yAxisIndex: 1,
		data: echartData.dataGpicTime
	},{
		name: echartData.dataGpicTimeName,
		type:'line',
		yAxisIndex: 1,
		data: echartData.dataGpicTime
	}
	]
};
var tradeOption2 = {
	color:['#004a97','#e8380d', '#009B63','#004a97','#e8380d', '#009B63'],
	title: {
		show: true,
        		text: '保费计算'
    	},
	tooltip: {
		trigger: 'axis'
	},
	legend: {
		orient: 'horizontal', 
        		x: 'right',
        		y: 'top',
		data: [echartData.dataCpicCountNamePremium, echartData.dataPiccCountNamePremium,echartData.dataGpicCountNamePremium,echartData.dataCpicTimeNamePremium, echartData.dataPiccTimeNamePremium,echartData.dataGpicTimeNamePremium]
	},
	grid: {
	        	left: '3%',
	        	right: '4%',
	        	bottom: '3%',
	        	containLabel: true
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
		name : '',
		splitArea: {
			show: false
			}
		},
		{
		type: 'value',
		name : '',
		splitArea: {
			show: false
			}
		}
	],
	series: [{
		name: echartData.dataCpicCountNamePremium,
		type:'bar',
		data: echartData.dataCpicCountPremium
	}, {
		name: echartData.dataPiccCountNamePremium,
		type:'bar',
		data: echartData.dataPiccCountPremium
	},{
		name: echartData.dataGpicCountNamePremium,
		type:'bar',
		data: echartData.dataGpicCountPremium
	},{
		name: echartData.dataGpicTimeNamePremium,
		type:'line',
		yAxisIndex: 1,
		data: echartData.dataGpicTimePremium
	},{
		name: echartData.dataGpicTimeNamePremium,
		type:'line',
		yAxisIndex: 1,
		data: echartData.dataGpicTimePremium
	},{
		name: echartData.dataGpicTimeNamePremium,
		type:'line',
		yAxisIndex: 1,
		data: echartData.dataGpicTimePremium
	}
	]
};
var tradeOption3 = {
	color:['#004a97','#e8380d', '#009B63','#004a97','#e8380d', '#009B63'],
	title: {
		show: true,
        		text: '保单提交'
    	},
	tooltip: {
		trigger: 'axis'
	},
	legend: {
		orient: 'horizontal', 
        		x: 'right',
        		y: 'top',
		data: [echartData.dataCpicCountNameApplication, echartData.dataPiccCountNameApplication,echartData.dataGpicCountNameApplication,echartData.dataCpicTimeNameApplication, echartData.dataPiccTimeNameApplication,echartData.dataGpicTimeNameApplication]
	},
	grid: {
	        	left: '3%',
	        	right: '4%',
	        	bottom: '3%',
	        	containLabel: true
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
		name : '',
		splitArea: {
			show: false
			}
		},
		{
		type: 'value',
		name : '',
		splitArea: {
			show: false
			}
		}
	],
	series: [{
		name: echartData.dataCpicCountNameApplication,
		type:'bar',
		data: echartData.dataCpicCountApplication
	}, {
		name: echartData.dataPiccCountNameApplication,
		type:'bar',
		data: echartData.dataPiccCountApplication
	},{
		name: echartData.dataGpicCountNameApplication,
		type:'bar',
		data: echartData.dataGpicCountApplication
	},{
		name: echartData.dataGpicTimeNameApplication,
		type:'line',
		yAxisIndex: 1,
		data: echartData.dataGpicTimeApplication
	},{
		name: echartData.dataGpicTimeNameApplication,
		type:'line',
		yAxisIndex: 1,
		data: echartData.dataGpicTimeApplication
	},{
		name: echartData.dataGpicTimeNameApplication,
		type:'line',
		yAxisIndex: 1,
		data: echartData.dataGpicTimeApplication
	}
	]
};
var renderChart=function(chart1,chart2,chart3,queryData,_this){
	chart1.showLoading({textStyle : {fontSize : 20}});
	chart2.showLoading({textStyle : {fontSize : 20}});
	chart3.showLoading({textStyle : {fontSize : 20}});
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
		 		    'indicators': queryData.indicators,
		 		    'provinces': queryData.provinces,
		 		    'interfaceTypes': queryData.interfaceTypes
		  })
	});
	result.then(function(response) {
		return response.json();
	}).then(function(j) {
		//console.log(j);
			//实时设置其他vue数据
			_this.dispatchDataLoad(_this,j);
			//按照指标设置数据
			_this.modifyEchartData(j,echartData,queryData.indicators);
			//处理chart各自数据
			_this.updatechart1Data(queryData,echartData);
			_this.updatechart2Data(queryData,echartData);
			_this.updatechart3Data(queryData,echartData);
			//处理Echart样式
			_this.updateEchartStyle(queryData,echartData,tradeOption1);
			_this.updateEchartStyle(queryData,echartData,tradeOption2);
			_this.updateEchartStyle(queryData,echartData,tradeOption3);
			//处理chart各自标题样式
			_this.updateChartStyle(chart1,chart2,chart3);
	}).catch(function(ex) {
		console.log('failed', ex)
	});
};
var myChart1;
var myChart2;
var myChart3;
var schedule = require('node-schedule');
var moment=require('moment');
var today=moment().format('YYYY-MM-DD');
//初始化查询条件
var arr1 = [];
var arr2 = [];
var queryCondition={
            startDate: today,
            endDate: today,
            contrastStartDate: '',
            contrastEndDate: '',
            periodType: 'TWO_MINUTE',//默认实时
            indicators: ['TOTAL_COUNT','MAX_DURATION'],//默认总交易数
            provinces: arr1,
            interfaceTypes: arr2
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
  			myChart1 = echarts.init(document.getElementById('statisticsData'),theme);	
			myChart2 = echarts.init(document.getElementById('calculatePremium'),theme);
			myChart3 = echarts.init(document.getElementById('submitApplication'),theme);
			renderChart(myChart1,myChart2,myChart3,queryData,_this);	
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
  			echartData.queryDates = j.reportData.dataTitle;
  			if(indicators[0]==='TOTAL_COUNT'){
  				echartData.dataCpicCount = j.reportData.items.cpic_TOTAL_COUNT;
				echartData.dataPiccCount = j.reportData.items.picc_TOTAL_COUNT;
				echartData.dataGpicCount = j.reportData.items.gpic_TOTAL_COUNT;
				echartData.dataCpicCountName = j.reportData.indicators.cpic_TOTAL_COUNT;
				echartData.dataPiccCountName = j.reportData.indicators.picc_TOTAL_COUNT;
				echartData.dataGpicCountName = j.reportData.indicators.gpic_TOTAL_COUNT;
  				//Premium		
  				echartData.dataCpicCountPremium = j.reportData.items.cpic_calculatePremium_TOTAL_COUNT;
  				echartData.dataPiccCountPremium = j.reportData.items.picc_calculatePremium_TOTAL_COUNT;		
				echartData.dataGpicCountPremium = j.reportData.items.gpic_calculatePremium_TOTAL_COUNT;
				echartData.dataCpicCountNamePremium = j.reportData.indicators.cpic_calculatePremium_TOTAL_COUNT;
				echartData.dataPiccCountNamePremium = j.reportData.indicators.picc_calculatePremium_TOTAL_COUNT;
				echartData.dataGpicCountNamePremium = j.reportData.indicators.gpic_calculatePremium_TOTAL_COUNT;
				//Application
				echartData.dataCpicCountApplication = j.reportData.items.cpic_submitApplication_TOTAL_COUNT;
				echartData.dataPiccCountApplication = j.reportData.items.picc_submitApplication_TOTAL_COUNT;
				echartData.dataGpicCountApplication = j.reportData.items.gpic_submitApplication_TOTAL_COUNT;
				echartData.dataCpicCountNameApplication = j.reportData.indicators.cpic_submitApplication_TOTAL_COUNT;
				echartData.dataPiccCountNameApplication = j.reportData.indicators.picc_submitApplication_TOTAL_COUNT;
				echartData.dataGpicCountNameApplication = j.reportData.indicators.gpic_submitApplication_TOTAL_COUNT;
  			}
  			if(indicators[0]==='SUCCESS_COUNT'){
  				echartData.dataCpicCount = j.reportData.items.cpic_SUCCESS_COUNT;
				echartData.dataPiccCount = j.reportData.items.picc_SUCCESS_COUNT;
				echartData.dataGpicCount = j.reportData.items.gpic_SUCCESS_COUNT;
				echartData.dataCpicCountName = j.reportData.indicators.cpic_SUCCESS_COUNT;
				echartData.dataPiccCountName = j.reportData.indicators.picc_SUCCESS_COUNT;
				echartData.dataGpicCountName = j.reportData.indicators.gpic_SUCCESS_COUNT;
  				//Premium
				echartData.dataCpicCountPremium = j.reportData.items.cpic_calculatePremium_SUCCESS_COUNT;
				echartData.dataPiccCountPremium = j.reportData.items.picc_calculatePremium_SUCCESS_COUNT;
				echartData.dataGpicCountPremium = j.reportData.items.gpic_calculatePremium_SUCCESS_COUNT;
				echartData.dataCpicCountNamePremium = j.reportData.indicators.cpic_calculatePremium_SUCCESS_COUNT;
				echartData.dataPiccCountNamePremium = j.reportData.indicators.picc_calculatePremium_SUCCESS_COUNT;
				echartData.dataGpicCountNamePremium = j.reportData.indicators.gpic_calculatePremium_SUCCESS_COUNT;
				//Application
				echartData.dataCpicCountApplication = j.reportData.items.cpic_submitApplication_SUCCESS_COUNT;
				echartData.dataPiccCountApplication = j.reportData.items.picc_submitApplication_SUCCESS_COUNT;
				echartData.dataGpicCountApplication = j.reportData.items.gpic_submitApplication_SUCCESS_COUNT;
				echartData.dataCpicCountNameApplication = j.reportData.indicators.cpic_submitApplication_SUCCESS_COUNT;
				echartData.dataPiccCountNameApplication = j.reportData.indicators.picc_submitApplication_SUCCESS_COUNT;
				echartData.dataGpicCountNameApplication = j.reportData.indicators.gpic_submitApplication_SUCCESS_COUNT;
  			}
  			if(indicators[0]==='FAILURE_COUNT'){
  				echartData.dataCpicCount = j.reportData.items.cpic_FAILURE_COUNT;
				echartData.dataPiccCount = j.reportData.items.picc_FAILURE_COUNT;
				echartData.dataGpicCount = j.reportData.items.gpic_FAILURE_COUNT;
				echartData.dataCpicCountName = j.reportData.indicators.cpic_FAILURE_COUNT;
				echartData.dataPiccCountName = j.reportData.indicators.picc_FAILURE_COUNT;
				echartData.dataGpicCountName = j.reportData.indicators.gpic_FAILURE_COUNT;
  				//Premium
				echartData.dataCpicCountPremium = j.reportData.items.cpic_calculatePremium_FAILURE_COUNT;
				echartData.dataPiccCountPremium = j.reportData.items.picc_calculatePremium_FAILURE_COUNT;
				echartData.dataGpicCountPremium = j.reportData.items.gpic_calculatePremium_FAILURE_COUNT;
				echartData.dataCpicCountNamePremium = j.reportData.indicators.cpic_calculatePremium_FAILURE_COUNT;
				echartData.dataPiccCountNamePremium = j.reportData.indicators.picc_calculatePremium_FAILURE_COUNT;
				echartData.dataGpicCountNamePremium = j.reportData.indicators.gpic_calculatePremium_FAILURE_COUNT;
				//Application
				echartData.dataCpicCountApplication = j.reportData.items.cpic_submitApplication_FAILURE_COUNT;
				echartData.dataPiccCountApplication = j.reportData.items.picc_submitApplication_FAILURE_COUNT;
				echartData.dataGpicCountApplication = j.reportData.items.gpic_submitApplication_FAILURE_COUNT;
				echartData.dataCpicCountNameApplication = j.reportData.indicators.cpic_submitApplication_FAILURE_COUNT;
				echartData.dataPiccCountNameApplication = j.reportData.indicators.picc_submitApplication_FAILURE_COUNT;
				echartData.dataGpicCountNameApplication = j.reportData.indicators.gpic_submitApplication_FAILURE_COUNT;
  			}
  			if(indicators[0]==='SUCCESS_RATE'){
  				echartData.dataCpicCount = j.reportData.items.cpic_SUCCESS_RATE;
				echartData.dataPiccCount = j.reportData.items.picc_SUCCESS_RATE;
				echartData.dataGpicCount = j.reportData.items.gpic_SUCCESS_RATE;
				echartData.dataCpicCountName = j.reportData.indicators.cpic_SUCCESS_RATE;
				echartData.dataPiccCountName = j.reportData.indicators.picc_SUCCESS_RATE;
				echartData.dataGpicCountName = j.reportData.indicators.gpic_SUCCESS_RATE;
  				//Premium
				echartData.dataCpicCountPremium = j.reportData.items.cpic_calculatePremium_SUCCESS_RATE;
				echartData.dataPiccCountPremium = j.reportData.items.picc_calculatePremium_SUCCESS_RATE;
				echartData.dataGpicCountPremium = j.reportData.items.gpic_calculatePremium_SUCCESS_RATE;
				echartData.dataCpicCountNamePremium = j.reportData.indicators.cpic_calculatePremium_SUCCESS_RATE;
				echartData.dataPiccCountNamePremium = j.reportData.indicators.picc_calculatePremium_SUCCESS_RATE;
				echartData.dataGpicCountNamePremium = j.reportData.indicators.gpic_calculatePremium_SUCCESS_RATE;
				//Application
				echartData.dataCpicCountApplication = j.reportData.items.cpic_submitApplication_SUCCESS_RATE;
				echartData.dataPiccCountApplication = j.reportData.items.picc_submitApplication_SUCCESS_RATE;
				echartData.dataGpicCountApplication = j.reportData.items.gpic_submitApplication_SUCCESS_RATE;
				echartData.dataCpicCountNameApplication = j.reportData.indicators.cpic_submitApplication_SUCCESS_RATE;
				echartData.dataPiccCountNameApplication = j.reportData.indicators.picc_submitApplication_SUCCESS_RATE;
				echartData.dataGpicCountNameApplication = j.reportData.indicators.gpic_submitApplication_SUCCESS_RATE;
  			}
  			if(indicators[1]==='MAX_DURATION'){
  				echartData.dataCpicTime = j.reportData.items.cpic_MAX_DURATION;
				echartData.dataPiccTime = j.reportData.items.picc_MAX_DURATION;
				echartData.dataGpicTime = j.reportData.items.gpic_MAX_DURATION;
				echartData.dataCpicTimeName = j.reportData.indicators.cpic_MAX_DURATION;
				echartData.dataPiccTimeName = j.reportData.indicators.picc_MAX_DURATION;
				echartData.dataGpicTimeName = j.reportData.indicators.gpic_MAX_DURATION;
  				//Premium
				echartData.dataCpicTimePremium = j.reportData.items.cpic_calculatePremium_MAX_DURATION;
				echartData.dataPiccTimePremium = j.reportData.items.picc_calculatePremium_MAX_DURATION;
				echartData.dataGpicTimePremium = j.reportData.items.gpic_calculatePremium_MAX_DURATION;
				echartData.dataCpicTimeNamePremium = j.reportData.indicators.cpic_calculatePremium_MAX_DURATION;
				echartData.dataPiccTimeNamePremium = j.reportData.indicators.picc_calculatePremium_MAX_DURATION;
				echartData.dataGpicTimeNamePremium = j.reportData.indicators.gpic_calculatePremium_MAX_DURATION;
				//Application
				echartData.dataCpicTimeApplication = j.reportData.items.cpic_submitApplication_MAX_DURATION;
				echartData.dataPiccTimeApplication = j.reportData.items.picc_submitApplication_MAX_DURATION;
				echartData.dataGpicTimeApplication = j.reportData.items.gpic_submitApplication_MAX_DURATION;
				echartData.dataCpicTimeNameApplication = j.reportData.indicators.cpic_submitApplication_MAX_DURATION;
				echartData.dataPiccTimeNameApplication = j.reportData.indicators.picc_submitApplication_MAX_DURATION;
				echartData.dataGpicTimeNameApplication = j.reportData.indicators.gpic_submitApplication_MAX_DURATION;
  			}
  			if(indicators[1]==='MIN_DURATION'){
				echartData.dataCpicTime = j.reportData.items.cpic_MIN_DURATION;
				echartData.dataPiccTime = j.reportData.items.picc_MIN_DURATION;
				echartData.dataGpicTime = j.reportData.items.gpic_MIN_DURATION;
				echartData.dataCpicTimeName = j.reportData.indicators.cpic_MIN_DURATION;
				echartData.dataPiccTimeName = j.reportData.indicators.picc_MIN_DURATION;
				echartData.dataGpicTimeName = j.reportData.indicators.gpic_MIN_DURATION;
  				//Premium
				echartData.dataCpicTimePremium = j.reportData.items.cpic_calculatePremium_MIN_DURATION;
				echartData.dataPiccTimePremium = j.reportData.items.picc_calculatePremium_MIN_DURATION;
				echartData.dataGpicTimePremium = j.reportData.items.gpic_calculatePremium_MIN_DURATION;
				echartData.dataCpicTimeNamePremium = j.reportData.indicators.cpic_calculatePremium_MIN_DURATION;
				echartData.dataPiccTimeNamePremium = j.reportData.indicators.picc_calculatePremium_MIN_DURATION;
				echartData.dataGpicTimeNamePremium = j.reportData.indicators.gpic_calculatePremium_MIN_DURATION;
				//Application
				echartData.dataCpicTimeApplication = j.reportData.items.cpic_submitApplication_MIN_DURATION;
				echartData.dataPiccTimeApplication = j.reportData.items.picc_submitApplication_MIN_DURATION;
				echartData.dataGpicTimeApplication = j.reportData.items.gpic_submitApplication_MIN_DURATION;
				echartData.dataCpicTimeNameApplication = j.reportData.indicators.cpic_submitApplication_MIN_DURATION;
				echartData.dataPiccTimeNameApplication = j.reportData.indicators.picc_submitApplication_MIN_DURATION;
				echartData.dataGpicTimeNameApplication = j.reportData.indicators.gpic_submitApplication_MIN_DURATION;
  			}
  			if(indicators[1]==='AVERAGE_DURATION'){
  				echartData.dataCpicTime = j.reportData.items.cpic_AVERAGE_DURATION;
				echartData.dataPiccTime = j.reportData.items.picc_AVERAGE_DURATION;
				echartData.dataGpicTime = j.reportData.items.gpic_AVERAGE_DURATION;
				echartData.dataCpicTimeName = j.reportData.indicators.cpic_AVERAGE_DURATION;
				echartData.dataPiccTimeName = j.reportData.indicators.picc_AVERAGE_DURATION;
				echartData.dataGpicTimeName = j.reportData.indicators.gpic_AVERAGE_DURATION;
  				//Premium
				echartData.dataCpicTimePremium = j.reportData.items.cpic_calculatePremium_AVERAGE_DURATION;
				echartData.dataPiccTimePremium = j.reportData.items.picc_calculatePremium_AVERAGE_DURATION;
				echartData.dataGpicTimePremium = j.reportData.items.gpic_calculatePremium_AVERAGE_DURATION;
				echartData.dataCpicTimeNamePremium = j.reportData.indicators.cpic_calculatePremium_AVERAGE_DURATION;
				echartData.dataPiccTimeNamePremium = j.reportData.indicators.picc_calculatePremium_AVERAGE_DURATION;
				echartData.dataGpicTimeNamePremium = j.reportData.indicators.gpic_calculatePremium_AVERAGE_DURATION;
				//Application
				echartData.dataCpicTimeApplication = j.reportData.items.cpic_submitApplication_AVERAGE_DURATION;
				echartData.dataPiccTimeApplication = j.reportData.items.picc_submitApplication_AVERAGE_DURATION;
				echartData.dataGpicTimeApplication = j.reportData.items.gpic_submitApplication_AVERAGE_DURATION;
				echartData.dataCpicTimeNameApplication = j.reportData.indicators.cpic_submitApplication_AVERAGE_DURATION;
				echartData.dataPiccTimeNameApplication = j.reportData.indicators.picc_submitApplication_AVERAGE_DURATION;
				echartData.dataGpicTimeNameApplication = j.reportData.indicators.gpic_submitApplication_AVERAGE_DURATION;
  			}
  			if(indicators[1]==='NINETY_DURATION'){
  				echartData.dataCpicTime = j.reportData.items.cpic_NINETY_DURATION;
				echartData.dataPiccTime = j.reportData.items.picc_NINETY_DURATION;
				echartData.dataGpicTime = j.reportData.items.gpic_NINETY_DURATION;
				echartData.dataCpicTimeName = j.reportData.indicators.cpic_NINETY_DURATION;
				echartData.dataPiccTimeName = j.reportData.indicators.picc_NINETY_DURATION;
				echartData.dataGpicTimeName = j.reportData.indicators.gpic_NINETY_DURATION;
  				//Premium
				echartData.dataCpicTimePremium = j.reportData.items.cpic_calculatePremium_NINETY_DURATION;
				echartData.dataPiccTimePremium = j.reportData.items.picc_calculatePremium_NINETY_DURATION;
				echartData.dataGpicTimePremium = j.reportData.items.gpic_calculatePremium_NINETY_DURATION;
				echartData.dataCpicTimeNamePremium = j.reportData.indicators.cpic_calculatePremium_NINETY_DURATION;
				echartData.dataPiccTimeNamePremium = j.reportData.indicators.picc_calculatePremium_NINETY_DURATION;
				echartData.dataGpicTimeNamePremium = j.reportData.indicators.gpic_calculatePremium_NINETY_DURATION;
				//Application
				echartData.dataCpicTimeApplication = j.reportData.items.cpic_submitApplication_NINETY_DURATION;
				echartData.dataPiccTimeApplication = j.reportData.items.picc_submitApplication_NINETY_DURATION;
				echartData.dataGpicTimeApplication = j.reportData.items.gpic_submitApplication_NINETY_DURATION;
				echartData.dataCpicTimeNameApplication = j.reportData.indicators.cpic_submitApplication_NINETY_DURATION;
				echartData.dataPiccTimeNameApplication = j.reportData.indicators.picc_submitApplication_NINETY_DURATION;
				echartData.dataGpicTimeNameApplication = j.reportData.indicators.gpic_submitApplication_NINETY_DURATION;
  			}
  		},
  		showIndicatorName: function(indicator) {
  			if(indicator==='TOTAL_COUNT'){
  				return '总交易次数';
  			}else if(indicator==='SUCCESS_COUNT'){
  				return '成功数';
  			}else if(indicator==='FAILURE_COUNT'){
  				return '失败数';
  			}else if(indicator==='SUCCESS_RATE'){
  				return '交易成功率';
  			}else if(indicator==='MAX_DURATION'){
  				return '最大时长';
  			}else if(indicator==='MIN_DURATION'){
  				return '最小时长';
  			}else if(indicator==='AVERAGE_DURATION'){
  				return '平均时长';
  			}else if(indicator==='NINETY_DURATION'){
  				return '90%时长';
  			}
  		},
  		updateChartStyle: function(chart1,chart2,chart3) {
  			chart1.hideLoading();
			chart2.hideLoading();
			chart3.hideLoading();

			tradeOption1.legend.y = 'bottom';
			tradeOption1.legend.x = 'center';
			tradeOption1.grid.bottom= '8%';
			chart1.setOption(tradeOption1);

			tradeOption2.title.x = 'center';
			tradeOption2.title.y = 'top';
			tradeOption2.grid.bottom= '15%';
			tradeOption2.title.text = '保费计算';
			tradeOption2.legend.y = 'bottom';
			tradeOption2.legend.x = 'right';
			chart2.setOption(tradeOption2);

			tradeOption3.title.x = 'center';
			tradeOption3.title.y = 'top';
			tradeOption3.grid.bottom= '15%';
			tradeOption3.legend.x = 'right';
			tradeOption3.legend.y = 'bottom';
			tradeOption3.title.text = '保单提交';
			chart3.setOption(tradeOption3);

			window.onresize = chart1.resize; 
			window.onresize = chart2.resize; 
			window.onresize = chart3.resize; 
  		},
  		updateEchartStyle: function(queryData,echartData,option) {
  			var yAxisName1 = this.showIndicatorName(queryData.indicators[0]);
  			var yAxisName2 = this.showIndicatorName(queryData.indicators[1]);
  			//指标为成功率时,设置y轴百分比
			if(queryData.indicators[0]==='SUCCESS_RATE'){
				option.yAxis=[{
					type: 'value',
					name: yAxisName1,
					splitArea: {
						show: false
						},
					axisLabel: {//设置y轴显示百分百
						show: true,
						interval: 'auto',
						formatter: '{value}%'
						}
					},{
					type: 'value',
					name: yAxisName2,
					splitArea: {
						show: false
						},
					axisLabel: {
						show: true,
						interval: 'auto',
						}
					}
				];
			}else{
				option.yAxis=[{
					type: 'value',
					name: yAxisName1,
					splitArea: {
						show: false
						}
					},{
					type: 'value',
					name: yAxisName2,
					splitArea: {
						show: false
						}
					}
				];
			}
			//设置legend显示位置
			option.legend.orient='horizontal';
			option.legend.x='right';
			option.legend.y='top';
			option.legend.data=[echartData.dataCpicCountName, echartData.dataPiccCountName,echartData.dataGpicCountName,echartData.dataCpicTimeName, echartData.dataPiccTimeName,echartData.dataGpicTimeName];
			if(queryData.periodType==='TWO_MINUTE' ){
				option.tooltip=[{
					trigger: 'axis'
				}];
				option.xAxis=[{
					type: 'category',
					boundaryGap : true,
					axisLabel :{  
					    	interval:'auto'  
					},
					data:  echartData.queryDates
				}];
			}else if(queryData.periodType==='HOUR'){
				option.tooltip=[{
					trigger: 'axis',
					formatter: '{b0}:00-{b1}:59<br/>{a0} : {c0}<br/>{a1} : {c1}<br/>{a2} : {c2}<br/>{a3} : {c3}<br/>{a4} : {c4}<br/>{a5} : {c5}'
				}];
				option.xAxis=[{
					type: 'category',
					boundaryGap : true,
					axisLabel :{  
					    	interval:'auto' ,  
					    	formatter: function (value, index) {
					    		if(index===23){
					    			value = '23点';
					    		}
					    		return value;
					    	}
					},
					
					data:  echartData.queryDates
				}];
			}else{
				option.tooltip=[{
					trigger: 'axis'
				}];
				option.xAxis=[{
					type: 'category',
					boundaryGap : false,//设置坐标轴数据靠近原点
					data:  echartData.queryDates
				}];
			}
  		},
  		updatechart2Data: function(queryData,echartData) {
  			tradeOption2.series=[{
				name: echartData.dataCpicCountNamePremium,
				type:'bar',
				barGap: '0',
				barWidth : 8,//柱图宽度
				data: echartData.dataCpicCountPremium
			}, {
				name: echartData.dataPiccCountNamePremium,
				type:'bar',
				barGap: '0',
				barWidth : 8,//柱图宽度
				data: echartData.dataPiccCountPremium
			},{
				name: echartData.dataGpicCountNamePremium,
				type:'bar',
				barGap: '0',
				barWidth : 8,//柱图宽度
				data: echartData.dataGpicCountPremium
			},{
				name: echartData.dataCpicTimeNamePremium,
				type:'line',
				yAxisIndex: 1,
				data: echartData.dataCpicTimePremium
			},{
				name: echartData.dataPiccTimeNamePremium,
				type:'line',
				yAxisIndex: 1,
				data: echartData.dataPiccTimePremium
			},{
				name: echartData.dataGpicTimeNamePremium,
				type:'line',
				yAxisIndex: 1,
				data: echartData.dataGpicTimePremium
			}
			];
  		},
  		updatechart3Data: function(queryData,echartData) {
  			tradeOption3.series=[{
				name: echartData.dataCpicCountNameApplication,
				type:'bar',
				barGap: '0',
				barWidth : 8,//柱图宽度
				data: echartData.dataCpicCountApplication
			}, {
				name: echartData.dataPiccCountNameApplication,
				type:'bar',
				barGap: '0',
				barWidth : 8,//柱图宽度
				data: echartData.dataPiccCountApplication
			},{
				name: echartData.dataGpicCountNameApplication,
				type:'bar',
				barGap: '0',
				barWidth : 8,//柱图宽度
				data: echartData.dataGpicCountApplication
			},{
				name: echartData.dataCpicTimeNameApplication,
				type:'line',
				yAxisIndex: 1,
				data: echartData.dataCpicTimeApplication
			},{
				name: echartData.dataPiccTimeNameApplication,
				type:'line',
				yAxisIndex: 1,
				data: echartData.dataPiccTimeApplication
			},{
				name: echartData.dataGpicTimeNameApplication,
				type:'line',
				yAxisIndex: 1,
				data: echartData.dataGpicTimeApplication
			}
			];
  		},
  		updatechart1Data: function(queryData,echartData) {
  			tradeOption1.series=[{
				name: echartData.dataCpicCountName,
				type:'bar',
				barGap: '0',
				barWidth : 8,//柱图宽度
				data: echartData.dataCpicCount
			}, {
				name: echartData.dataPiccCountName,
				type:'bar',
				barGap: '0',
				barWidth : 8,//柱图宽度
				data: echartData.dataPiccCount
			},{
				name: echartData.dataGpicCountName,
				type:'bar',
				barGap: '0',
				barWidth : 8,//柱图宽度
				data: echartData.dataGpicCount
			},{
				name: echartData.dataCpicTimeName,
				type:'line',
				yAxisIndex: 1,
				data: echartData.dataCpicTime
			},{
				name: echartData.dataPiccTimeName,
				type:'line',
				yAxisIndex: 1,
				data: echartData.dataPiccTime
			},{
				name: echartData.dataGpicTimeName,
				type:'line',
				yAxisIndex: 1,
				data: echartData.dataGpicTime
			}
			];
  		},
  		showEchartDisplay(periodType){
  			if(periodType==='TWO_MINUTE'){
  				$('#statisticsData').removeClass('divShow').addClass('divHide');
  				$('#statisticsCalculateData').removeClass('divHide').addClass('divShow');
  			}else{
  				$('#statisticsData').removeClass('divHide').addClass('divShow');
  				$('#statisticsCalculateData').removeClass('divShow').addClass('divHide');
  			}
  		}
  	},
  	events: {
	    'broadcast-todayStatistics-dataEchart-dataList-queryCondition': function(date) {
	      // 动态绑定查询对象数据
	      this.queryCondition = Object.assign({}, this.queryCondition, date);
	      this.drawEchart(this.queryCondition);
	      this.showEchartDisplay(this.queryCondition.periodType);
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
	width: 99%;
	height: 400px;
	min-width: 1000px;
}
.statisticsCalculateData {
	width: 99%;
	height: 400px;
	min-width: 1000px;
}

.calculatePremium {
	margin-left: 27px;
	display: inline-block;
	width: 44.5%;
	height: 400px;
	min-width: 500px;
}

.submitApplication {
	margin-left: 50px;
	display: inline-block;
	width: 44.5%;
	height: 400px;
	min-width: 500px;
}

.divHide {
	display: none;
}

.divShow {
	display: block;
}
</style>