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
	 		    'startDate': queryData.startDate,
	 		    'endDate': queryData.endDate,
	 		    'contrastStartDate': queryData.contrastStartDate,
	 		    'contrastEndDate': queryData.contrastEndDate,
	 		    'periodType': queryData.periodType,
	 		    'indicators': queryData.indicators
	  })
	})
	result.then(function(response) {
		return response.json();
	}).then(function(j) {
		//console.log(j);
		j={"reportData":{"dataTitle":["08:54","08:56","08:58","09:00","09:02","09:04","09:06","09:08","09:10","09:12","09:14","09:16","09:18","09:20","09:22","09:24","09:26","09:28","09:30","09:32","09:34","09:36","09:38","09:40","09:42","09:44","09:46","09:48","09:50","09:52"],"indicators":{"gpic_MAX_DURATION":"最大时长-国寿财险","cpic_MAX_DURATION":"最大时长-太保财险","picc_MAX_DURATION":"最大时长-人保财险"},"items":{"gpic_MAX_DURATION":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,26,11,0,0,0,0,0,0,0,0,0,0,0,0,0],"cpic_MAX_DURATION":[0,0,0,0,0,0,0,0,0,0,0,0,2,0,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"picc_MAX_DURATION":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]},"sumTitle":{"cpic_calculatePremium":"保费计算","gpic_calculatePremium":"保费计算","gpic":"合计","picc_calculatePremium":"保费计算","picc_submitApplication":"保单提交","cpic":"合计","cpic_submitApplication":"保单提交","picc":"合计","gpic_submitApplication":"保单提交"},"sumItems":{"cpic_calculatePremium":[2,0,2,0.00,2,1,null,1.5],"gpic_calculatePremium":[3,3,0,100.00,26,7,null,14.67],"gpic":[9,9,0,100.00,26,0,null,6],"picc_calculatePremium":[0,0,0,0,0,0,null,0],"picc_submitApplication":[0,0,0,0,0,0,null,0],"cpic":[4,2,2,50.00,2,1,null,1.75],"cpic_submitApplication":[0,0,0,0,0,0,null,0],"picc":[0,0,0,0,0,0,null,0],"gpic_submitApplication":[0,0,0,0,0,0,null,0]}}};
			 _this.judgeIndicator(queryData.indicators[0]);
			//设置判断[交易统计]还是[交易时效]渲染条件
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
					axisLabel: {
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
			tradeOption.legend.orient='horizontal';
			tradeOption.legend.x='right';
			tradeOption.legend.y='top';
			tradeOption.legend.data=[echartData.dataCpicName, echartData.dataPiccName,echartData.dataGpicName];
			tradeOption.xAxis=[{
				type: 'category',
				boundaryGap : false,
				axisLabel :{  
				    interval:0   
				},
				data:  echartData.queryDates
			}];
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
  				_this.$dispatch('loading-other-vue-data', data);
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
  			var setNames = {};
  			if(indicator==='TOTAL_COUNT' || indicator==='SUCCESS_COUNT' || indicator==='FAILURE_COUNT' || indicator==='SUCCESS_RATE'){
  				setNames.name1 = '总数';
  				setNames.name2 = '成功数';
  				setNames.name3 = '失败数';
  				setNames.name4 = '正确率';
  				setNames.flag = true;
  			}else{
  				setNames.name1 = '最大时长';
  				setNames.name2 = '最小时长';
  				setNames.name3 = '平均时长';
  				setNames.name4 = '90%时长';
  				setNames.flag = false;
  			}
  			this.setNames(setNames);

  		},
  		setNames: function(setNames) {
			this.$dispatch('set-names', setNames);
		}
  	},
  	events: {
	    'query-condition': function(dateData) {
	      // 事件回调内的 `this` 自动绑定到注册它的实例上
	      // 动态绑定查询对象数据
	      this.queryCondition = Object.assign({}, this.queryCondition, dateData);
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