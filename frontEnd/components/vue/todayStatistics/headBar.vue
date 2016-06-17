<template>
<div class="headBar" id="headBar">
	<div class="durationTarget" id="durationTarget" @click="clickTimeValueTarget">
	 	<a class="active" id="TWO_MINUTE">实时</a>
		<a id="today">今天</a>
		<a id="yesterday">昨天</a>
		<a id="last7Days">最近7天</a>
		<a id="last30Days">最近30天</a>
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
		
		<div id="contrastDisplay"  class="contrastHideDiv">
		<a id="duration" @click="clickDurationTarget">与其他时间段对比</a>
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
	<span id="tip" class="tipHide">请填写对比时间!</span>
	<span id="contrastTip" class="tipHide">开始时间不得小于结束时间!</span>
	<div class="dataTarget" id="dataTarget" @click="clickTimeTypeTarget">
		<a id="HOUR">按时</a>
		<a id="DAILY">按日</a>
		<a id="WEEKLY">按周</a>
		<a id="MONTHLY">按月</a>
	</div>
</div>
</template>

<script>
'use strict';
require('imports?$=jquery!../../lib/js/bootstrap.min.js');
require('imports?$=jquery!../../lib/js/bootstrap-datetimepicker.min.js');
require('imports?$=jquery!../../lib/js/locales/bootstrap-datetimepicker.zh-CN.js');
var Vue = require('vue');
var moment=require('moment');
var options={
	language: 'zh-CN',
	format: 'yyyy-mm-dd',
	minView: 'month',
	autoclose: true,
        	todayBtn: true,
        	pickerPosition: "bottom-right"
};
var queryTimeData={
            periodType: 'TWO_MINUTE'
}
module.exports= {
	data: function() {
	    //绑定数据
	    return {queryTimeData}
	},
	ready: function(){
			this.datePicker();
			//初始化事件
			this.clickTimeValueTarget();
			this.clickTimeTypeTarget();
			$('#startDate').val('')
			$('#endDate').val('')
			
	},
	methods: {
		datePicker: function() {
			var _this = this;
			$('#dateTimePickerBegin').datetimepicker(options).on('show', function(ev){
				//添加点击事件样式
	                    		 $('#durationTarget a').removeClass('active');
	                    		$('#contrastTip').removeClass('tipShow').addClass('tipHide');
	                    		$('#tip').removeClass('tipShow').addClass('tipHide');
			});
			$('#dateTimePickerEnd').datetimepicker(options).on('show', function(ev){
				//添加点击事件样式
	                    		 $('#durationTarget a').removeClass('active');
	                    		$('#contrastTip').removeClass('tipShow').addClass('tipHide');
	                    		$('#tip').removeClass('tipShow').addClass('tipHide');
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
			$('#contrastBegin').datetimepicker(options).on('show', function(ev){
				//添加点击事件样式
	                    		$('#duration').addClass('active');
	                    		$('#contrastTip').removeClass('tipShow').addClass('tipHide');
	                    		$('#tip').removeClass('tipShow').addClass('tipHide');
			});
			$('#contrastEnd').datetimepicker(options).on('show', function(ev){
				//添加点击事件样式
	                    		$('#duration').addClass('active');
	                    		$('#contrastTip').removeClass('tipShow').addClass('tipHide');
	                    		$('#tip').removeClass('tipShow').addClass('tipHide');
			});
			$('#contrastBegin').datetimepicker(options).on('changeDate', function(ev){
				if($('#contrastStartDate').val()!==''&&$('#contrastEndDate').val()!==''){
					if($('#contrastStartDate').val()>$('#contrastEndDate').val()){
						$('#contrastStartDate').val('');
						$('#contrastEndDate').val('');
						//提示信息
						 $('#contrastTip').removeClass('tipHide').addClass('tipShow');
					}else if($('#startDate').val()===''&&$('#endDate').val()===''){
						//提示信息
						 $('#tip').removeClass('tipHide').addClass('tipShow');
					}else{
						_this.dispatchData(_this);	
					}
				}
			});	
			$('#contrastEnd').datetimepicker(options).on('changeDate', function(ev){
				if($('#contrastStartDate').val()!==''&&$('#contrastEndDate').val()!==''){
					if($('#contrastStartDate').val()>$('#contrastEndDate').val()){
						$('#contrastStartDate').val('');
						$('#contrastEndDate').val('');
						//提示信息
						 $('#contrastTip').removeClass('tipHide').addClass('tipShow');
					}else if($('#startDate').val()===''&&$('#endDate').val()===''){
						//提示信息
						 $('#tip').removeClass('tipHide').addClass('tipShow');
					}else{
						_this.dispatchData(_this);
					}
				}
			});
		},
		clickTimeValueTarget: function() {
			var _this = this;
		            $('#durationTarget a').click(function(){
		           	         //添加点击样式
		           	       if($(this).context.id!=='duration'){
		                    	$('#durationTarget a').removeClass('active');
		                    	$(this).addClass('active');
		                     }
		                    //去除错误提示
		                    $('#contrastTip').removeClass('tipShow').addClass('tipHide');
		                    $('#tip').removeClass('tipShow').addClass('tipHide');
		                    //点击时间标签时,datepicker赋值操作
		                    if($(this).context.id==='TWO_MINUTE'){
		                    	//清空查询时间
		                    	$('#startDate').val('')
				$('#endDate').val('')
		                    	//设置查询条件
		                    	_this.queryTimeData.periodType = 'TWO_MINUTE';
		                    	//移除按时等标签
		                    	$('#dataTarget a').removeClass('active');
		                    	_this.dispatchData(_this);
		                    }else if($(this).context.id==='today'){
		                    	var today=moment().format('YYYY-MM-DD');
		                    	$('#startDate').val(today);
		                    	$('#endDate').val(today);
		                    	//没有设置指标,则设置默认指标
		                    	_this.setIndicator();
		                    	_this.dispatchData(_this);
		                    }else if($(this).context.id==='yesterday'){
		                    	var yesterday=moment().subtract(1, 'days').format('YYYY-MM-DD');
		                    	$('#startDate').val(yesterday);
		                    	$('#endDate').val(yesterday);
		                    	//没有设置指标,则设置默认指标
		                    	_this.setIndicator();
		                    	_this.dispatchData(_this);
		                    }else if($(this).context.id==='last7Days'){
		                    	var today=moment().format('YYYY-MM-DD');
		                    	var weeksbefore=moment().subtract(7, 'days').format('YYYY-MM-DD');
		                    	$('#startDate').val(weeksbefore);
		                    	$('#endDate').val(today);
		                    	//没有设置指标,则设置默认指标
		                    	_this.setIndicator();
		                    	_this.dispatchData(_this);
		                    }else if($(this).context.id==='last30Days'){
		                    	var today=moment().format('YYYY-MM-DD');
		                    	var monthbefore=moment().subtract(30, 'days').format('YYYY-MM-DD');
		                    	$('#startDate').val(monthbefore);
		                    	$('#endDate').val(today);
		                    	//没有设置指标,则设置默认指标
		                    	_this.setIndicator();
		                    	_this.dispatchData(_this);
		                    }
		            });
		 },
		 setIndicator: function() {
		 	if(!$('#HOUR').hasClass('active')&&!$('#DAILY ').hasClass('active')&&!$('#WEEKLY ').hasClass('active')&&!$('#MONTHLY').hasClass('active')){
		 		//默认实时是,选择天时,默认设置按时查询指标
		 		$('#HOUR').addClass('active');
		 		this.queryTimeData.periodType = 'HOUR';
		 	}
		 },
		 clickDurationTarget: function() {
		 		$('#contrastStartDate').val('');
		                    	$('#contrastEndDate').val('');
		                    	 if($('#contrastDisplay').hasClass('contrastHideDiv')){
		                    	 	$('#duration').addClass('active');
		                    		$('#contrastDisplay').removeClass('contrastHideDiv').addClass('contrastShowDiv');
		                    	}else{
		                    		$('#duration').removeClass('active');
		                    		$('#contrastDisplay').removeClass('contrastShowDiv').addClass('contrastHideDiv');
		                    	}
		 },
		 clickTimeTypeTarget: function() {
		 	var _this = this;
		 	$('#dataTarget a').click(function(){
		 	         	//点击按时或按日或按周或按月时,如果没有预先设置时间,则默认设置今天
		 	         	_this.setQueryTime();
		 	         	//移除实时样式
		 	         	$('#TWO_MINUTE').removeClass('active');
		           	         	//添加点击样式
		                    	$('#dataTarget a').removeClass('active');
		                    	$(this).addClass('active');
		                    	_this.queryTimeData.periodType = $(this).context.id;
		                })
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
					contrastStartDate: $('#contrastStartDate').val(),
             					contrastEndDate: $('#contrastEndDate').val(),
					periodType: this.queryTimeData.periodType
				}
				//将时间条件传递值父组件[todayStatistics.vue]中,最终至子组件[dataEchart.vue]中
				if(dateData.startDate===''&&dateData.endDate===''&&dateData.contrastStartDate===''&&dateData.contrastEndDate===''){
					//时间均为空
					data.$dispatch('dispatch-headBar-dataEchart-queryCondition', dateData);
				}else if((dateData.startDate!==''&&dateData.endDate!=='')&&(dateData.contrastStartDate===''&&dateData.contrastEndDate==='')){
					//判断对比时间为空,查询时间不为空
					data.$dispatch('dispatch-headBar-dataEchart-queryCondition', dateData);
				}else if((dateData.startDate===''&&dateData.endDate==='')&&(dateData.contrastStartDate!==''&&dateData.contrastEndDate!=='')){
					//判断对比时间不为空,查询时间为空
					data.$dispatch('dispatch-headBar-dataEchart-queryCondition', dateData);
				}else if(dateData.startDate!==''&&dateData.endDate!==''&&dateData.contrastStartDate!==''&&dateData.contrastEndDate!==''){
					//时间均为不空
					data.$dispatch('dispatch-headBar-dataEchart-queryCondition', dateData);
				}	
		}
	}
}
</script>

<style type="text/css">
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

</style>