<template>
<div class="headBar" id="headBar">
	<div class="durationTarget" id="durationTarget" >
	 	<a class="active" id="TWO_MINUTE"@click="clickTimeValueTarget">实时</a>
		<a id="today"@click="clickTimeValueTarget">今天</a>
		<a id="yesterday"@click="clickTimeValueTarget">昨天</a>
		<a id="last7Days"@click="clickTimeValueTarget">最近7天</a>
		<a id="last30Days"@click="clickTimeValueTarget">最近30天</a>
		<div id="datePickerDisplay"  class="displayDiv">
			<div class="datePicker input-append date" id="dateTimePickerBegin" data-date-format="yyyy-mm-dd">
			    <input  class="span2" size="16" type="text" placeholder="请选择开始时间" id="startDate" readonly>
			    <span class="add-on"><i class="icon-remove"></i></span>
			    <span class="add-on"><i class="icon-th"></i></span>
			</div> 
			<div class="datePicker input-append date" id="dateTimePickerEnd" data-date-format="yyyy-mm-dd">
			    <input  class="span2" size="16" type="text" placeholder="请选择结束时间" id="endDate" readonly>
			    <span class="add-on"><i class="icon-remove"></i></span>
			    <span class="add-on"><i class="icon-th"></i></span>
			</div> 
		</div>
	</div>
	<span id="contrastTip" class="tipHide">开始时间不得小于结束时间!</span>
</div>
</template>

<script>
'use strict';
require('imports?$=jquery!../../../lib/js/bootstrap.min.js');
require('imports?$=jquery!../../../lib/js/bootstrap-datetimepicker.min.js');
require('imports?$=jquery!../../../lib/js/locales/bootstrap-datetimepicker.zh-CN.js');
var moment=require('moment');
var options={
	language: 'zh-CN',
	format: 'yyyy-mm-dd',
	minView: 'month',
	autoclose: true,
        	todayBtn: true,
        	zIndex: 2147483650
};
var queryTimeErrorData={
            periodType: 'TWO_MINUTE'
}
module.exports= {
	data: function() {
	    	//绑定数据
	    	return {queryTimeErrorData}
	},
            created: function() {
                    	this.initQueryData();
            },
	ready: function(){
		//初始化事件
		this.datePicker();
		$('#startDate').val('');
		$('#endDate').val('');
	},
	methods: {
		initQueryData: function() {
                            	this.queryTimeErrorData.periodType = 'TWO_MINUTE';
                        },
		datePicker: function() {
			var _this = this;
			$('#dateTimePickerBegin').datetimepicker(options).on('show', function(ev){
				//添加点击事件样式
	                    		$('#durationTarget a').removeClass('active');
	                    		$('#contrastTip').removeClass('tipShow').addClass('tipHide');
			});
			$('#dateTimePickerEnd').datetimepicker(options).on('show', function(ev){
				//添加点击事件样式
	                    		$('#durationTarget a').removeClass('active');
	                    		$('#contrastTip').removeClass('tipShow').addClass('tipHide');
			});
			$('#dateTimePickerBegin').datetimepicker(options).on('changeDate', function(ev){
				if($('#startDate').val()!==''&&$('#endDate').val()!==''){
					if($('#startDate').val()>$('#endDate').val()){
						$('#startDate').val('');
						$('#endDate').val('');
						//提示信息
						$('#contrastTip').removeClass('tipHide').addClass('tipShow');
					}else{
						_this.dispatchData(_this);	
					}
				}
			});	
			$('#dateTimePickerEnd').datetimepicker(options).on('changeDate', function(ev){
				if($('#startDate').val()!==''&&$('#endDate').val()!==''){
					if($('#startDate').val()>$('#endDate').val()){
						$('#startDate').val('');
						$('#endDate').val('');
						//提示信息
						$('#contrastTip').removeClass('tipHide').addClass('tipShow');
					}else{
						_this.dispatchData(_this);	
					}
				}
			});	
		},
		clickTimeValueTarget: function(event) {
		           	//添加点击样式
	                    	$('#durationTarget a').removeClass('active');
	                    	$(event.target).addClass('active');
	                    	//去除错误提示
	                    	$('#contrastTip').removeClass('tipShow').addClass('tipHide');
	                    	//点击时间标签时,datepicker赋值操作
	                    	if(event.target.id==='TWO_MINUTE'){
		                    	//清空查询时间
		                    	$('#startDate').val('')
				$('#endDate').val('')
		                    	//设置查询条件
		                    	this.queryTimeErrorData.periodType = 'TWO_MINUTE';
		                    	//移除按时等标签
		                    	$('#dataTarget a').removeClass('active');
	                    	}else if(event.target.id==='today'){
		                    	var today=moment().format('YYYY-MM-DD');
		                    	$('#startDate').val(today);
		                    	$('#endDate').val(today);
		                    	this.queryTimeErrorData.periodType = 'DAILY';
	                    	}else if(event.target.id==='yesterday'){
		                    	var yesterday=moment().subtract(1, 'days').format('YYYY-MM-DD');
		                    	$('#startDate').val(yesterday);
		                    	$('#endDate').val(yesterday);
		                    	this.queryTimeErrorData.periodType = 'DAILY';
	                    	}else if(event.target.id==='last7Days'){
		                    	var today=moment().format('YYYY-MM-DD');
		                    	var weeksbefore=moment().subtract(7, 'days').format('YYYY-MM-DD');
		                    	$('#startDate').val(weeksbefore);
		                    	$('#endDate').val(today);
		                    	this.queryTimeErrorData.periodType = 'DAILY';
	                    	}else if(event.target.id==='last30Days'){
		                    	var today=moment().format('YYYY-MM-DD');
		                    	var monthbefore=moment().subtract(30, 'days').format('YYYY-MM-DD');
		                    	$('#startDate').val(monthbefore);
		                    	$('#endDate').val(today);
		                    	this.queryTimeErrorData.periodType = 'DAILY';
	                    	}
		            this.dispatchData(this);
		 },
		setQueryTime: function() {
			if(($('#startDate').val()===''&&$('#endDate').val()==='')||($('#startDate').val()!==''&&$('#endDate').val()==='')||($('#startDate').val()===''&&$('#endDate').val()!=='')){
		 		//点击按时或按日或按周或按月时,如果没有预先设置时间,则默认设置今天
		 		var today=moment().format('YYYY-MM-DD');
		 		$('#today').addClass('active');
		 		$('#startDate').val(today);
		 		$('#endDate').val(today);
		 	}
		},
		dispatchData: function(data) {
			var dateData = {
				startDate: $('#startDate').val(),
				endDate: $('#endDate').val(),
				periodType: this.queryTimeErrorData.periodType
			}
			//将时间条件传递值父组件[todayStatistics.vue]中,最终至子组件[dataEchart.vue]中
			if(dateData.startDate===''&&dateData.endDate===''){
				//时间均为空
				data.$dispatch('dispatch-headBar-dataEchart-queryCondition', dateData);
			}else if(dateData.startDate!==''&&dateData.endDate!==''){
				//判断对比时间为空,查询时间不为空
				data.$dispatch('dispatch-headBar-dataEchart-queryCondition', dateData);
			}	
		}
	}
}
</script>

