<template>
<div class="headBar" id="headBar">
	<div class="durationTarget" id="durationTarget" @click="clickTimeValueTarget">
		<a id="time">实时</a>
		<a class="active" id="today">今天</a>
		<a id="yesterday">昨天</a>
		<a id="last7Days">最近7天</a>
		<a id="last30Days">最近30天</a>
		<div id="datePickerDisplay"  class="displayDiv">
			<div class="datePicker" id="dateTimePickerBegin">
			    <input  size="16" type="text" placeholder="请选择开始时间" id="startDate" readonly>
			</div> 
			<div class="datePicker" id="dateTimePickerEnd">
			    <input  size="16" type="text" placeholder="请选择结束时间" id="endDate" readonly>
			</div> 
		</div>
		<a id="duration" @click="clickDurationTarget">时间区间</a>
		<div id="contrastDisplay"  class="contrastHideDiv">
			<div class="datePicker input-append date" id="contrastBegin" data-date-format="yyyy-mm-dd">
			    <input class="span2" size="16" type="text" placeholder="请选择开始时间" id="contrastStartDate" readonly>
			    <span class="add-on"><i class="icon-remove"></i></span>
			    <span class="add-on"><i class="icon-th"></i></span>
			</div> 
			<div class="datePicker input-append date" id="contrastEnd" data-date-format="yyyy-mm-dd">
			    <input class="span2" size="16" type="text" placeholder="请选择结束时间" id="contrastEndDate" readonly>
			    <span class="add-on"><i class="icon-remove"></i></span>
			    <span class="add-on"><i class="icon-th"></i></span>
			</div> 
		</div>
	</div>
	<span id="tip" class="tipHide">开始时间不得小于结束时间!</span>
	<div class="dataTarget" id="dataTarget" @click="clickTimeTypeTarget">
		<a id="byTime">按时</a>
		<a class="active"  id="byDay">按日</a>
		<a id="byWeek">按周</a>
		<a id="byMonth">按月</a>
	</div>
</div>
</template>

<script>
'use strict';
require('imports?$=jquery!../../lib/js/bootstrap.min.js');
require('imports?$=jquery!../../lib/js/bootstrap-datetimepicker.min.js');
require('imports?$=jquery!../../lib/js/locales/bootstrap-datetimepicker.zh-CN.js');
var Vue = require('vue')
var moment=require('moment');
var today=moment().format('YYYY-MM-DD');
var options={
	language: 'zh-CN',
	format: 'yyyy-mm-dd',
	minView: 'month',
	autoclose: true,
        	todayBtn: true,
        	endDate: today,
        	pickerPosition: "bottom-right"
};
var queryTimeData={
            periodType: 'byDay'
}
var vm = new Vue({
        data: {
            queryTimeData
        }
    });
