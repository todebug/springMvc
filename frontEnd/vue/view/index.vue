<template>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h1 class="page-header">统计图</h1>
		<div class="row placeholders">
			<div class="col-xs-6 col-sm-6">
			<div class="flex1">
				<div class="form-group">
					<div class='input-group date' id='bookingdate'>
						<input type='text' class="form-control" />
						<span class="input-group-addon">
							<span class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
		<div id="booking" class="flex3" style="height:300px">
	</div>
	</div>
	<div class="col-xs-6 col-sm-6" >
		<div class="flex1">
			<div class="form-group">
				<div class='input-group date' id='errordate'>
					<input type='text' class="form-control" />
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-calendar"></span>
					</span>
				</div>
			</div>
		</div>
		<div id="error" class="flex3" style="height:300px">
		</div>
	</div>
	</div>
	<h2 class="sub-header">{{msg}}</h2>
	<div style="width:100%">
		<table class="table table-striped ">

		</table>
	</div>
	</div>
</template>

<script>
'use strict';
var echarts = require('echarts');
var moment=require('moment');
//  var $ = require('jquery');
// window.jQuery = $;
// window.$ = $;
var _=require("lodash");
require('imports?$=jquery!../../components/lib/js/bootstrap.js');
require('imports?$=jquery!../../components/lib/js/bootstrap-datetimepicker.min.js');
var today=moment().format('YYYY-MM-DD');
var weeksbfore=moment().subtract(7, 'days').format('YYYY-MM-DD');
var host='http://http://localhost:8888/statistic/';
var dates=[],errorDate=[],errorData=[],bookingData=[],replyData=[],cbaData=[];

var optionerror = {
	tooltip: {
		trigger: 'axis'
	},
	legend: {
		data: ['接口错误数量']
	},
	toolbox: {
		show: false,
		feature: {

		}
	},
	xAxis: [{
		type: 'category',
		data:errorDate
	}],
	yAxis: [{
		type: 'value',
		splitArea: {
			show: true
		}
	}],
	series: [{
		name: '接口错误数量',
		type:'line',
		data:errorData
	}]
};
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

var fetchList=function(){

	// var url=host+'historyInfo/getFailureInfoList';
	// var result = fetch(url)
	// result.then(function(response) {
	// 	return response.json();
	// }).then(function(j) {
	// 		console.log(j);
	// 		_.templateSettings.variable = "rc";
	// 		var template = _.template(
	// 			$( "script.template" ).html()
	// 		);
	// 		console.log(template(j.pageResultList));
	// 		$( "table" ).after(
	// 			template(j)
	// 		);

	// });


};

var renderChart=function(type,startDate,endDate,chart){
	chart.showLoading({
		textStyle : {
			fontSize : 20
		}
	});
	var bookingurl=host+'getSucessSaveHistory/'+startDate+'/'+endDate;
	var failedurl=host+'getFailureInfoCount/'+startDate+'/'+endDate;
	var url=type===1?bookingurl:failedurl;
	url = 'http://http://localhost:8888/statistic/getSucessSaveHistory/2016-05-23/2016-05-27';
	var result = fetch(url)
	result.then(function(response) {
		return response.json();
	}).then(function(j) {
		if(type===1){
			//dates=j.flowRecordDate;
			dates = ['2016-05-21','2016-05-22','2016-05-23','2016-05-24','2016-05-25','2016-05-26','2016-05-27'];
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
		}else{

			//errorDate=j.flowRecordDate;
			errorDate = ['2016-05-21','2016-05-22','2016-05-23','2016-05-24','2016-05-25','2016-05-26','2016-05-27'];
			errorData=[2,5,6,9,3,5,2];//j.flowRecordCount;
			optionerror.xAxis = [{
				type: 'category',
				data:  errorDate
			}];
			optionerror.series = [{
				name: '接口错误数量',
				type:'line',
				data:errorData
			}];
			chart.hideLoading();
			chart.setOption(optionerror);
		}
	}).catch(function(ex) {
		console.log('failed', ex)
	});
};

var options={
	locale: 'zh-cn',
	format: 'YYYY-MM-DD',
	defaultDate:today
};

var myChart,myChart2;
module.exports= {
	data:function(){
			return{
					msg:'错误详情'
			}
	},
	ready:function(){
	  myChart = echarts.init(document.getElementById('booking'));
		myChart2=echarts.init(document.getElementById('error'));
	  fetchList();
		$('#bookingdate').datetimepicker(options);
		$('#errordate').datetimepicker(options);
		$("#bookingdate").on("dp.change", function (e) {
			var changedate=$('#bookingdate').data();console.log(changedate);
			var todays=changedate.date;
			var weeksbfores=moment(todays).subtract(7, 'days').format('YYYY-MM-DD');
			renderChart(1,weeksbfores,todays,myChart);
		});
		$("#errordate").on("dp.change", function (e) {
			var changedate=$('#errordate').data();
			var todays=changedate.date;
			var weeksbfores=moment(todays).subtract(7, 'days').format('YYYY-MM-DD');
			renderChart(2,weeksbfores,todays,myChart2);
		});
		renderChart(1,weeksbfore,today,myChart);
		renderChart(2,weeksbfore,today,myChart2);
  }
}
</script>
