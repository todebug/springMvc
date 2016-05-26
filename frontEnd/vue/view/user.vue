<template>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h1 class="page-header">设备统计图</h1>
		<div class="row placeholders">
			<div class="col-xs-6 col-sm-6">
				<div class="flex1">
					<div class="form-group">
						<div class='input-group date' id='loginDate'>
							<input type='text' class="form-control" />
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
				</div>
				<div id="loginData" class="flex3" style="height:300px"></div>
			</div>
			<div class="col-xs-6 col-sm-6" >
				<div class="flex1">
					<div class="form-group">
						<div id="wordCloud" class="flex3" style="height:100px"></div>	
						<div id="deviceType" class="flex3" style="height:200px"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>
<script>
var echarts = require("echarts");
var moment=require("moment");
var $ = require('jquery');
window.jQuery = $;
window.$ = $;
console.log($);
var _=require("lodash"),deviceTotal=0;
require('echarts/lib/chart/funnel');
require('echarts/lib/chart/pie');
require('echarts/lib/chart/bar');
require('imports?$=jquery!../../components/lib/js/bootstrap.js');
require('imports?$=jquery!../../components/lib/js/bootstrap-datetimepicker.min.js');
var today=moment().format('YYYY-MM-DD');
var weeksbfore=moment().subtract(7, 'days').format('YYYY-MM-DD');
var host='http://10.118.10.138:8070/statistic/';
var deviceLoginDate=[],deviceLoginData=[],androidCount=0,iosCount=0,everyDayLoginData=[],deviceTotal=0;

var optionLoginInfo = {
	    title : {
	        text: '登录设备统计',
	        subtext: '统计'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['新增设备','登录设备统计']
	    },
	    toolbox: {
	        show : false,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            data : deviceLoginDate
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'新增设备',
	            type:'bar',
	            data:deviceLoginData
	        },
	        {
	            name:'登录设备统计',
	            type:'bar',
	            data:everyDayLoginData,
	        }
	    ]
	};
var  optionDeviceType = {
	    title : {
	        text: '新增设备类型',
	        subtext: 'ios/android',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient : 'vertical',
	        x : 'right',
	        data:['iosCount','androidCount']
	    },
	    toolbox: {
	        show : false,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {
	                show: true, 
	                type: ['pie', 'funnel'],
	                option: {
	                    funnel: {
	                        x: '25%',
	                        width: '50%',
	                        funnelAlign: 'right',
	                        max: 1548
	                    }
	                }
	            },
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    series : [
	        {
	            name:'deviceCount',
	            type:'pie',
	            radius : '50%',
	            center: ['50%', '60%'],
	            data:[
	                  {value:androidCount, name:'androidCount'},
	                  {value:iosCount, name:'iosCount'}
	              ]
	        }
	    ]
	};

var fetchData=function(startDate,endDate){
	var charList=[barChart,pieChart];
	_.each(charList,function(chart){
		chart.showLoading({
			textStyle : {
				fontSize : 20
			}
		});
	});
	var urlType = 'getSucessSaveHistory/';
	var url=host+urlType+startDate+'/'+endDate;
	url = 'http://10.118.10.138:8070/statistic/getSucessSaveHistory/2016-05-23/2016-05-27'
	var result = fetch(url);
	result.then(function(response) {
		return response.json();
	}).then(function(j) {
		renderChart(1,j,barChart);
		renderChart(2,j,pieChart);
	}).catch(function(ex) {
		console.error('fetch data failed', ex)
	});

}
var renderChart=function(type,j,chart){
	if(type===1){
				//deviceLoginDate=j.newDeviceLoginDate;
				//deviceLoginDate=j.dateTime;
				deviceLoginDate = ['2016-05-21','2016-05-22','2016-05-23','2016-05-24','2016-05-25','2016-05-26','2016-05-27'];
				deviceLoginData=[2,5,6,9,3,5,2];//j.newDeviceLoginCount;
				everyDayLoginData=[4,5,8,2,6,7,2];//j.deviceCount;
				optionLoginInfo.xAxis=[{
					type: 'category',
					data:  deviceLoginDate
				}];
				optionLoginInfo.series=[{
					name: '新增设备',
					type:'bar',
					data: deviceLoginData
				},
				{
					name: '登录设备统计',
					type:'bar',
					data: everyDayLoginData
				}];
					chart.hideLoading();
					chart.setOption(optionLoginInfo);
			}else if(type===2){
				androidCount=5;//j.androidType;
				iosCount=4;//j.iosType;
				optionDeviceType.series=[{
					name: 'deviceCount',
					type:'pie',
		            radius : '50%',
		            center: ['50%', '60%'],
		            data:[
		                  {value:androidCount, name:'androidCount'},
		                  {value:iosCount, name:'iosCount'}
		              ]
				}
				];
				chart.hideLoading();
				chart.setOption(optionDeviceType);
			}

};

var options={
		locale: 'zh-cn',
		format: 'YYYY-MM-DD',
		defaultDate:today
	};

var barChart,pieChart;
module.exports= {
		ready:function(){
				barChart = echarts.init(document.getElementById('loginData'));
				pieChart = echarts.init(document.getElementById('deviceType'));
				$('#loginDate').datetimepicker(options);
				$("#loginDate").on("dp.change", function (e) {
					var changedate=$('#loginDate').data();
					var todays=changedate.date;
					var weeksbfores=moment(todays).subtract(7, 'days').format('YYYY-MM-DD');
					fetchData(weeksbfores,todays);
				});
				fetchData(weeksbfore,today);
		  }
}



</script>