module.exports= {
	data: function () {
	    return { 
	    	queryTimeData
	    }
	},
	ready: function(){
			this.datePicker();
			//初始化事件
			this.clickTimeValueTarget();
			this.clickTimeTypeTarget();
			$('#startDate').val(today)
			$('#endDate').val(today)
			
	},
	methods: {
		datePicker: function() {
			var _this = this;
			$('#contrastBegin').datetimepicker(options).on('show', function(ev){
				//添加点击事件样式
	                    		$('#durationTarget a').removeClass('active');
	                    		$('#duration').addClass('active');
	                    		$('#tip').removeClass('tipShow').addClass('tipHide');
			});
			$('#contrastEnd').datetimepicker(options).on('show', function(ev){
				//添加点击事件样式
	                    		$('#durationTarget a').removeClass('active');
	                    		$('#duration').addClass('active');
	                    		$('#tip').removeClass('tipShow').addClass('tipHide');
			});
			$('#contrastBegin').datetimepicker(options).on('changeDate', function(ev){
				if($('#contrastStartDate').val()!==''&&$('#contrastEndDate').val()!==''){
					if($('#contrastStartDate').val()>$('#contrastEndDate').val()){
						$('#contrastStartDate').val('');
						$('#contrastEndDate').val('');
						//提示信息
						 $('#tip').removeClass('tipHide').addClass('tipShow');
					}
				}
			});	
			$('#contrastEnd').datetimepicker(options).on('changeDate', function(ev){
				if($('#contrastStartDate').val()!==''&&$('#contrastEndDate').val()!==''){
					if($('#contrastStartDate').val()>$('#contrastEndDate').val()){
						$('#contrastStartDate').val('');
						$('#contrastEndDate').val('');
						//提示信息
						 $('#tip').removeClass('tipHide').addClass('tipShow');
					}else{
						_this.dispatchData(_this);
					}
				}
			});
		},
		clickTimeValueTarget: function() {
		            $('#durationTarget a').click(function(){
		           	         //添加点击样式
		                    $('#durationTarget a').removeClass('active');
		                    $(this).addClass('active');
		                    //去除错误提示
		                    $('#tip').removeClass('tipShow').addClass('tipHide');
		                    //点击时间标签时,datepicker赋值操作
		                    if($(this).context.id==='time'){
		                    	$('#startDate').val('');
		                    	$('#endDate').val('');
		                    }else if($(this).context.id==='today'){
		                    	$('#startDate').val(today);
		                    	$('#endDate').val(today);
		                    }else if($(this).context.id==='yesterday'){
		                    	var yesterday=moment().subtract(1, 'days').format('YYYY-MM-DD');
		                    	$('#startDate').val(yesterday);
		                    	$('#endDate').val(yesterday);
		                    }else if($(this).context.id==='last7Days'){
		                    	var weeksbefore=moment().subtract(7, 'days').format('YYYY-MM-DD');
		                    	$('#startDate').val(weeksbefore);
		                    	$('#endDate').val(today);
		                    }else if($(this).context.id==='last30Days'){
		                    	var monthbefore=moment().subtract(30, 'days').format('YYYY-MM-DD');
		                    	$('#startDate').val(monthbefore);
		                    	$('#endDate').val(today);
		                    }
		            });
		            this.dispatchData(this);
		 },
		 clickDurationTarget: function() {
		 		$('#contrastStartDate').val('');
		                    	$('#contrastEndDate').val('');
		                    	 if($('#contrastDisplay').hasClass('contrastHideDiv')){
		                    		$('#contrastDisplay').removeClass('contrastHideDiv').addClass('contrastShowDiv');
		                    	}else{
		                    		$('#contrastDisplay').removeClass('contrastShowDiv').addClass('contrastHideDiv');
		                    	}
		 },
		 clickTimeTypeTarget: function() {
		 	$('#dataTarget a').click(function(){
		           	         //添加点击样式
		                    $('#dataTarget a').removeClass('active');
		                    $(this).addClass('active');
		                    vm.queryTimeData.periodType = $(this).context.id;
		                })
		 	this.dispatchData(this);
		},
		dispatchData: function(data) {
			if($('#startDate').val()!==''&&$('#endDate').val()!==''){
				var dateData = {
					startDate: $('#startDate').val(),
					endDate: $('#endDate').val(),
					contrastStartDate: $('#contrastStartDate').val(),
             				contrastEndDate: $('#contrastEndDate').val(),
					periodType: vm.queryTimeData.periodType
				}
				data.$dispatch('head-bar-date-condition', dateData);
			}
		}
	}
	
}
</script>

<style type="text/css">

.headBar {
	margin: 5px;
	height: 36px;
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
	font-family: Microsoft Yahei,\\5FAE\8F6F\96C5\9ED1,Tahoma,Arial,Helvetica,STHeiti;
	display: inline-block;
	margin: 10px 10px 0px 10px;
	text-decoration: none;
	cursor: pointer;
	font-size: 10px;
}

.durationTarget .active {
    	color: #f1f1f1;
    	background-color: #41a1db;
    	border-radius: 5px;
    	font-weight: bold;
}

.tipShow {
	display: inline;
	min-width: 50px;
	color:red;
	font-size:10px;
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
	font-family: Microsoft Yahei,\\5FAE\8F6F\96C5\9ED1,Tahoma,Arial,Helvetica,STHeiti;
	display: inline-block;
	margin: 10px 10px 0px 10px;
	text-decoration: none;
	cursor: pointer;
	font-size: 10px;
}

.dataTarget .active {
    	color: #514c4c;
    	background-color: #faf9f9;
    	border-radius: 5px;
    	font-weight: bold;
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

</style>