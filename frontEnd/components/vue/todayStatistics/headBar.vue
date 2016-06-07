<template>
<div class="headBar" id="headBar">
	<div class="durationTarget" id="durationTarget" @click="clickTimeValueTarget">
		<a id="time">实时</a>
		<a class="active" id="today">今天</a>
		<a id="yesterday">昨天</a>
		<a id="last7Days">最近7天</a>
		<a id="last30Days">最近30天</a>
		<a id="duration">时间区间</a>
		<div id="datePickerDisplay"  class="displayDiv">
			<div class="datePicker input-append date" id="dateTimePickerBegin" data-date-format="yyyy-mm-dd hh:ii">
			    <input class="span2" size="16" type="text" placeholder="请选择开始时间" id="dateValueBegin" >
			    <span class="add-on"><i class="icon-remove"></i></span>
			    <span class="add-on"><i class="icon-th"></i></span>
			</div> 
			<div class="datePicker input-append date" id="dateTimePickerEnd" data-date-format="yyyy-mm-dd hh:ii">
			    <input class="span2" size="16" type="text" placeholder="请选择结束时间" id="dateValueEnd"  >
			    <span class="add-on"><i class="icon-remove"></i></span>
			    <span class="add-on"><i class="icon-th"></i></span>
			</div> 
		</div>
	</div>
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
	format: 'yyyy-mm-dd hh:ii',
	autoclose: true,
        	todayBtn: true
};
var queryTimeData={
            dateType: 'byDay'
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
			$('#dateValueBegin').val(today)
			$('#dateValueEnd').val(today)
			
	},
	methods: {
		datePicker: function() {
			var _this = this;
			$('#dateTimePickerBegin').datetimepicker(options).on('show', function(ev){
				//添加点击事件样式
	                    		$('#durationTarget a').removeClass('active');
	                    		$('#duration').addClass('active');
			});
			$('#dateTimePickerEnd').datetimepicker(options).on('show', function(ev){
				//添加点击事件样式
	                    		$('#durationTarget a').removeClass('active');
	                    		$('#duration').addClass('active');
			});
			$('#dateTimePickerEnd').datetimepicker(options).on('changeDate', function(ev){
				_this.dispatchData(_this);
			});
		},
		clickTimeValueTarget: function() {
		            $('#durationTarget a').click(function(){
		           	         //添加点击样式
		                    $('#durationTarget a').removeClass('active');
		                    $(this).addClass('active');
		                    //点击时间标签时,datepicker赋值操作
		                    if($(this).context.id==='time'){
		                    	$('#dateValueBegin').val('');
		                    	$('#dateValueEnd').val('');
		                    }else if($(this).context.id==='today'){
		                    	$('#dateValueBegin').val(today);
		                    	$('#dateValueEnd').val(today);
		                    }else if($(this).context.id==='yesterday'){
		                    	var yesterday=moment().subtract(1, 'days').format('YYYY-MM-DD');
		                    	$('#dateValueBegin').val(yesterday);
		                    	$('#dateValueEnd').val(yesterday);
		                    }else if($(this).context.id==='last7Days'){
		                    	var weeksbefore=moment().subtract(7, 'days').format('YYYY-MM-DD');
		                    	$('#dateValueBegin').val(weeksbefore);
		                    	$('#dateValueEnd').val(today);
		                    }else if($(this).context.id==='last30Days'){
		                    	var monthbefore=moment().subtract(30, 'days').format('YYYY-MM-DD');
		                    	$('#dateValueBegin').val(monthbefore);
		                    	$('#dateValueEnd').val(today);
		                    }else if($(this).context.id==='duration'){
		                    	$('#dateValueBegin').val('');
		                    	$('#dateValueEnd').val('');
		                    }
		            });
		            this.dispatchData(this);
		 },
		 clickTimeTypeTarget: function() {
		 	$('#dataTarget a').click(function(){
		           	         //添加点击样式
		                    $('#dataTarget a').removeClass('active');
		                    $(this).addClass('active');
		                    vm.queryTimeData.dateType = $(this).context.id;
		                })
		 	this.dispatchData(this);
		},
		dispatchData: function(data) {
			if($('#dateValueBegin').val()!==''&&$('#dateValueEnd').val()!==''){
				var dateData = {
					dateBegin: $('#dateValueBegin').val(),
					dateEnd: $('#dateValueEnd').val(),
					dateType: vm.queryTimeData.dateType
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
	border: 1px solid #d6d6d6;
    	border-radius: 5px;
    	text-align: center;
    	background: #f7f1f1;
}


</style>