<style type="text/css" >
.headBar {
	margin: 5px;
	height: 34px;
	background: #e2edfb;
	border: 1px solid #d6d6d6;
    	border-radius: 5px;
    	min-width: 1000px;
}

.displayDiv {
	display: inline-block;
	position: relative;
}

.contrastHideDiv {
	display: none;
}

.contrastShowDiv {
	display: inline-block;
	position: relative;
}

.datePicker {
	display: inline-block;	
}

.durationTarget {
	display: inline;
	min-width: 800px;
}

.durationTarget a {
	font-family: 微软雅黑;
	display: inline-block;
	margin: 10px 10px 0px 10px;
	text-decoration: none;
	cursor: pointer;
	font-size: 12px;
}

.durationTarget .active {
    	color: #f1f1f1;
    	background-color: #41a1db;
    	border-radius: 5px;
    	font-weight: bold;
    	font-family: 微软雅黑;
}

.tipShow {
	display: inline;
	min-width: 50px;
	color:red;
	font-size:10px;
	font-family: 微软雅黑;
	margin-left: 10px;
}

.tipHide {
	display:none;
}

.dataTarget {
	display: inline;
	min-width: 400px;
	height: 50px;
	border: 1px solid #d6d6d6;
    	border-radius: 5px;
    	margin-left: 180px;
}

.dataTarget a {
	font-family: 微软雅黑;
	display: inline-block;
	margin: 10px 10px 0px 10px;
	text-decoration: none;
	cursor: pointer;
	font-size: 12px;
}

.dataTarget .active {
    	color: #514c4c;
    	background-color: #faf9f9;
    	border-radius: 5px;
    	font-weight: bold;
    	font-family: 微软雅黑;
}

.datePickerDisplay {
	display: inline;
}

.datePicker input {
	display: inline;
	border: 1px solid #d6d6d6;
    	border-radius: 5px;
    	text-align: center;
    	background: #f7f1f1;
}

.span2 {
	font-family: 微软雅黑;
	font-size: 12px;
}

</style>