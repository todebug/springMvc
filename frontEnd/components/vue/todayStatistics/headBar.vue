<template>
<div class="col-sm-9 col-sm-offset-3  col-md-offset-2 main">
	<div>
		<a >实时</a>
		<a >今天</a>
		<a >昨天</a>
		<a >最近7天</a>
		<a >最近30天</a>
		<a >时间区间</a>
	</div>
	<div>
		<div class='datePicker input-group date' id='dateTimePickerBegin' readonly="readonly" >
	                            <input type='text' class="form-control" id="dateValueBegin" @blur="notify"/>
	                            <span class="input-group-addon" >
	                            	<span class="glyphicon glyphicon-calendar"></span>
	                            </span>
                        	</div>
                        	<div class='datePicker input-group date' id='dateTimePickerEnd' readonly="readonly">
	                            <input type='text' class="form-control" id="dateValueEnd" @blur="notify"/>
	                            <span class="input-group-addon">
	                            	<span class="glyphicon glyphicon-calendar"></span>
	                            </span>
                        	</div>
	</div>
	<div>
		<a >按时</a>
		<a >按日</a>
		<a >按周</a>
		<a >按月</a>
	</div>
	
</div>


</template>

<script>
'use strict';
require('imports?$=jquery!../../lib/js/bootstrap.min.js');
require('imports?$=jquery!../../lib/js/bootstrap-datetimepicker.min.js');
var moment=require('moment');
var today=moment().format('YYYY-MM-DD');
var options={
	locale: 'zh-cn',
	format: 'yyyy-mm-dd hh:ii',
	defaultDate: today
};
module.exports= {
	ready:function(){
			$('#dateTimePickerBegin').datetimepicker(options);
			$('#dateTimePickerEnd').datetimepicker(options);
		},
	methods: {
		notify: function() {
			if ($('#dateValueBegin').val()!=='') {
			        this.$dispatch('head-bar-date-begin', $('#dateValueBegin').val());
			 }
		            if ($('#dateValueEnd').val()!=='') {
		           	        this.$dispatch('head-bar-date-end', $('#dateValueEnd').val());
		            }
		}
	}
	
}
</script>

<style type="text/css">
.datePicker {
	width:200px;
}
</style>